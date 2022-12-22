import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import HomeView from "../views/HomeView.vue";
import CategoryView from "@/views/CategoryView.vue";
import CheckoutView from "@/views/CheckOutView.vue";
import CartView from "@/views/CartView.vue";
import ConfirmationView from "@/views/ConfirmationView.vue";
const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "home-view",
    component: HomeView,
  },
  {
    path: "/category/:name",
    name: "category-view",
    component: CategoryView,
    props: true,
  },
  {
    path: "/cart",
    name: "cart-view",
    component: CartView,
  },
  {
    path: "/checkout",
    name: "check-out-view",
    component: CheckoutView,
  },
  {
    path: "/confirmation",
    name: "confirmation-view",
    component: ConfirmationView,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
