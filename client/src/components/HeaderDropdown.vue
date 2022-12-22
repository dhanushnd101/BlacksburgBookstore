<script setup lang="ts">
import { useCategoryStore } from "@/stores/CategoryStore";
const categoryStore = useCategoryStore();
</script>

<style scoped>
.header-dropdown {
  position: relative;
}

.categories-button {
  /* background-color: var(--primary-color-dark); */
  margin-top: 0.25em;
}

.header-dropdown ul {
  background-color: var(--neutral-color);
  color: white;
  display: none;
  border-radius: 5px;
}

li a {
  display: block;
}

.header-dropdown li {
  padding: 0.25em 0.5em;
}

.header-dropdown a {
  color: white;
  text-decoration: none;
}

.header-dropdown li:hover {
  background: var(--primary-color-dark);
  border-radius: 5px;
}

.header-dropdown:hover ul {
  display: block;
  position: absolute;
  min-width: 7.5em;
  z-index: 1;
  cursor: pointer;
  box-shadow: 0px 0px 7px rgba(0, 0, 0, 0.25);
}
</style>

<template>
  <div class="header-dropdown">
    <button class="button categories-button">
      <i class="fas fa-chevron-down"></i>
      Categories
    </button>
    <ul>
      <li
        v-for="category in categoryStore.categoryList"
        :key="category.categoryId"
      >
        <router-link :to="'../category/' + category.name">
          {{ category.name }}
        </router-link>
      </li>
    </ul>
  </div>
</template>
