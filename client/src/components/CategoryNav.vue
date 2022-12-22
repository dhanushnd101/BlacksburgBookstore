<script setup lang="ts">
import { inject } from "vue";
import { useRoute } from "vue-router";
const route = useRoute();
const categoryList = inject("categoryList");
</script>

<style scoped>
.category-buttons {
  display: flex;
  flex-direction: row;
  text-align: center;
  background-color: var(--neutral-color);
}

.button.selected-category-button {
  background-color: darkblue;
  border-radius: 0;
}

.button.unselected-category-button,
.button.unselected-category-button:visited {
  background-color: var(--neutral-color);
}

.button.unselected-category-button:hover,
.button.unselected-category-button:active {
  background-color: var(--primary-color);
  border-radius: 0;
}
</style>

<template>
  <nav class="category-nav">
    <ul class="category-buttons">
      <li v-for="category in categoryList" :key="category.categoryId">
        <router-link
          :to="{ name: 'category-view', params: { name: category.name } }"
          class="button"
          :class="
            category.name === route.params.name
              ? 'selected-category-button'
              : 'unselected-category-button'
          "
        >
          {{ category.name }}
        </router-link>
      </li>
    </ul>
  </nav>
</template>
