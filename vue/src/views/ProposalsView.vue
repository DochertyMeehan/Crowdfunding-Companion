<template>
    <router-link v-bind:to="{ name: 'SingleCampaignView' }">Go back </router-link>
    <div class="row">
      <div class="col-md-4" v-for="proposal in proposals" :key="proposal.id">
        <div class="card mb-3">
          <div class="card-body">
            <h5 class="card-title">{{ proposal.proposalName }}</h5>
            <p class="card-text">{{ proposal.description }}</p>
            <p class="card-text"><strong>Status:</strong> {{ proposal.status }}</p>
          </div>
        </div>
      </div>
    </div>
    <button><router-link class="nav-link" v-bind:to="{ name: 'CreateProposal' }">Make a new Proposal</router-link></button>
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