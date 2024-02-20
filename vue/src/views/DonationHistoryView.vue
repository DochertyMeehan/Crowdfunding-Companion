<template>
    <div>

    </div>
</template>

<script>
import CampaignService from '../services/CampaignService';

export default {
    data(){
        return {
            donationList: [],
        }
    },
    methods: {
        loadData() {
            console.log("hello")
            CampaignService.getDonationHistory()

        }
    },
    created() {
        this.loadData();
    }
}
</script>