package business.order;

import api.ApiException;
import business.book.Book;
import business.book.BookDao;
import business.cart.ShoppingCart;
import business.customer.CustomerForm;

import java.time.DateTimeException;
import java.time.YearMonth;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultOrderService implements OrderService {

	private BookDao bookDao;

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	@Override
	public OrderDetails getOrderDetails(long orderId) {
		// NOTE: THIS METHOD PROVIDED NEXT PROJECT
		return null;
	}

	@Override
    public long placeOrder(CustomerForm customerForm, ShoppingCart cart) {

		validateCustomer(customerForm);
		validateCart(cart);

		// NOTE: MORE CODE PROVIDED NEXT PROJECT

		return -1;
	}


	private void validateCustomer(CustomerForm customerForm) {

		//name validation
    	String name = customerForm.getName();
		if (name == null || name.equals("") || name.length() > 45 || name.length()<3) {
			throw new ApiException.InvalidParameter("Invalid name field");
		}

		//address validation
		String address = customerForm.getAddress();
		if (address == null || address.equals("") || address.length() > 45 || address.length()<4) {
			throw new ApiException.InvalidParameter("Invalid address field");
		}

		// Phone validation
		String phone = customerForm.getPhone();
		if( phone== null || name.equals("")){
			throw new ApiException.InvalidParameter("The phone number provided is incorrect");
		}

		String pattern = "^(\\+\\d{1,2}\\s?)?1?\\-?\\.?\\s?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$";
		Pattern pat = Pattern.compile(pattern);
		Matcher mat = pat.matcher(phone);
		if( phone== null || name.equals("")|| !mat.matches()){
			throw new ApiException.InvalidParameter("The phone number provided is incorrect");
		}

		//Email validation
		String email = customerForm.getEmail();
		if (email == null || email.equals("") || !email.contains("@") || email.contains(" ") || email.endsWith(".")) {
			throw new ApiException.InvalidParameter("Invalid email field");
		}

		//cc validation
		String cc = customerForm.getCcNumber();

		if( cc== null || cc.equals("")){
			throw new ApiException.InvalidParameter("The credit card provided is incorrect");
		}

		cc = cc.replace(" ","");
		cc = cc.replace("-","");
		cc = cc.replace(".","");
		String patternCC = "^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$";

		Pattern patCC = Pattern.compile(patternCC);
		Matcher matCC = patCC.matcher(cc);
		if(!matCC.matches() ){
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
			};

			//incorrect category
			if(databaseBook.getCategoryId() != item.getBookForm().getCategoryId()){
				throw new ApiException.InvalidParameter("Invalid Category (does not match the Category in DB)");
			};
		});
	}

}
