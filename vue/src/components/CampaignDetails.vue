<template>
    <div>
      <h2>Campaign Details</h2>
      <p>{{ selectedCampaign }}</p>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        selectedCampaign: '',
      };
    },
    created() {
      this.selectedCampaign = this.$route.params.campaign_id;
    },
  };
  </script>
  