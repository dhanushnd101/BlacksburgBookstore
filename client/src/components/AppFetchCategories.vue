<script setup lang="ts">
import { provide } from "vue";
import { CategoryItem } from "@/types";
import AppHeader from "@/components/AppHeader.vue";
import AppFooter from "@/components/AppFooter.vue";

const url =
  "https://cs5244.cs.vt.edu:8443/DhanushBookstoreFetch/api/categories/";
const categoryList: CategoryItem[] = await fetch(url).then((response) =>
  response.json()
);

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
