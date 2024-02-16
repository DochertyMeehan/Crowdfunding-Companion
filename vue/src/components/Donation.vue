<template>
 <div>
    <h2>Amount Form</h2>
    <form @submit.prevent="submitForm">
      <label for="amount">Amount:</label>
      <input
        type="number"
        id="amount"
        v-model="amount"
        placeholder="Enter amount"
        required
      />

      <button type="submit">Submit</button>
    </form>

    <div v-if="submitted">
      <p>Amount submitted: {{ amount }}</p>
    </div>
  </div>
</template>
<script>
import CampaignService from '../services/CampaignService';
export default {
    data() {
        return {
            donation: {
                campaign_id: null,
                user_id: null,
                amount: null,
            },
            amount: null,
            submitted: false,
        };
    },
  methods: {
    submitForm() {
        this.donation.amount = this.amount;
        CampaignService.makeDonation(this.donation).then(res => {

        })
        this.submitted = true;
    }
  },
  mounted() {
    this.donation.campaign_id    = this.$route.params.id;
    this.donation.user_id        = this.$store.state.user.id;
  },
};
</script>