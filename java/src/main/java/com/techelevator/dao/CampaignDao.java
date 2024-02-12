package com.techelevator.dao;

import com.techelevator.model.CampaignDto;

import java.util.List;

public interface CampaignDao {

    public List<CampaignDto> getCampaigns();
    public CampaignDto getCampaign(int campaignId);
    public CampaignDto createCampaign(CampaignDto campaignToCreate);
    public CampaignDto editCampaign(CampaignDto campaignToEdit);
    public void deleteCampaign(int campaignId);

}
