<template>
    <div>
        <div>
            <CampaignsList :campaigns="campaignData"/>
        </div>       
    </div>
</template>
<script>

import CampaignsList from '../components/CampaignsList.vue';
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
            console.log("This is loadData in CampaignView")
            CampaignService.getCampaigns().then(resp => {                
                // this.campaignData = resp.data;                
                let campaigns = resp.data
                campaigns.forEach(campaign => {
                    let percentage = (campaign.balance / campaign.amountGoal) * 100;

                    let data = {
                        campaign_id: campaign.campaign_id,
                        username: campaign.username,
                        description: campaign.description,
                        amountGoal: campaign.amountGoal,
                        balance: campaign.balance,
                        campaignName: campaign.campaignName,
                        campaignType: campaign.campaignType,
                        percentage: percentage,
                    };

                    this.campaignData.push(data);
                });

                this.$store.commit('SET_CAMPAIGNS', this.campaignData);
                console.log(this.campaignData)
            });
            
        }
    },
    created() {
        this.loadData();
    }
}
</script>