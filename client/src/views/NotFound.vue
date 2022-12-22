<script setup lang="ts"></script>

<style scoped>
h1 {
  color: var(--primary-color-dark);
}

.image-empty-cart {
  display: flex;
  width: 600px;
}
.empty-cart {
  margin-top: 50px;
  display: block;
  text-align: center;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
</style>

<template>
  <div class="empty-cart">
    <div>
      <h1>Your content was not found</h1>
      <p>There appears to be nothing to find here.</p>
    </div>

    <img
      class="image-empty-cart"
      :src="require('@/assets/images/site/error.png')"
    />
  </div>
</template>
