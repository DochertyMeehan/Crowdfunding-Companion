<template>
    <div>
        <div>
            <CampaignDetails v-bind:campaign="campaignData"/>
        </div>
    </div>
</template>
<script>

import CampaignService from '../services/CampaignService';
import CampaignDetails from '../components/CampaignDetails.vue' 
export default {
    data(){
        return {
            campaignData:[]
        }
    },
    components: {
        CampaignDetails
    },
    methods: {
        loadData() {
            CampaignService.getCampaign(this.$route.params.id).then(res => {
                this.campaignData = res.data;
            });
    
        }
    },
    created() {
        this.loadData();
    }
}
</script>