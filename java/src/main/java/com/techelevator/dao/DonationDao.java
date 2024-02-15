package com.techelevator.dao;

import com.techelevator.model.DonationDto;

import java.util.List;

public interface DonationDao {

    public DonationDto getDonation(int donationId);

    public void createDonation(DonationDto donationToCreate);

    public List<DonationDto> getDonationsByCampaignId(int campaignId,String username);

    public List<DonationDto> getDonationsByUserId(DonationDto donation);

    public String getUsernameByDonationId(int donationId);
    public void updateBalanceForCampaign(int campaignId, double amount);



}
