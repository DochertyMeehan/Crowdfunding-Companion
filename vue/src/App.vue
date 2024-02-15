<template>
  <div id="capstone-app">
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
      <div class="container">
        <a class="navbar-brand" href="#">Crowdfunding</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-between" id="navbarNavDropdown">
          <ul class="navbar-nav">
            <li class="nav-item">
              <router-link class="nav-link" v-bind:to="{ name: 'home' }">Home</router-link>
            </li>
            <li class="nav-item">
              <router-link class="nav-link" v-bind:to="{name:'campaignCreate'}">Create Campaign</router-link>
            </li>
            <li class="nav-item">
              <router-link class="nav-link" v-bind:to="{name: 'FAQ'}">FAQ</router-link>
            </li>                       
          </ul>
          <ul class="navbar-nav">
            <li class="nav-item">
              <router-link class="nav-link" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
            </li>
          </ul>
          <ul class="navbar-nav">
            <li class="nav-item">
              <router-link class="nav-link" v-bind:to="{ name: 'login' }" v-if="$store.state.token != ''">LogIn</router-link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <router-view />
  </div>
</template>
<script>
import CampaignView from './views/CampaignView.vue';
import FAQview from './views/FAQview.vue';
</script>
