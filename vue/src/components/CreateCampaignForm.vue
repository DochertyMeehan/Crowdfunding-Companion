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
                                    Username:
                                </div>
                                <div class="col-10">
                                    <!-- <input class="form-control" type="text" placeholder="Username" aria-label="Username" v-model="formData.username">
                                    {{ formData.username }} -->
                                    {{ $store.state.user.username }}
                                </div>
                            </div>
                            <div class="row align-items-center mb-3">
                                <div class="col-2">
                                    Campaign
                                </div>
                                <div class="col-10">
                                    <input class="form-control" type="text" placeholder="Campaign" aria-label="Campaign Name" v-model="formData.campaignName">
                                </div>
                                <div class="col-10">
                                     Campaign Image<input class="form-control" type="text" placeholder="Campaign Image Link" aria-label="Campaign Image" v-model="formData.campaignImage">
                                </div>
                            </div>
                            <div class="row align-items-center mb-3">
                                <div class="col-2">
                                    Type of Campaign
                                </div>
                                <div class="col-10">
                                    <select class="form-control" id="campaignType" name="campaignType" v-model="formData.campaignType">
                                                        <option value="Animals">Animals</option>
                                                        <option value="Business">Business</option>
                                                        <option value="Education">Education</option>
                                                        <option value="Emergency">Emergency</option>
                                                        <option value="Events">Events</option>
                                                        <option value="Family">Family</option>
                                                        <option value="Medical">Medical</option>
                                                        <option value="Memorial">Memorial</option>
                                                        <option value="Nonprofit">Nonprofit</option>
                                                        <option value="Sports">Sports</option>
                                                        <option value="Travel">Travel</option>
                                                        <option value="Wishes">Wishes</option>
                                    </select>                                
                                </div>
                            </div>
                            <div class="row align-items-center mb-3">
                                <div class="col-2">
                                    Description
                                </div>
                                <div class="col-10">
                                    <textarea class="form-control" type="text" placeholder="Description" aria-label="Description" v-model="formData.description"> </textarea>
                                </div>
                            </div>
                            <div class="row align-items-center mb-3">
                                <div class="col-2">
                                    Goal $
                                </div>
                                <div class="col-10">
                                    <input class="form-control" type="text" placeholder="Fundraising Goal $" aria-label="Goal Amount" v-model.number="formData.amountGoal">
                                </div>
                            </div>
                            <div class="row justify-content-center mb-3">
                                <div class="col-3 d-grid">
                                    <button type="submit" class="btn btn-primary">Create</button>
                                </div>
                                <div class="col-3 d-grid">
                                    <button type="submit" class="btn btn-primary" @click="goToCampaignList">Cancel</button>
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
        username: null,
        campaignName: '',
        campaignImage: '',
        campaignType: '',
        description: '',
        amountGoal: null,
      },
    };
  },
  mounted() {
    this.formData.username = this.$store.state.user.username;
  },
  methods: {
    goToCampaignList(){
        this.$router.push({name: 'home'});
    },
    submitForm(){
        campaignService.createNewCampaign(this.formData).then(resp => {
            if (resp.status === 201){
                this.$store.commit('SET_NOTIFICATION', {
                    message: 'A new Campaign was added.',
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