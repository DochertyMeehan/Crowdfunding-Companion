<template>
    <div class="container">
        <form @submit.prevent="submitForm">
            <div>
                <span>
                    <h1 class="fs-1">Create Campaign</h1>
                    <hr>
                    <div class="row d-flex justify-content-center">
                        <div class="col-6">
                            <div class="row align-items-center mb-3">
                                <div class="col-2">
                                    Username
                                </div>
                                <div class="col-10">
                                    <input class="form-control" type="text" placeholder="Username" aria-label="Username" v-model="formData.username">
                                </div>
                            </div>
                            <div class="row align-items-center mb-3">
                                <div class="col-2">
                                    Campaign
                                </div>
                                <div class="col-10">
                                    <input class="form-control" type="text" placeholder="Campaign" aria-label="Campaign Name" v-model="formData.campaignName">
                                </div>
                            </div>
                            <div class="row align-items-center mb-3">
                                <div class="col-2">
                                    Type
                                </div>
                                <div class="col-10">
                                    <input class="form-control" type="text" placeholder="Type" aria-label="Campaign Type" v-model="formData.campaignType">
                                </div>
                            </div>
                            <div class="row align-items-center mb-3">
                                <div class="col-2">
                                    Description
                                </div>
                                <div class="col-10">
                                    <input class="form-control" type="text" placeholder="Description" aria-label="Description" v-model="formData.description">
                                </div>
                            </div>
                            <div class="row align-items-center mb-3">
                                <div class="col-2">
                                    Goal
                                </div>
                                <div class="col-10">
                                    <input class="form-control" type="text" placeholder="" aria-label="Goal Amount" v-model.number="formData.amountGoal">
                                </div>
                            </div>
                            <div class="row justify-content-center mb-3">
                                <div class="col-6 d-grid">
                                    <button type="submit" class="btn btn-primary">Create</button>
                                </div>
                                
                            </div>
                        </div>
                        
                    </div>
                    
                </span>
            </div>
        </form>
    </div>
    
</template>

<script>
import campaignService from '../services/CampaignService';

export default {
  data() {
    return {
      formData: {
        username: '',
        campaignName: '',
        campaignType: '',
        description: '',
        amountGoal: null,
      },
    };
  },
  methods: {
    submitForm(){
        campaignService.createNewCampaign(this.formData).then(resp => {
            if (resp.status === 201){
                this.$store.commit('SET_NOTIFICATION', {
                    message: 'A new card was added.',
                    type: 'success'
                }
                );
                this.$router.push({name: 'home'});
            }
        }).catch(err => {
            this.handleErrorResponse(err, 'adding');
        })

    },
    handleErrorResponse(error, verb) {
      if (error.response) {
        this.$store.commit('SET_NOTIFICATION',
          "Error " + verb + " card. Response received was '" + error.response.statusText + "'.");
      } else if (error.request) {
        this.$store.commit('SET_NOTIFICATION', "Error " + verb + " card. Server could not be reached.");
      } else {
        this.$store.commit('SET_NOTIFICATION', "Error " + verb + " card. Request could not be created.");
      }
    },
  }  
};
</script>