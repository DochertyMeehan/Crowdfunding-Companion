<template>
    <div>
      <h2>{{ campaign.username }}</h2>
      <p>{{campaign.description }}</p>
      <router-link v-bind:to="{ name: 'EditCampaign', params: { id: $route.params.campaign_id } }" class="btn btn-submit">Edit
      Campaign</router-link>
    <button class="btn btn-cancel" v-on:click="removeCampaign(id)">Delete Card</button>
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
      removeCampaign() {
        if (
          confirm(
            'Are you sure you want to delete this card? This action cannot be undone.'
          )
        ) {
          CampaignService.deleteCampaign(this.campaign.campaign_id).then(resp => {
            if (resp.status === 204) 
            console.log(resp.status)
            {
              console.log("haha")
              this.$store.commit('SET_NOTIFICATION', 
              {
                message: 'Campaign was successfully deleted.',
                type: 'Success'
              }
            );
            this.$router.push({ name: 'home' });

            }
          }).catch (err => {
            if (err.response) {
              this.$store.commit('SET_NOTIFICATION',
                `Error deleting card. Response received was "${err.response.statusText}".`);
            } else if (err.request) {
              this.$store.commit('SET_NOTIFICATION', 'Error deleting card. Server could not be reached.');
            } else {
              this.$store.commit('SET_NOTIFICATION', 'Error deleting card. Request could not be created.');
            }
          })
        }        
      }
    },
    created() {
      this.selectedCampaign = this.$route.params.campaign_id;
    },
  };
  </script>
  