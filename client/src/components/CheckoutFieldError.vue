<script setup lang="ts">
import { defineProps, Ref } from "vue";
import { Validation } from "@vuelidate/core";

const props = defineProps<{
  fieldName: string;
  validator: Ref<Validation>;
}>();
</script>

<style scoped>
form > .error {
  color: orangered;
  text-align: right;
  margin: -10px 0px 10px 0px;
  font-size: small;
  font-style: italic;
}
</style>

<template>
  <template v-if="validator[fieldName].$error">
    <span
      class="error"
      v-for="error of validator[fieldName].$errors"
      :key="error.$uid"
      >{{ error.$message }}
    </span>
  </template>
</template>
