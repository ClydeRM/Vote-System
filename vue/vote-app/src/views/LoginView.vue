<template>
  <h1>LoginPage</h1>
  <div class="container">
    <div>
      <label for="email">Email:</label>
      <input type="text" id="email" v-model="state.email" />
    </div>
    <div>
      <label for="password">Password:</label>
      <input type="password" id="password" v-model="state.password" />
    </div>
    <div>
      <button @click="login">登入</button>
    </div>
    <router-view></router-view>
  </div>
</template>

<script>
import { reactive } from "vue";
import { store } from "../store";
import { useRouter } from "vue-router";
import axios from "axios";
export default {
  setup() {
    const state = reactive({
      email: "",
      password: "",
    });
    const router = useRouter();

    const login = async () => {
      const { email, password } = state;

      try {
        const response = await axios.post(
          "http://localhost:8080/api/v1/auth/authenticate",
          {
            email: email,
            password: password,
          }
        );

        store.commit("setAccessToken", response.data.access_token);
        store.commit("setRefreshToken", response.data.refresh_token);
        router.push("/");
      } catch (error) {
        console.error(error);
      }
    };

    return {
      state,
      login,
    };
  },
};
</script>
