<script setup lang="ts"></script>

<style scoped>
footer {
  background: var(--secondary-background-color);
  display: flex;
  justify-content: space-between;
  gap: 1em;
}
.footer-footer {
  background: var(--primary-color-dark);
  display: flex;
  justify-content: space-around;
  gap: 1em;
  color: white;
}

.links,
.social-media-icons {
  display: flex;
  flex-direction: column;
  line-height: 1.4;
}

.social-media-icons-style {
  font-style: normal;
  font-weight: 400;
  font-size: 32px;

  color: #06145f;
}

.v7 {
  color: #333;
  font-family: Font Awesome 5 Brands;
  font-weight: normal;
  font-size: 32px;
  opacity: 1;
}
</style>

<template>
  <footer class="container">
    <section class="links" style="padding-left: 100px; font-size: 23px">
      <router-link to="/" class="a"> About </router-link>
      <router-link to="/" class="a"> Contact Us </router-link>
      <router-link to="/" class="a"> Gift Cards </router-link>
      <router-link to="/" class="a"> Website </router-link>
    </section>
    <section style="text-align: center; padding-right: 200px">
      <p style="color: var(--primary-color-dark); font-size: 30px">
        NEWS LETTER
      </p>
      <p style="color: #2c49ef; font-size: 18px">
        Be the first to know about sales and whats new!
      </p>
      <input
        type="text"
        id="fname"
        name="fname"
        placeholder="john@example.com"
        style="border-color: #2c49ef; border-radius: 5px; width: 330px"
      />
      <p class="social-media-icons-style">
        <fa :icon="['fab', 'facebook']" />
        <fa :icon="['fab', 'youtube']" />
        <fa :icon="['fab', 'instagram']" />
        <fa :icon="['fab', 'linkedin']" />
        <fa :icon="['fab', 'twitch']" />
        <fa :icon="['fab', 'snapchat-ghost']" />
        <fa :icon="['fab', 'discord']" />
        <fa :icon="['fab', 'github']" />
        <fa :icon="['fab', 'facebook']" />
      </p>
    </section>
  </footer>
  <footer class="footer-footer">
    <p><fa :icon="['fas', 'heart']" /> from team DND</p>
    <p><fa :icon="['fas', 'copyright']" /> 2022 All rights reserved</p>
    <p>Privacy Policy | Terms & conditios</p>
  </footer>
</template>
