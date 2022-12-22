<script setup lang="ts">
import { provide } from "vue";
import { BookItem } from "@/types";
import CategoryNav from "@/components/CategoryNav.vue";
import CategoryBookList from "@/components/CategoryBookList.vue";
const bookList: BookItem[] = [
  {
    bookId: 1001,
    title: "Ugly Love",
    author: "Colleen Hoover",
    price: 1049,
    isPublic: true,
  },
  {
    bookId: 1002,
    title: "Beach Read",
    author: "Emily Henry",
    price: 599,
    isPublic: false,
  },
  {
    bookId: 1003,
    title: "The Silent Patient",
    author: "Alex Michaelides",
    price: 1235,
    isPublic: true,
  },
  {
    bookId: 1004,
    title: "This we never got over",
    author: "Lucy Score",
    price: 627,
    isPublic: true,
  },
];
</script>

<style scoped></style>

<template>
  <div>
    <category-nav></category-nav>
    <category-book-list :bookList="bookList"> </category-book-list>
  </div>
</template>
