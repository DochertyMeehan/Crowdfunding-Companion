<template>
    <div>
        <div>
            <campaigns-list />
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
        campaign(){
            return this.$store.state.campaigns;
        }
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