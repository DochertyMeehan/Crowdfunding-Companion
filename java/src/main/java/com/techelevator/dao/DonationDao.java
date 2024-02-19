package com.techelevator.dao;

import com.techelevator.model.DonationDto;
import com.techelevator.model.DonorUserDto;

import java.util.List;

public interface DonationDao {

    public DonationDto getDonation(int donationId);

    public String createDonation(DonationDto donationToCreate);

    public List<DonorUserDto> getDonationsByCampaignIdForCreator(int campaign_id, String username);

    public List<DonationDto> getDonationsByUserId(DonationDto donotation);

    public String getUsernameByDonationId(int donationId);
    public void updateBalanceForCampaign(int campaignId, double amount);

    public List<DonorUserDto> getDonationsByCampaignIdForDonor(int campaign_id, String username);

    public List<DonorUserDto> getHallOfFameDonors();
}
