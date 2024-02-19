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
            console.log("this is id:" ,this.$route.params.id )
            CampaignService.getCampaign(this.$route.params.id).then(res => {
                console.log("res data")
                console.log(res.data)
                this.campaignData = res.data;
            });
    
        }
    },
    created() {
        // check if user logged in
        const user = this.$store.state.user;
        if (user && Object.keys(user).length === 0 && user.constructor === Object) {
            this.$router.push({ name: 'login'});
        }

        this.loadData();
        console.log("alooo")
        console.log(this.campaignData)
    }
}
</script>
<style>

</style>