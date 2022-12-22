import { createApp } from "vue";
import "@/assets/css/global.css"; // imports the global CSS before all other CSS files
import App from "./App.vue";
import router from "./router";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import { library } from "@fortawesome/fontawesome-svg-core";
import { fas } from "@fortawesome/free-solid-svg-icons";
import { fab } from "@fortawesome/free-brands-svg-icons";
library.add(fas, fab);

createApp(App).component("fa", FontAwesomeIcon).use(router).mount("#app");
