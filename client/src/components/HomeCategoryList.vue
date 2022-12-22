<script setup lang="ts">
import { inject } from "vue";
import { CategoryItem } from "@/types";
const categoryList = inject("categoryList");
const categoryImageFileName = function (category: CategoryItem): string {
  let name = category.name.toLowerCase();
  name = name.replace(/ /g, "-");
  name = name.replace(/'/g, "");
  return `${name}.jpg`;
};
</script>

<style scoped>
ul {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  flex-wrap: wrap;
  gap: 1em;
}

li {
  text-align: center;
  cursor: pointer;
}

li div {
  padding: 0.5em 0;
  background: rgba(0, 0, 0, 0.5); /* last # is percent opacity */
  color: white;
  transform: translateY(-2.25em);
  margin-bottom: -2em;
}
.category-buttons {
  display: flex;
  flex-direction: row;
  text-align: center;
  background-color: var(--neutral-color);
}

.button.selected-category-button {
  background-color: var(--primary-color);
  border-radius: 0px;
}

.button.unselected-category-button,
.button.unselected-category-button:visited {
  background-color: var(--neutral-color);
  border-radius: 0px;
}

.button.unselected-category-button:hover,
.button.unselected-category-button:active {
  background-color: var(--primary-color);
  border-radius: 0px;
}

.category-image-items {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  flex-wrap: wrap;
  gap: 1em;
}

.category-image-items a {
  text-align: center;
  cursor: pointer;
}

.category-image-items div {
  padding: 0.5em 0;
  background: rgba(0, 0, 0, 0.5); /* last # is percent opacity */
  color: white;
  transform: translateY(-2.25em);
  margin-bottom: -2em;
}
</style>

<template>
  <ul class="category-images container">
    <li
      v-for="category in categoryList"
      :key="category.categoryId"
      class="category-image-items"
    >
      <router-link :to="'../category/' + category.name">
        <img
          :src="
            require('@/assets/images/categories/' +
              categoryImageFileName(category))
          "
          :alt="category.name + 'category'"
          :width="180"
        />
        <div>{{ category.name }}</div>
      </router-link>
    </li>
  </ul>
</template>
