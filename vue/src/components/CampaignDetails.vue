<template>
    <div class="campaign-details">
      <h2>{{ campaign.campaignName }}</h2>
      <p>Campaign Manager: {{ campaign.username }}</p>
      <p>Campaign Type: {{ campaign.campaignType }}</p>
      <p>What are we raising money for? <br>
        {{campaign.description }}</p>
      <p>Goal: ${{ campaign.amountGoal }}</p>
      <p>Balance: ${{ campaign.balance }}</p>
      <router-link class="btn btn-primary" v-bind:to="{name: 'donation'}">donate</router-link>
      <router-link v-bind:to="{ name: 'EditCampaign', params: { id: $route.params.campaign_id } }" class="btn btn-submit">Edit
      Campaign</router-link>
    <button class="btn btn-cancel" v-on:click="removeCampaign(id)">Delete Campaign</button>
    </div>
    <br>
    <div class="container">
        <button><router-link class="nav-link" v-bind:to="{ name: 'ProposalList' }">See all the proposals</router-link></button>
        </div>
    <br>
    <div>
        <h4>Donation List:</h4>
        <ul class="list-group list-group-flush" v-for="(donation, index) in donations" v-bind:key="index">
            <li class="list-group-item">{{ index }}. {{ donation.username }} donated: {{ donation.amount }}$ for this campaign</li>
        </ul>
    </div>
  </template>
  
<script>
import CampaignService from '../services/CampaignService';
import ProposalsView from '../views/ProposalsView.vue';

  export default {
    components: {
    },
    data() {
        return {
            selectedCampaign: '',
            donations:{
            },
        };
    },
    props: ['campaign'],
    methods: {
        removeCampaign() {
            if (confirm('Are you sure you want to delete this card? This action cannot be undone.')) {
                CampaignService.deleteCampaign(this.campaign.campaign_id).then(resp => {
                    if (resp.status === 204)
                        console.log(resp.status);
                    {
                        console.log("haha");
                        this.$store.commit('SET_NOTIFICATION', {
                            message: 'Campaign was successfully deleted.',
                            type: 'Success'
                        });
                        this.$router.push({ name: 'home' });
                    }
                }).catch(err => {
                    if (err.response) {
                        this.$store.commit('SET_NOTIFICATION', `Error deleting campaign. Response received was "${err.response.statusText}".`);
                    }
                    else if (err.request) {
                        this.$store.commit('SET_NOTIFICATION', 'Error deleting campaign. Server could not be reached.');
                    }
                    else {
                        this.$store.commit('SET_NOTIFICATION', 'Error deleting campaign. Request could not be created.');
                    }
                });
            }
        },
        loadData(){
            console.log("hello")
            console.log(this.campaign)
            CampaignService.getDonationForCreator(this.$route.params.id).then(res => {
                console.log(res.status)
                this.donations = res.data
                console.log(this.donation)
            });

        },

    },
    created() {
        this.selectedCampaign = this.$route.params.campaign_id;
        this.loadData();
    },
   
};
  </script>
  <style>
.campaign-details {
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid navy;
    border-radius: 5px;
    background-color: #f9f9f9;
    box-shadow: 0 2px 5px rgba(0, 0, 0, .1);
    color: #87ae73;
}
.campaign-details h2 {
    margin-top: 0;
}
.campaign-details p {
    margin: 10px 0;
}
.btn {
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    text-decoration: none;
    background-color: #87ae73;
    color: white;
    border-color: navy;
}
.btn:hover {
  background-color: white;
  color: #87ae73;
  border-color: navy;
}
</style>
  