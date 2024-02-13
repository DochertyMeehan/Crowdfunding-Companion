<template>
    <div class="container" v-for="(campaign, index) in campaigns" v-bind:key="index">
      <div>
        <div>
          {{ campaign.username }}
        </div>
        <div>
          {{ campaign.campaignName}}
        </div>
        <div>
          {{ campaign.campaignType }}
        </div>
        <div>
          {{ campaign.description }}
        </div>
        <div>
          {{ campaign.amountGoal }}
        </div>
        <div>
          {{ campaign.balance }}
        </div>
      </div>
    </div>
    
</template>

<script>

export default {
  name: 'all-campaigns',
  props: ['campaigns'],
  methods: {
    toggleShowDetails(campaign){
      this.$store.commit('TOGGLE_SHOW_DETAILS', campaign); 
    }
  }
};
</script>