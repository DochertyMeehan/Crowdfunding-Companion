<template>
    <router-link class="nav-link" v-bind:to="{ name: 'ProposalList' }">Go back</router-link>
    <div class="container">
        <form @submit.prevent="submitProposal">
      <div class="mb-3">
        <label for="proposalName" class="form-label">Proposal Name</label>
        <input v-model="proposalName" type="text" class="form-control" id="proposalName" required>
      </div>

      <div class="mb-3">
        <label for="votePassed" class="form-label">Vote Passed</label>
        <input v-model="votePassed" type="text" class="form-control" id="votePassed" required>
      </div>

      <div class="mb-3">
        <label for="description" class="form-label">Description</label>
        <textarea v-model="description" class="form-control" id="description" rows="3" required></textarea>
      </div>

      <div class="mb-3">
        <label for="proposalStatus" class="form-label">Proposal Status</label>
        <select v-model="proposalStatus" class="form-select" id="proposalStatus" required>
          <option value="pending">Pending</option>
          <option value="approved">Approved</option>
          <option value="rejected">Rejected</option>
        </select>
      </div>

      <button type="submit" class="btn btn-primary">Submit Proposal</button>
    </form>
  </div>
</template>
<script>
import CampaignService from '../services/CampaignService';


export default {
    components: {
        
    },
}
</script>