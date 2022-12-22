<script setup lang="ts">
import { reactive, Ref } from "vue";
import useVuelidate from "@vuelidate/core";
import {
  email,
  helpers,
  maxLength,
  minLength,
  required,
} from "@vuelidate/validators";
import { useCartStore } from "@/stores/CartStore";
import {
  isMobilePhone,
  asDollarsAndCents,
  isCreditCard,
  isEmail,
} from "@/utils";
import CheckoutFieldError from "@/components/CheckoutFieldError.vue";
const cartStore = useCartStore();
const cart = cartStore.cart;
import router from "@/router";
import { useCategoryStore } from "@/stores/CategoryStore";
const categoryStore = useCategoryStore();

const months: string[] = [
  "January",
  "February",
  "March",
  "April",
  "May",
  "June",
  "July",
  "August",
  "September",
  "October",
  "November",
  "December",
];

const form = reactive({
  name: "",
  address: "",
  phone: "",
  email: "",
  ccNumber: "",
  ccExpiryMonth: new Date().getMonth() + 1,
  ccExpiryYear: new Date().getFullYear(),
  checkoutStatus: "",
});

const rules = {
  name: {
    required: helpers.withMessage("Please provide a name", required),
    minLength: helpers.withMessage(
      "Name must have at least 4 letters",
      minLength(4)
    ),
    maxLength: helpers.withMessage(
      "Name can have at most 45 letters",
      maxLength(45)
    ),
  },
  address: {
    required: helpers.withMessage("Please provide an address", required),
    minLength: helpers.withMessage(
      "Address must have at least 4 letters",
      minLength(4)
    ),
    maxLength: helpers.withMessage(
      "Address can have at most 45 letters",
      maxLength(45)
    ),
  },
  phone: {
    required: helpers.withMessage("Please provide a phone number", required),
    minLength: helpers.withMessage(
      "Please provide a valid phone number",
      (value: string) => !helpers.req(value) || isMobilePhone(value)
    ),
  },
  ccNumber: {
    required: helpers.withMessage(
      "Please provide a credit card number",
      required
    ),
    isCreditCard: helpers.withMessage(
      "Please provide a valid credit card number",
      (value: string) => !helpers.req(value) || isCreditCard(value)
    ),
  },
  email: {
    required: helpers.withMessage("Please provide a email", required),
    isEmail: helpers.withMessage(
      "Please provide a valid email.",
      (value: string) => !helpers.req(value) || isEmail(value)
    ),
  },
  ccExpiryMonth: {},
  ccExpiryYear: {},
};
const $v = useVuelidate(rules, form);

async function submitOrder() {
  console.log("Submit order");
  const isFormCorrect = await $v.value.$validate();
  if (!isFormCorrect) {
    form.checkoutStatus = "ERROR";
  } else {
    form.checkoutStatus = "PENDING";
    setTimeout(() => {
      form.checkoutStatus = "OK";
      setTimeout(() => {
        router.push({ name: "confirmation-view" });
      }, 2000);
    }, 1000);
  }
}

/* NOTE: For example yearFrom(0) == <current_year> */
function yearFrom(index: number) {
  return new Date().getFullYear() + index;
}
</script>

<style scoped>
.checkout-page {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.checkout-page-body {
  display: flex;
  flex-direction: column;
  align-items: center;
  /*justify-content: center;*/
  padding: 1em;
  color: #0f4069;
  width: 500px;
  box-shadow: 0px 0px 3px 1px cornflowerblue;
  background: #edf7fd;
  border-radius: 20px;
  margin-bottom: 20px;
}

form {
  display: flex;
  flex-direction: column;
}

form > div {
  display: flex;
  justify-content: flex-end;
  margin-bottom: 0.5em;
}

form > div > input,
form > div > select {
  background-color: white;
  margin-left: 0.5em;
  color: #06145f;
  border: 2px solid cornflowerblue;
}
form > div > input {
  width: 195px;
}

form > .error {
  color: red;
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

.checkoutStatusBox {
  margin: 1em;
  padding: 1em;
  text-align: center;
}

.checkout-button {
  align-items: center;
  border-radius: 20px;
  margin: 30px 10px 10px 60px;
  width: fit-content;
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

  <div v-else class="checkout-page">
    <section class="checkout-page-body">
      <form @submit.prevent="submitOrder">
        <div>
          <label for="name">Name</label>
          <input
            type="text"
            size="20"
            id="name"
            name="name"
            v-model.lazy="$v.name.$model"
          />
        </div>
        <checkout-field-error field-name="name" :validator="$v" />

        <div>
          <label for="address">Address</label>
          <input
            type="text"
            size="20"
            id="address"
            name="address"
            v-model.lazy="$v.address.$model"
          />
        </div>
        <checkout-field-error field-name="address" :validator="$v" />

        <div>
          <label for="phone">Phone</label>
          <input
            class="textField"
            type="text"
            size="20"
            id="phone"
            name="phone"
            v-model.lazy="$v.phone.$model"
          />
        </div>
        <checkout-field-error field-name="phone" :validator="$v" />

        <div>
          <label for="email">Email</label>
          <input
            type="text"
            size="20"
            id="email"
            name="email"
            v-model.lazy="$v.email.$model"
          />
        </div>
        <checkout-field-error field-name="email" :validator="$v" />

        <div>
          <label for="ccNumber">Credit card</label>
          <input
            type="text"
            size="20"
            id="ccNumber"
            name="ccNumber"
            v-model.lazy="$v.ccNumber.$model"
          />
        </div>
        <checkout-field-error field-name="ccNumber" :validator="$v" />

        <div>
          <label>Expiry Date</label>
          <select v-model="$v.ccExpiryMonth.$model">
            <option
              v-for="(month, index) in months"
              :key="index"
              :value="index + 1"
            >
              {{ month }} ({{ index + 1 }})
            </option>
          </select>
          <select v-model="$v.ccExpiryYear.$model">
            <option v-for="index in 15" :key="index">
              {{ index + 2021 }}
            </option>
          </select>
        </div>
        <input
          type="submit"
          name="submit"
          class="button checkout-button"
          :disabled="form.checkoutStatus === 'PENDING'"
          value="Complete Purchase"
          @submit.prevent="submitOrder"
        />
      </form>

      <section class="checkout-details">
        Your credit card will be charged
        <strong>{{ asDollarsAndCents(cart.subtotal + cart.surcharge) }}</strong>
        <br />
        (<strong>{{ asDollarsAndCents(cart.subtotal) }}</strong> sub total +
        <strong>{{ asDollarsAndCents(cart.surcharge) }}</strong> shipping)
      </section>

      <section v-show="form.checkoutStatus !== ''" class="checkoutStatusBox">
        <div v-if="form.checkoutStatus === 'ERROR'" style="color: red">
          Error: Please fix the problems above and try again.
        </div>

        <div
          v-else-if="form.checkoutStatus === 'PENDING'"
          style="color: #2295f4"
        >
          Processing...
        </div>

        <div v-else-if="form.checkoutStatus === 'OK'" style="color: #1ead1b">
          Order placed...
        </div>

        <div v-else style="color: red">
          An unexpected error occurred, please try again.
        </div>
      </section>
    </section>
  </div>
</template>
