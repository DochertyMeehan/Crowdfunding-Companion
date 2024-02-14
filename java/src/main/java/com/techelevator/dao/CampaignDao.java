package com.techelevator.dao;

import com.techelevator.model.CampaignDto;

import java.util.List;

public interface CampaignDao {

    public List<CampaignDto> getCampaigns();
    public CampaignDto getCampaign(int campaignId);
    public CampaignDto createCampaign(CampaignDto campaignToCreate);
    public void editCampaign(CampaignDto campaignToEdit, String username);
    public void deleteCampaign(String name,int campaignId);

}
