<template>
    <div>
        <h4>Proposal List</h4>
            <ul>
                <li v-for="proposal in proposals" :key="proposal.id">
                    {{ proposal.description }} |
                    {{ proposal.proposal_status }} |
                    {{ proposal.vote_passed }}
                </li>   
            </ul> 
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
            CampaignService.getAllProposals().then(res => {
                this.proposals = res.data;
            });
    
        }
    },
    created() {
        this.loadData();
    }
}
</script>