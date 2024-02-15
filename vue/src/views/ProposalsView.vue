<template>
    <router-link v-bind:to="{ name: 'SingleCampaignView' }">Go back </router-link>
    <div class="container">
        <div>
            <ul>
                <li v-for="proposal in proposals" :key="proposal.id">
                    {{ proposal.description }} |
                    {{ proposal.proposal_status }} |
                    {{ proposal.vote_passed }}
                </li>   
            </ul> 
            <button><router-link class="nav-link" v-bind:to="{ name: 'CreateProposal' }">Make a new Proposal</router-link></button>
        </div>
    </div>
</template>
<script>
import CampaignService from '../services/CampaignService';

export default {
    data() {
        return {
            proposals: [],
        }
    },
    methods: {
        loadData() {
            CampaignService.getAllProposals(this.$route.params.id).then(res => {
                this.proposals = res.data;
            });
    
        }
    },
    created() {
        this.loadData();
    }
}
</script>