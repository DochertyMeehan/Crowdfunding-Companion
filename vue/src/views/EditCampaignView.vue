<template>
    <div class="container">
      <h2>Edit Form</h2>
      <form @submit.prevent="submitForm">
        <div class="mb-3">
          <label for="username" class="form-label">Username</label>
          <input v-model="formData.username" type="text" class="form-control" id="username" required>
        </div>
  
        <div class="mb-3">
          <label for="campaignName" class="form-label">Campaign Name</label>
          <input v-model="formData.campaignName" type="text" class="form-control" id="campaignName" required>
        </div>
  
        <div class="mb-3">
          <label for="campaignType" class="form-label">Campaign Type</label>
          <select v-model="formData.campaignType" class="form-select" id="campaignType" required>
            <!-- Add options for campaign types here -->
          </select>
        </div>
  
        <div class="mb-3">
          <label for="description" class="form-label">Description</label>
          <textarea v-model="formData.description" class="form-control" id="description" required></textarea>
        </div>
  
        <div class="mb-3">
          <label for="amountGoal" class="form-label">Amount Goal</label>
          <input v-model="formData.amountGoal" type="number" class="form-control" id="amountGoal" required>
        </div>
  
        <button type="submit" class="btn btn-primary">Submit</button>
      </form>
    </div>
  </template>
<script>

export default {
  data() {
    return {
      formData: {
        username: '',
        campaignName: '',
        campaignType: '',
        description: '',
        amountGoal: 0,
      },
    };
  },
  created() {
    let id = parseInt(this.$route.params.id);
    if (id != 0) {
        console.log(id)
    //   boardService
    //     .getCard(cardId)
    //     .then(response => {
    //       this.card = response.data;
    //       this.isLoading = false;
    //     })
    //     .catch(error => {
    //       if (error.response && error.response.status === 404) {
    //         this.$store.commit('SET_NOTIFICATION', `Error getting card ${cardId}. This card may have been deleted or you have entered an invalid card ID.`);
    //         this.$router.push({ name: 'HomeView' });
    //       }
    //     });
    }
  }
  
};
</script>