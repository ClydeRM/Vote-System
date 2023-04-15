import { createStore } from "vuex";

export const store = createStore({
  state: {
    accessToken:
      localStorage.getItem("access_token") || "",
    refreshToken:
      localStorage.getItem("refresh_token") || "",
  },
  mutations: {
    setAccessToken(state, token) {
      state.accessToken = token;
      localStorage.setItem("access_token", token);
    },
    setRefreshToken(state, token) {
      state.refreshToken = token;
      localStorage.setItem(
        "refresh_token",
        token
      );
    },
  },
});

