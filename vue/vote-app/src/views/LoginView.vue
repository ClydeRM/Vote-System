<template>
  <h1>LoginPage</h1>
  <div class="container container d-flex flex-column justify-content-center align-items-center">
    <table class="table">
      <tbody>
        <tr>
          <td><label for="email">Email:</label></td>
          <td><input type="text" id="email" v-model="state.email"></td>
        </tr>
        <tr>
          <td><label for="password">Password:</label></td>
          <td><input type="password" id="password" placeholder="6-12" v-model="state.password"></td>
        </tr>
      </tbody>
    </table>
    <div>
      <button @click="login">登入</button>
    </div>
  </div>
</template>

<script>
import { reactive } from "vue";
import { store } from "../store";
import { useRouter } from "vue-router";
import axios from "axios";
import { checkValue } from "../common/CheckValue";
export default {
  setup() {
    const state = reactive({
      email: "",
      password: "",
    });
    const router = useRouter();

    const login = async () => {
      const { email, password } = state;
      const validEmail = checkValue(email);
      const validPassword = checkValue(password);
      try {
        const response = await axios.post(
          "http://localhost:8080/api/v1/auth/authenticate",
          {
            email: validEmail,
            password: validPassword,
          }
        );

        store.commit("setAccessToken", response.data.access_token);
        store.commit("setRefreshToken", response.data.refresh_token);
        await router.push("/dashboard");
      } catch (error) {
        console.error(error);
        throw error;
      }
    };

    return {
      state,
      login,
    };
  },
};
</script>
