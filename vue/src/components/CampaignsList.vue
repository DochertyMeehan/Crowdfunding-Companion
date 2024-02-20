<template>
  <div class="container">
    <div>
      <h4>Welcome to Crowdfunding Companion!</h4>
      <p>The easiest way to fundraise and donate to the causes that matter most to you.</p>
    </div>
  </div>
  <div class="container">
    <div class="row">
      <div class="col-4" v-for="(campaign, index) in cards" v-bind:key="index">        
        <div class="card mb-3" v-on:click="viewCampaignDetails(campaign)">
          <div class="card-header logged-in-card">
            <i v-if="campaign.campaignType === 'Animals'" class="bi bi-bug"></i>
            <i v-else-if="campaign.campaignType === 'Business'" class="bi bi-luggage"></i>
            <i v-else-if="campaign.campaignType === 'Education'" class="bi bi-mortarboard-fill"></i>
            <i v-else-if="campaign.campaignType === 'Emergency'" class="bi bi-fire"></i>
            <i v-else-if="campaign.campaignType === 'Events'" class="bi bi-ticket-perforated"></i>
            <i v-else-if="campaign.campaignType === 'Family'" class="bi bi-people-fill"></i>
            <i v-else-if="campaign.campaignType === 'Medical'" class="bi bi-heart-pulse"></i>
            <i v-else-if="campaign.campaignType === 'Memorial'" class="bi bi-balloon-heart"></i>
            <i v-else-if="campaign.campaignType === 'Nonprofit'" class="bi bi-piggy-bank-fill"></i>
            <i v-else-if="campaign.campaignType === 'Sports'" class="bi bi-dribbble"></i>
            <i v-else-if="campaign.campaignType === 'Travel'" class="bi bi-airplane-engines"></i>
            <i v-else-if="campaign.campaignType === 'Wishes'" class="bi bi-magic"></i>
            {{ campaign.campaignType}}
          </div>
          <div class="card-body text-capitalize logged-in-card">
            <h5 class="card-title">{{ campaign.campaignName}}</h5>
            <div class="progress" role="progressbar" aria-label="Success example" :aria-valuenow="campaign.percentage" aria-valuemin="0" aria-valuemax="100">
                <div class="progress-bar bg-success" :style="{ width: campaign.percentage + '%' }"></div>
            </div>
            <p class="card-text"><i class="fa-solid fa-bullseye"></i> ${{ campaign.balance }} raised of ${{ campaign.amountGoal }}</p>
            <p class="card-text">{{ campaign.description }}</p>
          </div>
          <div class="card-footer text-body-secondary">
            <div class="row d-flex align-items-center">
              <!-- <div class="col">
                {{ campaign.username }}
              </div> -->
              <div class="col text-end">
                <router-link class="btn btn-primary" v-bind:to="{name: 'donation', params: {id: campaign.campaign_id}}">Donation</router-link>
              </div>
            </div>
            A fundraiser organized by: {{ campaign.username }}
          </div>
        </div>  
      </div>
    </div>
  </div> 
</template>

<script>
export default {
  props: ['campaigns'],
  data() {
    return {
      selectedCampaignType: '',
      cards: [],
      searchQuery: '', 
    };
  },
  methods: {
    loadData() {
      this.cards = this.campaigns
    },
    viewCampaignDetails(campaign) {
      const user = this.$store.state.user;
      if (user && Object.keys(user).length === 0 && user.constructor === Object) {
        this.$router.push({ name: 'login' });
      } else {
        this.$router.push({ name: 'SingleCampaignView', params: { id: campaign.campaign_id } });
      }
    },
  },
  computed: {
    campaignTypes() {
      const types = new Set();
      this.campaigns.forEach(campaign => {
        types.add(campaign.campaignType);
      });
      return Array.from(types);
    },
    filteredCampaigns() {
      if (!this.searchQuery) {
        return this.campaigns;
      }
      return this.campaigns.filter(campaign => {
        return campaign.campaignType === this.selectedCampaignType;
      });
    },
  },
  created() {
    this.loadData()
  }
};
</script>

<style>
.logged-in-card {
  cursor: pointer;
}

.card {  
  color: #87ae73;
  margin-bottom: 20px;
}
.container {
  color: #87ae73;
  margin-bottom: 20px;
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
select {
  padding: 8px 16px;
  border-radius: 5px;
  border: 1px solid #ccc;
}
</style>