<template>
  <div>
    <h3>Edit Campaign</h3>

    <button v-on:click.prevent="deleteCampaign">Delete?</button>
    
    <form v-on:submit.prevent="editCampaign">
    Campaign Id: {{ currentCampaign }} <br>
    Campaign Manager: <input type="text" v-model="currentCampaign.username"> <br>
    Campaign Name: <input type="text" v-model="currentCampaign.name"> <br>
    Campaign Type: <input type="text" v-model="currentCampaign.type"> <br>
    Campaign Description: <input type="text" v-model="currentCampaign.description"> <br>
    Goal: <input type="text" v-model="currentCampaign.goal"> <br>
    <button>Update</button>

    </form>
  </div>
</template>

<script>
import CampaignService from '../services/CampaignService'
export default {
    created() {
        CampaignService.getCampaign(this.$route.params.id).then(
            (response) => {
                this.currentCampaign = response.data;
            }
        );
    },
    methods: {
        updateCampaign() {
            CampaignService.updateCampaign(this.currentCampaign).then(
                (response) => {
                    if(response.status === 200) {
                        window.alert("Campaign update!");
                        this.$router.push({name: "home"});
                    }
                }
            ).catch(
                (error) => {
                    if(error.response) {
                        this.errorMsg = "There was a problem.";
                    }
                }
            );
        },
        deleteCampaign() {
            CampaignService.deleteCampaign(this.currentCampaign.id).then(
                (response) => {
                    if(response.status === 200) {
                        window.alert("Campaign deleted.");
                        this.$router.push({name: "home"});
                    }
                }
            );
        }
    },
    data() {
        return {
            currentCampaign: {},
            errorMsg: ""
        }
    }

}
</script>

<style>

</style>