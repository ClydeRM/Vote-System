<template>
  <h1>RegisterPage</h1>
  <div
    class="container container d-flex flex-column justify-content-center align-items-center"
  >
    <table>
      <tbody>
        <tr>
          <td><label for="username">Username:</label></td>
          <td><input type="text" id="username" v-model="state.username" /></td>
        </tr>
        <tr>
          <td><label for="email">Email:</label></td>
          <td><input type="text" id="email" v-model="state.email" /></td>
        </tr>
        <tr>
          <td><label for="password">Password:</label></td>
          <td>
            <input type="password" id="password" v-model="state.password" />
          </td>
        </tr>
      </tbody>
    </table>
    <div>
      <button @click="register">註冊</button>
    </div>
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

      const passwordRegex = /^[a-zA-Z0-9]+$/;
      const emailRegex = /^[^\s@~!#$%&*|?]+@[^\s@~!#$%&*|?]+\.[^\s@~!#$%&*|?]+$/;
      const usernameRegex = /^[^\s@~!#$%&*|?-\\+]+$/;

      try {
        if (!emailRegex.test(email)) {
          throw new Error("Credentials are not valid.");
        }
        if (!passwordRegex.test(password)) {
          throw new Error("Credentials are not valid.");
        }
        if (!usernameRegex.test(username)) {
          throw new Error("Credentials are not valid.");
        }
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

        await router.push("/dashboard");
      } catch (error) {
        console.error(error);
        throw error;
      }
    };

    return {
      state,
      register,
    };
  },
};
</script>
