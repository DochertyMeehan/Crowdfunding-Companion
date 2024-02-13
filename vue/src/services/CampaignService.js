import axios from "axios";




export default {
    createNewCampaign(campaign){
        return axios.post('/create-campaign', campaign)
    },
    editCampaign(campaign) {
        return axios.put('/edit-campaign', campaign)
    }
}