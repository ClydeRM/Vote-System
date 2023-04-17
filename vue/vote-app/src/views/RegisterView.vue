<template>
  <h1>RegisterPage</h1>
  <div class="container">
    <div>
      <label for="username">Username:</label>
      <input type="text" id="username" v-model="state.username" />
    </div>
    <div>
      <label for="email">Email:</label>
      <input type="text" id="email" v-model="state.email" />
    </div>
    <div>
      <label for="password">Password:</label>
      <input type="password" id="password" v-model="state.password" />
    </div>
    <div>
      <button @click="register">註冊</button>
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
      username: "",
      email: "",
      password: "",
    });
    const router = useRouter();

    const register = async () => {
      const { username, email, password } = state;

      try {
        const response = await axios.post(
          "http://localhost:8080/api/v1/auth/register",
          {
            username: username,
            email: email,
            password: password,
          }
        );

        store.commit("setAccessToken", response.data.access_token);
        store.commit("setRefreshToken", response.data.refresh_token);

        router.push("/dashboard");
      } catch (error) {
        console.error(error);
      }
    };

    return {
      state,
      register,
    };
  },
};
</script>
