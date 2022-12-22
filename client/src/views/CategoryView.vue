<script setup lang="ts">
import CategoryFetchBooks from "../components/CategoryFetchBooks.vue";
</script>

<style scoped></style>

<template>
  <Suspense>
    <CategoryFetchBooks></CategoryFetchBooks>
  </Suspense>
</template>
