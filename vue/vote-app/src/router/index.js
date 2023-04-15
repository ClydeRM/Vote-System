import {
  createRouter,
  createWebHistory,
} from "vue-router";
import Home from "../views/HomeView";
import Login from "../views/LoginView";
import Register from "../views/RegisterView";
import Voting from "../views/VoteView";

const routes = [
  {
    path: "/",
    component: Home,
  },
  {
    path: "/login",
    component: Login,
  },
  {
    path: "/register",
    component: Register,
  },
  {
    path: "/voting",
    component: Voting,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
