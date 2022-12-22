<script setup lang="ts">
import { defineProps } from "vue";
import { BookItem } from "@/types";
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
  width: 160px;
  background-color: var(--card-background-color);
  padding: 1em;
  gap: 0.25em;
  box-shadow: 5px 5px 5px rgba(0, 0, 0, 0.25);
  border-radius: 5px;
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
  /* margin-top: -5px; */
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
  /*margin-left: 25px;*/
  position: absolute;
  left: 0;
}

.add-to-cart:hover {
  background: var(--primary-color);
  cursor: pointer;
}

.read-now-button {
  background-color: rgba(0, 0, 0, 0.5);
  color: white;
  transform: translateY(-1.6em);
  margin-bottom: -1.6em;
  text-align: center;
  padding: 0.1em;
}

.read-now-button:hover {
  cursor: pointer;
}
</style>

<template>
  <li class="book-box">
    <div class="book-image">
      <img
        :src="require('@/assets/images/books/' + bookImageFileName(book))"
        :alt="book.title"
      />
      <div class="read-now-button">Read Now</div>
    </div>
    <div class="book-title">{{ book.title }}</div>
    <div class="book-author">{{ book.author }}</div>
    <div class="book-price">${{ (book.price / 100).toFixed(2) }}</div>
    <button class="add-to-cart">Add to Cart</button>
  </li>
</template>
