import axios from "axios";




export default {
    createNewCampaign(campaign){
        return axios.post('/create-campaign', campaign);
    },
    getCampaigns() {
        return axios.get('/all-campaigns');
    },
    editCampaign(campaign) {
        return axios.put('/edit-campaign', campaign);
    },
    getCampaign(id) {
        return axios.get('/campaign', {params: { campaign_id: id }});
    },
    deleteCampaign(id){
        return axios.delete('/delete', {params: { campaign_id: id }})
    },
    getAllProposals(id) {
        return axios.get('/getProposalListByCampaignId', {params: { campaign_id: id }})
    },
    makeProposal(proposal) {
        return axios.post('/create-proposal', proposal);
    }
}    