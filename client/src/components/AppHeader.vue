<script setup lang="ts">
import HeaderDropdown from "@/components/HeaderDropdown.vue";
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

.search-bar button:hover {
  background: #ccc;
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

input[type="search"] {
  border: none;
  background: transparent;
  margin: 0;
  padding-left: 10px;
  font-size: 17px;
  color: inherit;
  border: 1px;
  border: 1px solid transparent;
  border-radius: inherit;
}

button[type="submit"] {
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

button[type="submit"]:hover {
  opacity: 1;
}

.button-sign-in {
  /*margin-left: 10px;*/
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
}
.add-to-cart-number {
  font-size: 17px;
  background: red;
  color: white;
  border-radius: 55px;
  padding: 3px;
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
          width="150"
        />
      </router-link>
    </section>
    <router-link to="/" class="primary-buttons"> Home </router-link>
    <router-link to="/category/Best%20Selling" class="primary-buttons">
      New Arrivals
    </router-link>
    <router-link to="/category/Best%20Selling" class="primary-buttons">
      Best Sellers
    </router-link>
    <form action="CategoryBookList.vue">
      <router-link to="/category/Best%20Selling">
        <div>
          <input type="search" placeholder="search books" name="search" />
          <button type="submit" value="Search">
            <fa :icon="['fas', 'search']" />
          </button>
        </div>
      </router-link>
    </form>
    <header-dropdown></header-dropdown>
    <div class="add-to-cart">
      <p>
        <fa :icon="['fas', 'shopping-cart']" />
        <span class="add-to-cart-number">11</span>
      </p>
    </div>
    <div>
      <button class="button button-sign-in">
        <fa :icon="['fas', 'fa-user']" /> DND
      </button>
    </div>
  </header>
</template>
