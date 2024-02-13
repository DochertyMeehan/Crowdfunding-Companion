<template>
    <div class="container">
        <ul>
            <li v-for="campaign in campaigns" :key="campaign.id">{{ campaign.name }}</li>
        </ul>
    </div>
    
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      campaigns: [],
    };
  },
  mounted() {
 
  },
  methods: {

  },
};
</script>