<script setup lang="ts">
import { provide } from "vue";
import { CategoryItem } from "@/types";
import AppHeader from "@/components/AppHeader.vue";
import AppFooter from "@/components/AppFooter.vue";
const categoryList: CategoryItem[] = [
  { categoryId: 1001, name: "Best Selling" },
  { categoryId: 1002, name: "Mystery" },
  { categoryId: 1003, name: "Fiction" },
  { categoryId: 1004, name: "Novels" },
  { categoryId: 1005, name: "Romance" },
  { categoryId: 1006, name: "Fantasy" },
  { categoryId: 1007, name: "Horror" },
  { categoryId: 1008, name: "KIDS" },
];

provide("categoryList", categoryList);
</script>

<style></style>

<template>
  <div id="app">
    <app-header></app-header>
    <router-view></router-view>
    <app-footer></app-footer>
  </div>
</template>
