<template>
  <div class="container">
    <div class="row">
      <div class="col-4" v-for="(campaign, index) in campaigns" v-bind:key="index">        
        <div class="card"  v-on:click="viewCampaignDetails(campaign)">
          <div class="card-header">
            {{ campaign.campaignType}}
          </div>
          <div class="card-body">
            <h5 class="card-title">{{ campaign.campaignName}}</h5>
            <p class="card-text">{{ campaign.amountGoal }}</p>
            <p class="card-text">{{ campaign.balance }}</p>
            <p class="card-text">{{ campaign.description }}</p>
            <a href="#" class="btn btn-primary">Go somewhere</a>
          </div>
          <div class="card-footer text-body-secondary">
            {{ campaign.username }}
          </div>
        </div>  
      </div>
    </div>
  </div>    
</template>

<script>
export default {
  name: 'all-campaigns',
  props: ['campaigns'],
  methods: {
    viewCampaignDetails(campaign) {
      this.$router.push({ name: 'SingleCampaignView', params: { id: campaign.campaign_id } });
    }
  }
};
</script>

<style>
.card {
  cursor: pointer;
}
</style>