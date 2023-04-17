<template>
  <div class="container container d-flex flex-column justify-content-center align-items-center">
    <h1>Vote System</h1>
    <div class="row">
      <div class="col d-flex justify-content-between">
        <div class="mx-2">
          <router-link to="/login">Login</router-link>
        </div>
        <div class="mx-2">
          <router-link to="/register">Register</router-link>
        </div>
      </div>
    </div>
    <router-view></router-view>
  </div>
</template>
