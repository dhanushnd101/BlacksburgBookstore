<template>
  <div class="checkout-view">Hear your check out page</div>
  <check-out-table></check-out-table>
</template>

<script>
import CheckOutTable from "@/components/CheckOutTable.vue";
export default {
  name: "CheckOutView",
  components: { CheckOutTable },
};
</script>

<style scoped></style>
