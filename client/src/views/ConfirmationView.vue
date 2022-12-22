<script setup lang="ts">
import ConfirmationTable from "@/components/ConfirmationTable";

import { useCategoryStore } from "@/stores/CategoryStore";
import { useOrderDetailsStore } from "@/stores/OrderDetailsStore";
import { computed } from "vue";

const categoryStore = useCategoryStore();

const orderDetailsStore = useOrderDetailsStore();
const orderDetails = orderDetailsStore.orderDetails;

const orderDate = computed(function () {
  let date = new Date(orderDetails.order.dateCreated);
  return date.toDateString() + " " + date.toLocaleTimeString();
});

const ccExpDate = computed(function (): Date {
  return new Date(orderDetails.customer.ccExpDate);
});

const ccExpYear = computed(function (): number {
  let date = new Date(orderDetails.customer.ccExpDate);
  return date.getFullYear();
});

const ccExpMonth = computed(function (): string {
  let date = new Date(orderDetails.customer.ccExpDate);
  let month = date.getMonth() + 1;
  //TODO try to fix this

  let result = "" + month;
  if (month < 10) {
    result = "0" + month;
  }
  return result;
});

const ccProtected = computed(function (): string {
  let ccProtectedString = "#### #### #### ";
  let ccNumber = orderDetails.customer.ccNumber;
  return ccProtectedString + ccNumber.substring(12);
});
</script>

<style scoped>
#confirmationView {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin: 1em auto;
}

ul {
  text-align: left;
}

ul > li {
  margin: 0.25em;
}

h1,
h2 {
  margin: 1em auto;
  text-align: center;
}

h2 {
  color: var(--primary-color-dark);
}

h3 {
  margin-bottom: -20px;
}

#noDetails {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin-bottom: 50px;
}

.goToHome {
  width: 200px;
  align-items: center;
  align-content: center;
  text-align: center;
}

.confirmation-page {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.confirmation-page-body {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 1em;
  color: #0f4069;
  width: 500px;
  box-shadow: 0px 0px 3px 1px cornflowerblue;
  background: #edf7fd;
  border-radius: 20px;
  margin-bottom: 20px;
}

.image-done {
  width: 70px;
  margin-top: 10px;
}
</style>

<template>
  <h1>Confirmation Page</h1>

  <div id="noDetails" v-if="!orderDetailsStore.hasOrderDetails()">
    <h2>We are sorry, the order you requested could not be found!</h2>
    <router-link class="button goToHome" :to="{ name: 'home-view' }"
      >Go to Home Page
    </router-link>
  </div>

  <div class="confirmation-page" v-else>
    <div class="confirmation-page-body">
      <img class="image-done" :src="require('@/assets/images/site/done.png')" />
      <br />
      <h3>Hey {{ orderDetails.customer.customerName }},</h3>
      <h1>Your order is complete!</h1>
      <p>Thank your for your purchase</p>
      <p>Confirmation No. {{ orderDetails.order.confirmationNumber }}</p>
      <p>{{ orderDate }}</p>

      <br />
      <br />
      <p>Books information:</p>
      <confirmation-table></confirmation-table>

      <br />
      <p>Customer information:</p>
      <ul>
        <li>{{ orderDetails.customer.customerName }}</li>
        <li>{{ orderDetails.customer.address }}</li>
        <li>{{ orderDetails.customer.email }}</li>
        <li>{{ orderDetails.customer.phone }}</li>
        <!--        <li>VISA</li>-->
        <li>{{ ccProtected }} ({{ ccExpMonth }}-{{ ccExpYear }})</li>
      </ul>
      <br />

      <router-link class="button goToHome" :to="{ name: 'home-view' }"
        >Go to Home Page
      </router-link>
    </div>
  </div>
</template>
