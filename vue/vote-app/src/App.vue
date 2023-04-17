<template>
  <div class="container container d-flex flex-column justify-content-center align-items-center">
    <h1>Vote System</h1>
    <div>
      <router-link to="/login">Login</router-link>
    </div>
    <div>
      <router-link to="/register">Register</router-link>
    </div>
    <router-view></router-view>
  </div>
</template>
