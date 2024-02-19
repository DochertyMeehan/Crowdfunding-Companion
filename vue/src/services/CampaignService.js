import axios from "axios";




export default {
    createNewCampaign(campaign){
        return axios.post('/create-campaign', campaign);
    },
    getCampaigns() {
        return axios.get('/all-campaigns');
    },
    editCampaign(campaign) {
        console.log("alo")
        console.log(campaign)
        return axios.put('/edit-campaign', campaign);
    },
    getCampaign(id) {
        console.log("this is getcampaign")
        return axios.get('/campaign', {params: { campaign_id: id }});
    },
    deleteCampaign(id){
        return axios.delete('/delete', {params: { campaign_id: id }})
    },
    getAllProposals(id) {
        return axios.get('/getProposalListByCampaignId', {params: { campaign_id: id }})
    },
    // makeProposal(id, proposal) {
    //     return axios.post('/create-proposal' + {params: { campaign_id: id }}, proposal);
    // }
    makeProposal(id, proposal) {
        console.log(`/create-proposal?campaign_id=${id}`)
        console.log(proposal)
        return axios.post(`/create-proposal?campaign_id=${id}`, proposal);
    },
    makeDonation(donation){
        return axios.post('/create-donation', donation);
    },
    getDonationForCreator(id) {
        console.log(`/get-donations-list-by-campaign-id/creator?campaign_id=${id}`)
        return axios.get(`/get-donations-list-by-campaign-id/creator?campaign_id=${id}`)
    },
    getDonationForDonor(id){
        console.log(`/get-donations-list-by-campaign-id/donor?campaign_id=${id}`)
        return axios.get(`/get-donations-list-by-campaign-id/donor?campaign_id=${id}`)
    }
}    