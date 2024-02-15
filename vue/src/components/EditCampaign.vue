<template>
        <div class="container">
            <div class="container">
                    <h2>Edit Form</h2>
                    <form @submit.prevent="submitForm">
                        <div class="mb-3">
                        <label for="campaign_id" class="form-label">Campaign ID</label>
                        <!-- <input v-model="currentCampaign.campaign_id" type="text" class="form-control" id="campaign_id"  required> -->
                        {{ editCampaign.campaign_id }}
                        </div>
                        <div class="mb-3">
                        <label for="username" class="form-label">Creator</label>
                        <!-- <input v-model="currentCampaign.username" type="text" class="form-control" id="username"  required> -->
                        {{ editCampaign.username }}
                        </div>
                
                        <div class="mb-3">
                        <label for="campaignName" class="form-label">Campaign Name</label>
                        <input v-model="currentCampaign.campaignName" type="text" class="form-control" id="campaignName" required>
                        </div>
                
                        <div class="mb-3">
                        <label for="campaignType" class="form-label">Campaign Type</label>
                        <select v-model="currentCampaign.campaignType" class="form-select" id="campaignType" required>
                                                        <option value="option1">Animals</option>
                                                        <option value="option2">Business</option>
                                                        <option value="option3">Education</option>
                                                        <option value="option4">Emergency</option>
                                                        <option value="option5">Events</option>
                                                        <option value="option6">Family</option>
                                                        <option value="option7">Medical</option>
                                                        <option value="option8">Memorial</option>
                                                        <option value="option9">Nonprofit</option>
                                                        <option value="option10">Sports</option>
                                                        <option value="option11">Travel</option>
                                                        <option value="option12">Wishes</option>

                        </select>
                        </div>
                
                        <div class="mb-3">
                        <label for="description" class="form-label">Description</label>
                        <textarea v-model="editCampaign.description" class="form-control" id="description" required></textarea>
                        </div>
                
                        <div class="mb-3">
                        <label for="amountGoal" class="form-label">Amount Goal</label>
                        <input v-model="editCampaign.amountGoal" type="number" class="form-control" id="amountGoal" required>
                        </div>
                
                        <button type="submit" class="btn btn-primary">Submit</button> 
                        <button  class="btn btn-primary" v-on:click="cancelForm" type="button">Cancel</button>
                    </form>
        </div>
    </div>
</template>
<script>
import CampaignService from '../services/CampaignService';
export default {
  props: {
    formData: {
      type: Object,
      required: true
    },
  },

  data() {
    return {  
        editCampaign: {
            campaign_id: this.formData.campaign_id,
            username: this.formData.username,
            campaignName: this.formData.campaignName,
            campaignType: this.formData.campaignType,
            description: this.formData.description,
            amountGoal: this.formData.amountGoal,
            balance: this.formData.balance
        },
    };
  },

  methods: {
    submitForm() {
        console.log('Submitting form:', this.editCampaign);
        CampaignService.editCampaign(this.currentCampaign)
        .then(resp => {
            console.log('Response from service:', resp);
            console.log('Updated data from server:', resp.data);

            if (resp.status === 200) {
                this.$store.commit(
                    'SET_NOTIFICATION',
                    {
                        message: `Campaign ${this.currentCampaign.id} was updated.`,
                        type: 'success'
                    }
                );
                this.$router.push({name: 'home'});
            }
        })
        .catch(error => {
            this.handleErrorResponse(error, 'updating');
        })
    },
    cancelForm(){
        this.$router.push({name: 'home'})
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
  },
};
</script>