<script setup lang="ts">
import { watch } from "vue";
import CategoryNav from "@/components/CategoryNav.vue";
import CategoryBookList from "@/components/CategoryBookList.vue";
import { useRoute } from "vue-router";
import { useBookStore } from "@/stores/BookStore";
import { useCartStore } from "@/stores/CartStore";
import { useCategoryStore } from "@/stores/CategoryStore";
import router from "@/router";
const categoryStore = useCategoryStore();
const route = useRoute();
const bookStore = useBookStore();
watch(
  () => route.params.name,
  (newName) => {
    categoryStore.setSelectedCategoryName(newName as string);
    bookStore
      .fetchBooks(newName as string)
      .catch(() => router.push("/not-found"));
  },
  { immediate: true }
);
</script>

<style scoped></style>

<template>
  <div>
    <category-nav></category-nav>
    <category-book-list> </category-book-list>
  </div>
</template>
