<script setup lang="ts">
import HeaderDropdown from "@/components/HeaderDropdown.vue";
import { useCartStore } from "@/stores/CartStore";
const cartStore = useCartStore();
</script>

<style scoped>
header {
  background: var(--secondary-background-color);
  display: flex;
  justify-content: space-between;
  padding: 1em;
  gap: 1em;
}

header > * {
  flex-basis: content;
  align-items: center;
}

@media (max-width: 600px) {
  header {
    flex-direction: column;
  }
}

.bookstore-logo {
  text-align: left;
}

.header-dropdown-and-cart {
  align-items: center;
}
.primary-buttons {
  font-size: x-large;
  text-decoration: none;
}

form {
  color: rgb(66, 37, 255);
  display: flex;
  padding: 2px;
  padding-left: 10px;
  border: solid var(--primary-color-dark);
  border-radius: 20px;
  height: fit-content;
}

.search-bar {
  background: transparent;
  margin: 0;
  padding-left: 10px;
  font-size: 17px;
  color: inherit;
  border: none;
}

.search-bar:focus {
  outline: none;
}

.search-button {
  overflow: hidden;
  width: 30px;
  padding: 0;
  margin: 0;
  border: none;
  border-radius: inherit;
  cursor: pointer;
  opacity: 0.7;
  background-color: transparent;
}

.button-sign-in {
  background: #2dd62a;
  border: 1px solid #2295f4;
}

.button-sign-in:hover {
  background: #30bb2e;
}

.add-to-cart {
  padding: 1px 1px;
  font-family: "Roboto";
  font-size: 30px;
  color: var(--primary-color);
  border: none;
}

.add-to-cart:hover {
  color: var(--primary-color-dark);
  cursor: pointer;
}
.add-to-cart-number {
  font-size: 17px;
  background: red;
  color: white;
  border-radius: 50%;
  padding: 7px;
  position: relative;
  left: -15px;
  top: -15px;
}
</style>

<template>
  <header class="container header-dropdown-and-cart">
    <section class="bookstore-logo">
      <router-link to="/">
        <img
          src="@/assets/images/site/BlacksburgBooks.png"
          alt="Another Bookstore Logo"
          width="160"
        />
      </router-link>
    </section>
    <router-link to="/" class="primary-buttons"> Home </router-link>
    <!--    <router-link to="/category/Best%20Selling" class="primary-buttons">-->
    <!--      New Arrivals-->
    <!--    </router-link>-->
    <router-link to="/category/Best%20Selling" class="primary-buttons">
      Best Sellers
    </router-link>
    <form action="CategoryBookList.vue">
      <div class="search">
        <input class="search-bar" type="search" placeholder="search books" />
        <router-link to="/category/Best%20Selling">
          <button class="search-button" type="submit" value="Search">
            <i class="fas fa-search"></i>
          </button>
        </router-link>
      </div>
    </form>
    <header-dropdown></header-dropdown>
    <div class="add-to-cart">
      <i class="fas fa-shopping-cart">
        <span class="add-to-cart-number">{{ cartStore.count }}</span></i
      >
    </div>
    <div>
      <button class="button button-sign-in">
        <i class="fa-solid fa-user"></i> DND
      </button>
    </div>
  </header>
</template>
