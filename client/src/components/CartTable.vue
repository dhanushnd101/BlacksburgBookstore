<script setup lang="ts">
import { useCartStore } from "@/stores/CartStore";
const cartStore = useCartStore();
import { BookItem } from "@/types";
import { asDollarsAndCents } from "@/utils";
import { useCategoryStore } from "@/stores/CategoryStore";
const categoryStore = useCategoryStore();

const bookImageFileName = function (book: BookItem): string {
  let name = book.title.toLowerCase();
  name = name.replace(/ /g, "-");
  name = name.replace(/'/g, "");
  return `${name}.gif`;
};
const updateCart = function (book: BookItem, quantity: number) {
  cartStore.cart.update(book, quantity);
};
</script>

<style scoped>
.tg td {
  font-size: 14px;
  padding: 10px 5px;
  word-break: normal;
}
.tg th {
  font-size: 14px;
  font-weight: normal;
  padding: 3px 5px;
  word-break: normal;
}
.tg .tg-wp8o {
  border-color: transparent;
  text-align: center;
  vertical-align: top;
  color: black;
  font-size: 15px;
}
.tg .tg-73oq {
  border-color: transparent;
  text-align: left;
  vertical-align: top;
  color: black;
  font-size: 15px;
}

.cart-table {
  display: grid;
  grid-template-columns: max-content minmax(15em, 20em) repeat(3, max-content);
  row-gap: 0.5em;
  width: fit-content;
  margin: auto;
  margin-bottom: 30px;
  border-radius: 20px;
  box-shadow: 0px 0px 3px 1px cornflowerblue;
  background: #edf7fd;
}

ul {
  display: contents;
}

ul > li {
  display: contents;
}

.table-heading {
  color: #0f4069;
}

.table-heading > * {
  margin: 10px;
  padding: 0.5em;
}

.heading-book {
  grid-column: 1 / 2;
}

.heading-price {
  grid-column: 3 / 4;
  text-align: right;
}

.heading-quantity {
  grid-column: 4/5;
  text-align: right;
}

.heading-subtotal {
  text-align: right;
  grid-column: -2 / -1;
}

.cart-book-image {
  padding: 0 1em;
}

.cart-book-image > * {
  margin-left: auto;
  margin-right: 0;
}

img {
  display: block;
  width: 100px;
  height: 200px;
  height: auto;
}

.cart-book-price {
  padding-left: 1em;
  text-align: right;
}

.cart-book-quantity {
  padding-left: 1em;
  padding-right: 1em;
}

.cart-book-subtotal {
  text-align: right;
  padding-left: 1em;
  padding-right: 1em;
  min-width: 100px;
}

/* Row separators in the table */

.line-sep {
  display: block;
  height: 2px;
  background-color: cornflowerblue;
  grid-column: 1 / -1;
}

/* Increment/decrement buttons */

.icon-button {
  border: none;
  cursor: pointer;
  padding: 0px;
}

.inc-button {
  font-size: 1.125rem;
  color: #1ec21e;
  background: transparent;
}

.inc-button:hover {
  color: #038003;
}

.dec-button {
  font-size: 1.125rem;
  color: #ff5252;
  background: transparent;
}

.dec-button:hover {
  color: #ff0808;
}

input[type="number"] {
  width: 4em;
}

.quantity {
  display: inline-block;
  min-width: 23px;
  text-align: center;
}

select {
  background-color: var(--primary-color);
  color: white;
  border: 2px solid var(--primary-color-dark);
  border-radius: 3px;
}

.image-empty-cart {
  display: flex;
  width: 600px;
}
.empty-cart {
  display: block;
  text-align: center;
  display: flex;
  flex-direction: row;
  justify-content: space-around;
}

h2 {
  color: #06145f;
  padding-top: 100px;
  padding-bottom: 10px;
}

.check-out {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
}
.check-out-box {
  border-radius: 20px;
  padding: 15px 20px 10px 35px;
  box-shadow: 0px 0px 3px 1px cornflowerblue;
  background: #edf7fd;
}
.checkout-button {
  border-radius: 20px;
  padding: 5px 40px 5px 40px;
  margin-bottom: 10px;
}
.delete-button {
  padding-left: 17px;
  padding-bottom: 5px;
  color: red;
}
.delete-button:hover {
  color: #860000;
  cursor: pointer;
}
.continue-shopping-button {
  border: solid;
  background-color: transparent;
  border-radius: 20px;
  padding: 5px 50px 5px 50px;
  margin-bottom: 30px;
  color: cornflowerblue;
}
.continue-shopping-button:hover {
  cursor: pointer;
  background-color: #adcae7;
  color: #06145f;
}
</style>

<template>
  <div v-if="cartStore.count == 0" class="empty-cart">
    <div>
      <h2>Your shopping cart is empty</h2>
      <router-link
        :to="{
          name: 'category-view',
          params: { name: categoryStore.categoryName },
        }"
        class="button"
      >
        Continue shopping
      </router-link>
    </div>

    <img
      class="image-empty-cart"
      :src="require('@/assets/images/site/empty-cart.webp')"
    />
  </div>
  <div v-else class="check-out">
    <div class="cart-table">
      <ul>
        <li class="table-heading">
          <div class="heading-book">Book</div>
          <div class="heading-price">Price</div>
          <div class="heading-quantity">Quantity</div>
          <div class="heading-subtotal">Amount</div>
        </li>
        <li class="line-sep"></li>

        <template v-for="item in cartStore.cart.items" :key="item.book.bookId">
          <li>
            <div class="cart-book-image">
              <img
                :src="
                  require('@/assets/images/books/' +
                    bookImageFileName(item.book))
                "
                :alt="item.book.title"
                width="100px"
                height="auto"
              />
            </div>
            <div class="cart-book-title">{{ item.book.title }}</div>
            <div class="cart-book-price">
              {{ asDollarsAndCents(item.book.price) }}
            </div>
            <div class="cart-book-quantity">
              &nbsp;
              <button
                class="icon-button inc-button"
                @click="
                  cartStore.updateBookQuantity(item.book, item.quantity + 1)
                "
              >
                <i class="fas fa-plus-circle"></i>
              </button>
              <span class="quantity">{{ item.quantity }}</span>
              <button
                class="icon-button dec-button"
                @click="
                  cartStore.updateBookQuantity(item.book, item.quantity - 1)
                "
              >
                <i class="fas fa-minus-circle"></i>
              </button>
            </div>
            <div class="cart-book-subtotal">
              {{ asDollarsAndCents(item.book.price * item.quantity) }}
            </div>
          </li>
          <li class="line-sep"></li>
        </template>
        <li class="table-heading">
          <div class="heading-book">Total Amount</div>
          <div class="heading-subtotal">
            {{ asDollarsAndCents(cartStore.totalAmountWithoutSurcharge) }}
          </div>
        </li>
      </ul>
      <li class="line-sep"></li>
      <a class="delete-button" @click="cartStore.clearCart()">Delete items</a>
    </div>
    <div>
      <div class="check-out-box">
        <div>
          <router-link to="/checkout" class="button checkout-button">
            Continue to Checkout
          </router-link>
        </div>
        <div>
          <router-link
            :to="{
              name: 'category-view',
              params: { name: categoryStore.categoryName },
            }"
            class="button continue-shopping-button"
          >
            Continue shopping
          </router-link>
        </div>
        <table class="tg" style="undefined;table-layout: fixed; width: 245px">
          <colgroup>
            <col style="width: 191px" />
            <col style="width: 54px" />
          </colgroup>
          <thead>
            <tr>
              <th class="tg-73oq">
                <span style="font-weight: bold; font-style: normal"
                  >Subtotal</span
                >
                ({{ cartStore.count }} Item)
              </th>
              <th class="tg-wp8o">
                {{ asDollarsAndCents(cartStore.totalAmountWithoutSurcharge) }}
              </th>
            </tr>
          </thead>
          <tbody>
            <tr class="line-box">
              <td class="tg-73oq">Tax</td>
              <td class="tg-wp8o">
                {{ (cartStore.surcharge / 100).toFixed(2) }}%
              </td>
            </tr>
            <tr>
              <td class="tg-73oq">
                <span style="font-weight: bold">Total</span>
              </td>
              <td class="tg-wp8o">
                {{ asDollarsAndCents(cartStore.totalAmount) }}
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>
