<template>
  <div class="container">
    <div>
    <div>
      <h4>Welcome to Crowdfunding Companion!</h4>
      <p>The easiest way to fundraise and donate to the causes that matter most to you.</p>
    </div>
  </div>
  </div>
  <div class="container">
    <div class="row">
      <div class="col-4" v-for="(campaign, index) in campaigns" v-bind:key="index">        
        <div class="card mb-3">
          <div class="card-header" v-on:click="viewCampaignDetails(campaign)">
            {{ campaign.campaignType}}
          </div>
          <div class="card-body"  v-on:click="viewCampaignDetails(campaign)">
            <h5 class="card-title">{{ campaign.campaignName}}</h5>
            <br>
            <p class="card-text">Goal: ${{ campaign.amountGoal }}</p>
            <p class="card-text">Current Balance: ${{ campaign.balance }}</p>
            <p class="card-text">{{ campaign.description }}</p>
            <!-- <a href="#" class="btn btn-primary">Donate</a> -->            
          </div>
          <div class="card-footer text-body-secondary">
            <div class="row d-flex align-items-center">
              <div class="col">
                {{ campaign.username }}
              </div>
              <div class="col text-end">
                <router-link class="btn btn-primary" v-bind:to="{name: 'donation', params: {id: campaign.campaign_id}}">Donation</router-link>
              </div>
            </div>
          </div>
        </div>  
      </div>
    </div>
  </div> 
</template>

<script>
export default {
  components: {
  
  },
  name: 'all-campaigns',
  props: ['campaigns'],
  methods: {
    viewCampaignDetails(campaign) {
      this.$router.push({ name: 'SingleCampaignView', params: { id: campaign.campaign_id } });
    },
  },
};
</script>

<style>
.card {
  cursor: pointer;
  color: #87ae73;
}
.container {
  color: #87ae73;
}
.btn {
  background-color: #87ae73;
  color: white;
  border-color: navy;
}
.btn:hover {
  background-color: white;
  color: #87ae73;
}
</style>