package com.techelevator.dao;

import com.techelevator.model.CampaignDto;
import com.techelevator.model.DonationDto;
import com.techelevator.model.ProposalDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class DonationJdbcDao implements DonationDao{

    private JdbcTemplate template;

    public DonationJdbcDao(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public DonationDto getDonation(int donationId) {
        String sql = "SELECT * FROM donation WHERE donation_id = ?";
        SqlRowSet result = template.queryForRowSet(sql, donationId);

        DonationDto donation = null;
        if (result.next()) {
            donation = mapRowToDonation(result);
        }
        return donation;
    }

    @Override
    public void createDonation(DonationDto donationToCreate) {
        int userId = donationToCreate.getUser_id();
        int campaignId = donationToCreate.getCampaign_id();
        double amount = donationToCreate.getAmount();

        String sql = "INSERT INTO donation(user_id, campaign_id, amount) VALUES (?, ?, ?);";
        template.update(sql,userId, campaignId, amount);
        updateBalanceForCampaign(campaignId, amount);

    }


    public void updateBalanceForCampaign(int campaignId, double amount){
        String sql = "SELECT balance FROM campaign WHERE campaign_id = ?";
        double balance = template.queryForObject(sql, Double.class,campaignId);

        double newBalance = balance + amount;

        String sql2 = "UPDATE campaign SET balance = ? WHERE campaign_id = ?";
        template.update(sql2,
                newBalance,
                campaignId
                );
    }

    @Override
    public List<DonationDto> getDonationsByCampaignId(int userId, String username) {
        List<DonationDto> donations = new ArrayList<>();

        return donations;

    }

    @Override
    public List<DonationDto> getDonationsByUserId(DonationDto donation) {
        List<DonationDto> donations = new ArrayList<>();
        String sql = "SELECT donation WHERE user_id = ?;";
        SqlRowSet result = template.queryForRowSet(sql, donation.getUser_id());

       while (result.next()){
            DonationDto donate = mapRowToDonation(result);
            donations.add(donate);
        }
        return donations;
    }

    public DonationDto mapRowToDonation(SqlRowSet rowset) {
        int userId = rowset.getInt("user_id");
        int campaignId = rowset.getInt("campaign_id");
        double amount = rowset.getDouble("amount");

        DonationDto donation = new DonationDto(userId, campaignId, amount);
        return donation;
    }

    public String getUsernameByDonationId(int donationId){
        String sql = "SELECT username FROM campaign WHERE donation_id = ?;";

        String username = template.queryForObject(sql, String.class, donationId);

        return username;
    }

    public String getDonorUsernameByUserId(int userId){
        String sql = "select username FROM users\n" +
                "JOIN donation ON users.user_id = donation.user_id WHERE donation.user_id = 4;";
        return null;
    }




}
