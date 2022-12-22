package business.order;

import api.ApiException;
import business.BookstoreDbException;
import business.JdbcUtils;
import business.book.Book;
import business.book.BookDao;
import business.cart.ShoppingCart;
import business.cart.ShoppingCartItem;
import business.customer.Customer;
import business.customer.CustomerDao;
import business.customer.CustomerForm;


import java.sql.Connection;
import java.sql.SQLException;
import java.time.YearMonth;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DefaultOrderService implements OrderService {

	private BookDao bookDao;
	private OrderDao orderDao;
	private LineItemDao lineItemDao;
	private CustomerDao customerDao;

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	public void setOrderDao(OrderDao orderDao){this.orderDao = orderDao;}
	public void setLineItemDao(LineItemDao lineItemDao) {this.lineItemDao = lineItemDao;}
	public void setCustomerDao(CustomerDao customerDao){this.customerDao = customerDao;}

	@Override
	public OrderDetails getOrderDetails(long orderId) {
		Order order = orderDao.findByOrderId(orderId);
		Customer customer = customerDao.findByCustomerId(order.getCustomerId());
		List<LineItem> lineItems = lineItemDao.findByOrderId(orderId);
		List<Book> books = lineItems
				.stream()
				.map(lineItem -> bookDao.findByBookId(lineItem.getBookId()))
				.collect(Collectors.toList());
		return new OrderDetails(order, customer, lineItems, books);
	}

	@Override
    public long placeOrder(CustomerForm customerForm, ShoppingCart cart) {

		validateCustomer(customerForm);
		validateCart(cart);

		try (Connection connection = JdbcUtils.getConnection()) {
			Date date = getDate(
					customerForm.getCcExpiryMonth(),
					customerForm.getCcExpiryYear());
			return performPlaceOrderTransaction(
					customerForm.getName(),
					customerForm.getAddress(),
					customerForm.getPhone(),
					customerForm.getEmail(),
					customerForm.getCcNumber(),
					date, cart, connection);
		} catch (SQLException e) {
			throw new BookstoreDbException("Error during close connection for customer order", e);
		}
	}

	private Date getDate(String monthString, String yearString) {
		Date date = new Date(Integer.valueOf(yearString)-1900,Integer.valueOf(monthString)-1,5);
		return date;
	}

	private long performPlaceOrderTransaction(
			String name, String address, String phone,
			String email, String ccNumber, Date date,
			ShoppingCart cart, Connection connection) {
		try {
			connection.setAutoCommit(false);
			long customerId = customerDao.create(connection, name, address, phone, email,
					ccNumber, date);
			long customerOrderId = orderDao.create(
					connection,
					cart.getComputedSubtotal() + cart.getSurcharge(),
					generateConfirmationNumber(), customerId);
			for (ShoppingCartItem item : cart.getItems()) {
				lineItemDao.create(connection, customerOrderId, item.getBookId(),
						item.getQuantity());
			}
			connection.commit();
			return customerOrderId;
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new BookstoreDbException("Failed to roll back transaction", e1);
			}
			return 0;
		}
	}

	private int generateConfirmationNumber(){
		Random random = new Random();
		return random.nextInt(999999999);
	}



	private void validateCustomer(CustomerForm customerForm) {

		//name validation
    	String name = customerForm.getName();
		if (name == null || name.equals("") || name.length() > 45 || name.length()<4) {
			throw new ApiException.InvalidParameter("Invalid name field");
		}

		//address validation
		String address = customerForm.getAddress();
		if (address == null || address.equals("") || address.length() > 45 || address.length()<4) {
			throw new ApiException.InvalidParameter("Invalid address field");
		}

		// Phone validation
		String phone = customerForm.getPhone();
		if(phone== null || phone.equals("")){
			throw new ApiException.InvalidParameter("The phone number provided is incorrect");
		}

		String pattern = "^(\\+\\d{1,2}\\s?)?1?\\-?\\.?\\s?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$";
		Pattern pat = Pattern.compile(pattern);
		Matcher mat = pat.matcher(phone);
		if(!mat.matches() || phone== null || phone.equals("")){
			throw new ApiException.InvalidParameter("The phone number provided is incorrect");
		}

		//Email validation
		String email = customerForm.getEmail();
		if(email== null || email.equals("")){
			throw new ApiException.InvalidParameter("The Email provided is incorrect");
		}

		String patternEmail = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
		Pattern patEmail = Pattern.compile(patternEmail);
		Matcher matEmail = patEmail.matcher(email);
		if(!matEmail.matches() || email== null || email.equals("")){
			throw new ApiException.InvalidParameter("The Email provided is incorrect");
		}

		//cc validation
		String cc = customerForm.getCcNumber();
		if(cc== null || cc.equals("")){
			throw new ApiException.InvalidParameter("The credit card provided is incorrect");
		}

		cc = cc.replace(" ","");
		cc = cc.replace("-","");
		cc = cc.replace(".","");
		String patternCC = "^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$";

		Pattern patCC = Pattern.compile(patternCC);
		Matcher matCC = patCC.matcher(cc);
		if(!matCC.matches() || cc== null || cc.equals("")){
			throw new ApiException.InvalidParameter("The credit card provided is incorrect");
		}

		//date validation
		String month = customerForm.getCcExpiryMonth();
		String year = customerForm.getCcExpiryYear();
		if (month ==null || year ==null|| month.equals("")||year.equals("")|| expiryDateIsInvalid(month,year)) {
			throw new ApiException.InvalidParameter("Invalid expiry date");
		}
	}

	private boolean expiryDateIsInvalid(String ccExpiryMonth, String ccExpiryYear) {
		int year = Integer.parseInt(ccExpiryYear);
		int month = Integer.parseInt(ccExpiryMonth);
		if (month > 12 || month < 1) {
			return true;
		}
		YearMonth expiryDate = YearMonth.of(year, month);
		YearMonth today = YearMonth.now();
		return expiryDate.isBefore(today);
	}

	private void validateCart(ShoppingCart cart) {

		//empty cart
		if (cart.getItems().size() <= 0) {
			throw new ApiException.InvalidParameter("Cart is empty.");
		}

		//for each book
		cart.getItems().forEach(item-> {
			if (item.getQuantity() < 1 || item.getQuantity() > 99) {
				throw new ApiException.InvalidParameter("Invalid quantity");
			}
			Book databaseBook = bookDao.findByBookId(item.getBookId());

			//incorrect price
			if(databaseBook.getPrice() != item.getBookForm().getPrice()){
				throw new ApiException.InvalidParameter("Invalid price (does not match the price in DB)");
			}

			//incorrect category
			if(databaseBook.getCategoryId() != item.getBookForm().getCategoryId()) {
				throw new ApiException.InvalidParameter("Invalid Category (does not match the Category in DB)");
			}
		});
	}

}
