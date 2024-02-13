<template>
  <div class="home">
    <h1>Home</h1>
    <div class="container">
        <ul>
            <li v-for="item in apiData" :key="item.campaign_id">{{ item }}</li>
        </ul>
    </div>

  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      apiData: [],
    };
  },
  mounted() {
    // Call your API when the component is mounted
    this.fetchApiData();
  },
  methods: {
    async fetchApiData() {
      try {
        // Replace 'your-api-endpoint' with the actual API endpoint
        const response = await axios.get('/all-campaigns');
        this.apiData = response.data;

        console.log(this.apiData)
      } catch (error) {
        console.error('Error fetching API data:', error);
      }
    },
  },
};
</script>
