<template>
  <h1>Checkout Page</h1>
  <check-out-table></check-out-table>
</template>

<script>
import CheckOutTable from "@/components/CheckOutTable.vue";
export default {
  name: "CheckOutView",
  components: { CheckOutTable },
};
</script>

<style scoped>
h1 {
  margin: 1em auto;
  text-align: center;
}
</style>
