import {
  createRouter,
  createWebHistory,
} from "vue-router";
import Home from "../views/HomeView";
import Login from "../views/LoginView";
import Register from "../views/RegisterView";
import Voting from "../views/VoteView";
import Dashboard from "../views/DashboardView";

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
  {
    path: "/dashboard",
    component: Dashboard,
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
