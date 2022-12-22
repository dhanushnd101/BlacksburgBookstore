<script setup lang="ts">
import { defineProps } from "vue";
import { asDollarsAndCents } from "@/utils";
import { BookItem } from "@/types";
import { useCartStore } from "@/stores/CartStore";
const cartStore = useCartStore();
const props = defineProps<{
  book: BookItem;
}>();
const bookImageFileName = function (book: BookItem): string {
  let name = book.title.toLowerCase();
  name = name.replace(/ /g, "-");
  name = name.replace(/'/g, "");
  return `${name}.gif`;
};
</script>
<style scoped>
#book-boxes {
  display: flex;
  flex-wrap: wrap;
  padding: 1em;
  gap: 1em;
}

.book-box {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  width: 160px;
  background-color: var(--card-background-color);
  padding: 1em;
  gap: 0.25em;
  box-shadow: 5px 5px 5px rgba(0, 0, 0, 0.25);
  border-radius: 5px;
}

.book-box-featured {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  width: 160px;
  background-color: var(--card-background-color);
  padding: 1em;
  gap: 0.25em;
  box-shadow: 5px 5px 5px rgba(0, 0, 0, 0.25);
  border-radius: 5px;
  background-image: url("@/assets/images/books/featured.jpg");
  background-repeat: no-repeat;
}

.book-title {
  font-weight: bold;
  font-size: 20px;
  color: var(--primary-color-dark);
}

.book-author {
  margin-top: -5px;
  font-style: italic;
  font-size: 15px;
  color: #fe1fa5;
}

.book-image {
  width: 116px;
  height: 185px;
}

.book-price {
  color: #1ead1b;
}

.add-to-cart {
  padding: 7px 10px;
  background: var(--primary-color-dark);
  border-radius: 50px;
  font-family: "Roboto";
  font-size: 16px;
  color: #ffffff;
  border: none;
  width: 110px;
  margin-left: 25px;
  position: static;
  bottom: 110px;
  left: 0px;
}

.add-to-cart:hover {
  background: var(--primary-color);
  cursor: pointer;
}

.read-now-button {
  background-color: #ff6c02;
  color: white;
  transform: translateY(-1.6em);
  text-align: center;
  padding: 0.1em;
}
.read-now-button-no {
  display: none;
}

.read-now-button:hover {
  cursor: pointer;
  background-color: white;
  color: #ff6c02;
}
</style>

<template>
  <li :class="book.isFeatured === true ? 'book-box-featured' : 'book-box'">
    <div>
      <div class="book-image">
        <img
          :src="require('@/assets/images/books/' + bookImageFileName(book))"
          :alt="book.title"
        />
        <div
          :class="
            book.isPublic === true ? 'read-now-button' : 'read-now-button-no'
          "
        >
          Read Now
        </div>
      </div>
      <div class="book-title">{{ book.title }}</div>
      <div class="book-author">{{ book.author }}</div>
    </div>
    <div>
      <div class="book-price">{{ asDollarsAndCents(book.price) }}</div>
      <button class="add-to-cart" @click="cartStore.addToCart(book)">
        Add to Cart
      </button>
    </div>
  </li>
</template>
