package com.techelevator.dao;

import com.techelevator.model.DonationDto;

import java.util.List;

public interface DonationDao {

    public DonationDto getDonation(int donationId);

    public void createDonation(DonationDto donationToCreate);

    public List<DonationDto> getDonationsByCampaignId(int userId,String username);

    public List<DonationDto> getDonationsByUserId(DonationDto donotation);

    public String getUsernameByDonationId(int donationId);
    public void updateBalanceForCampaign(int campaignId, double amount);



}
