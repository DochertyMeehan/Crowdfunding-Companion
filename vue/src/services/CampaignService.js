import axios from "axios";




export default {
    createNewCampaign(campaign){
        return axios.post('/create-campaign', campaign);
    },
    getCampaigns() {
        return axios.get('/all-campaigns');
    }
}