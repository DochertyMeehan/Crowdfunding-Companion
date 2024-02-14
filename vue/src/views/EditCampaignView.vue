<template>
   <div>
    <EditCampaign :formData="formData" />
   </div>
  </template>
<script>
import CampaignService from '../services/CampaignService';
import EditCampaign from '../components/EditCampaign.vue'
export default {

  components: {
    EditCampaign
  },
  data() {
    return {
      formData: {
        username: '',
        campaignName: '',
        campaignType: '',
        description: '',
        amountGoal: 0,
      },
    };
  },
  created() {
    let id = parseInt(this.$route.params.id);
    if (id != 0) {
      CampaignService
        .getCampaign(id)
        .then(response => {
          this.formData = response.data;

        })      
        .catch(error => {
          if (error.response && error.response.status === 404) {
            this.$store.commit('SET_NOTIFICATION', `Error getting card ${id}. This card may have been deleted or you have entered an invalid card ID.`);
            this.$router.push({ name: 'home' });
          }
        });
    }
  },
//   methods: {
//     updateFormData(newFormData) {
//         this.formData = newFormData;
//     },
//   },
  
};
</script>