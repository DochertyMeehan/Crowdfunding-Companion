<template>
    <div>
        <div>
            <CampaignsList v-bind:campaigns="campaignData"/>
        </div>
    </div>
</template>
<script>

import CampaignsList from '../views/CampaignsList.vue';
import CampaignService from '../services/CampaignService';
export default {
    data(){
        return {
            campaignData:[]
        }
    },
    components: {
        CampaignsList
    },
    computed: {
        campaigns(){
            return this.$store.state.campaigns;
        },
    },

    methods: {
        loadData() {
            CampaignService.getCampaigns().then(resp => {                
                this.campaignData = resp.data;
                this.$store.commit('SET_CAMPAIGNS', this.campaignData);
            });
        }
    },
    created() {
        this.loadData();
    }
}
</script>