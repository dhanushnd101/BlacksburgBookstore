import { defineStore } from "pinia";
import { ShoppingCart } from "@/models/ShoppingCart";
import { BookItem } from "@/types";

export const useCartStore = defineStore("CartStore", {
  state: () => ({
    cart: new ShoppingCart(),
  }),

  getters: {
    count(): number {
      return this.cart.numberOfItems;
    },
  },

  actions: {
    clearCart() {
      this.cart.clear();
    },
    addToCart(book: BookItem) {
      this.cart.addBook(book);
    },
    updateBookQuantity(book: BookItem, quantity: number) {
      this.cart.update(book, quantity);
    },
  },
});
