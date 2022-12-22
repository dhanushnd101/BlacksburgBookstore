<script setup lang="ts">
import HomeCategoryList from "@/components/HomeCategoryList.vue";
</script>

<style scoped>
.home-page {
  display: flex;
  flex-direction: column;
}
.welcome-text {
  font-size: 45px;
  margin-top: 20px;
}
.welcome-text-body {
  font-size: 23px;
  max-width: 750px;
}
.shop-me-flex {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.shop-me-button {
  background: #2295f4;
  box-shadow: 5px 10px 10px rgba(0, 0, 0, 0.25);
  border-radius: 10px;
  width: 190px;
  height: 60px;
  font-family: "Roboto";
  font-style: normal;
  font-weight: 600;
  font-size: 26px;
  color: #ffffff;
  border: none;
  transition-duration: 500ms;
  transform: scale(1);
}
.shop-me-button:hover {
  text-align: center;

  background: #0f4069;
  cursor: pointer;
  transform: scale(1.5);
}
</style>

<template>
  <div class="home-page">
    <section class="flow-content container">
      <h1 class="welcome-text">
        MEET YOUR NEXT
        <span style="color: var(--primary-color-dark)"> BOOK</span>
      </h1>
      <p class="welcome-text-body">
        The best books of all-time, best sellers, contemporary books organized
        by category based on many hours of reading and research. You can be sure
        that each one is fantastic, interesting and will be worth your time.
      </p>
      <section class="shop-me-flex">
        <section
          style="
            text-align: center;
            position: relative;
            left: 100px;
            top: 100px;
          "
        >
          <router-link
            to="/category/Best%20Selling"
            class="button shop-me-button"
          >
            SHOP NOW
          </router-link>
        </section>
        <section>
          <img
            align="right"
            :src="
              require('@/assets/images/site/toppng.com-children-books-png-1756x692.png')
            "
            :width="730"
          />
        </section>
      </section>
    </section>
    <section class="category-images container">
      <home-category-list></home-category-list>
    </section>
  </div>
</template>
