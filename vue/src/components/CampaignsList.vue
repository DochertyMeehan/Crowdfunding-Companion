<template>
  <div class="container">
    <div>
    <div>
      <h4>About us</h4>
      <p>Under the sapphire canopy of the evening sky, the city came alive with a kaleidoscope of colors, as skyscrapers stretched their shimmering fingers towards the heavens. The neon signs that adorned bustling avenues flickered like distant stars, casting a vibrant glow upon the bustling streets below. A symphony of honks and distant chatter intermingled with the rhythmic footsteps of pedestrians, creating a lively urban tapestry. In the midst of this kinetic energy, an old bookstore stood with weathered charm, its shelves laden with tales from worlds unknown. A street artist, inspired by the city's heartbeat, wielded a paintbrush with fervor, leaving behind strokes of imagination on a once mundane wall. As the night unfurled, a food market erupted in a mosaic of scents, tempting passersby with the savory promises of diverse cuisines. In a hidden courtyard, an impromptu gathering of musicians conjured melodies that danced through the air, inviting anyone with a listening ear to join their nocturnal serenade. The city, a living organism, pulsed with life, its narrative woven from the threads of countless stories that played out under the moonlit sky.</p>
    </div>
  </div>
  </div>
  <div class="container">
    <div class="row">
      <div class="col-4" v-for="(campaign, index) in campaigns" v-bind:key="index">        
        <div class="card"  v-on:click="viewCampaignDetails(campaign)">
          <div class="card-header">
            {{ campaign.campaignType}}
          </div>
          <div class="card-body">
            <h5 class="card-title">{{ campaign.campaignName}}</h5>
            <p class="card-text">{{ campaign.amountGoal }}</p>
            <p class="card-text">{{ campaign.balance }}</p>
            <p class="card-text">{{ campaign.description }}</p>
            <a href="#" class="btn btn-primary">Donate for this Campaign</a>
          </div>
          <div class="card-footer text-body-secondary">
            {{ campaign.username }}
          </div>
        </div>  
      </div>
    </div>
  </div>    
</template>

<script>
export default {
  name: 'all-campaigns',
  props: ['campaigns'],
  methods: {
    viewCampaignDetails(campaign) {
      this.$router.push({ name: 'SingleCampaignView', params: { id: campaign.campaign_id } });
    },
  },
};
</script>

<style>
.card {
  cursor: pointer;
}
</style>