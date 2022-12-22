<script setup lang="ts">
import { useCartStore } from "@/stores/CartStore";
const cartStore = useCartStore();
import { asDollarsAndCents } from "@/utils";

import { useOrderDetailsStore } from "@/stores/OrderDetailsStore";
import { BookItem, OrderDetails } from "@/types";

const orderDetailsStore = useOrderDetailsStore();
const orderDetails = orderDetailsStore.orderDetails;

const bookAt = function (orderDetails: OrderDetails, index: number): BookItem {
  return orderDetails.books[index];
};
</script>

<style scoped>
table {
  border: 1px cornflowerblue solid;
  width: fit-content;
  margin-bottom: 1em;
  border-radius: 10px;
}

td {
  display: table-cell;
  padding: 0.5em 0.5em;
  background-color: transparent;
  vertical-align: middle;
}

tr:nth-child(even) > td {
  background-color: rgb(225, 236, 246);
  border-radius: 2px;
}

td:nth-child(1) {
  text-align: left;
}

td:nth-child(2) {
  text-align: center;
}

td:nth-child(3) {
  text-align: right;
}

.checkout-details {
  color: #0f4069;
  font-size: small;
  text-align: center;
}

.checkout-details strong {
  color: #1ead1b;
}
</style>

<template>
  <table>
    <td>Book</td>
    <td>Quantity</td>
    <td>Price</td>

    <tr v-for="(item, index) in orderDetails.lineItems" :key="item.productId">
      <td>
        {{ orderDetails.books[index].title }}
      </td>
      <td>{{ item.quantity }}</td>
      <td>{{ asDollarsAndCents(bookAt(orderDetails, index).price) }}</td>
    </tr>
  </table>

  <section class="checkout-details">
    Your credit card will be charged
    <strong>{{ asDollarsAndCents(orderDetails.order.amount) }}</strong>
    <br />
    (<strong>{{
      asDollarsAndCents(orderDetails.order.amount - cartStore.surcharge)
    }}</strong>
    sub total +
    <strong>{{ asDollarsAndCents(cartStore.surcharge) }}</strong> shipping)
  </section>
</template>
