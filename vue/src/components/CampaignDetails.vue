<template>
    <div>
      <h2>{{ campaign.username }}</h2>
      <p>{{campaign.description }}</p>
      <router-link v-bind:to="{ name: 'EditCampaign', params: { id: $route.params.campaign_id } }" class="btn btn-submit">Edit
      Campaign</router-link>
    <button class="btn btn-cancel" v-on:click="removeCampaign()">Delete Card</button>
    </div>
  </template>
  
  <script>
  import CampaignService from '../services/CampaignService';
  export default {
    data() {
      return {
        selectedCampaign: '',
      };
    },
    props: ['campaign'],

    methods: {
      removeCampaign(id) {
        if (
          confirm(
            'Are you sure you want to delete this card? This action cannot be undone.'
          )
        ) {
          
          CampaignService.deleteCampaign(id).then(resp => {
            if (resp.status === 200) {
              this
            }
          })
          this.$router.push({ name: 'home' });
        }        
      }
    },
    created() {
      this.selectedCampaign = this.$route.params.campaign_id;
    },
  };
  </script>
  