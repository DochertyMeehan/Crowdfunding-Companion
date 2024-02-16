package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.CampaignDto;
import com.techelevator.model.DonationDto;
import com.techelevator.model.DonorUserDto;
import com.techelevator.model.ProposalDto;
import org.springframework.dao.DataAccessException;
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
    public List<DonorUserDto> getDonationsByCampaignIdForCreator(int campaign_id, String username) {
        List<DonorUserDto> donationUserDtoList = new ArrayList<>();

        try {
            if (getCreatorByCampaignId(campaign_id).equals(username)){
                String sql = "SELECT donation.amount, users.username\n" +
                        "FROM donation\n" +
                        "JOIN users ON donation.user_id = users.user_id\n" +
                        "WHERE donation.campaign_id = ?;\n";


            SqlRowSet results = template.queryForRowSet(sql, campaign_id);

            while (results.next()) {
                donationUserDtoList.add(mapRowToDonorUser(results));
            }

        }
        }catch (DataAccessException e){
            throw new DaoException("Cannot view donations of campaign that's not yours");
        }
        return donationUserDtoList;
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

    public DonationDto mapRowToDonation(SqlRowSet rowset) {
        int userId = rowset.getInt("user_id");
        int campaignId = rowset.getInt("campaign_id");
        double amount = rowset.getDouble("amount");

        DonationDto donation = new DonationDto(userId, campaignId, amount);
        return donation;
    }
    public DonorUserDto mapRowToDonorUser(SqlRowSet rowset) {
        String username = rowset.getString("username");

        double amount = rowset.getDouble("amount");

        DonorUserDto donation = new DonorUserDto(username, amount);
        return donation;
    }

    public String getCreatorByCampaignId(int campaignId){
        String sql = "SELECT username FROM campaign WHERE campaign_id = ?;";

        String username = template.queryForObject(sql, String.class, campaignId);

        return username;
    }


    @Override
    public List<DonorUserDto> getDonationsByCampaignIdForDonor(int campaign_id, String username) {
        List<DonorUserDto> donationUserDtoList = new ArrayList<>();

        try {
            if (listOfDonors(campaign_id).contains(username)){
                String sql = "SELECT donation.amount, users.username\n" +
                        "FROM donation\n" +
                        "JOIN users ON donation.user_id = users.user_id\n" +
                        "WHERE donation.campaign_id = ?;\n";


                SqlRowSet results = template.queryForRowSet(sql, campaign_id);

                while (results.next()) {
                    donationUserDtoList.add(mapRowToDonorUser(results));
                }

            }
        }catch (DataAccessException e){
            throw new DaoException("Cannot view donations of campaign that's not yours");
        }
        return donationUserDtoList;
    }


    public List<String> listOfDonors(int campaign_id){
        List<String> donorList = new ArrayList<>();

        String sql = "SELECT username FROM users \n" +
                "JOIN donation ON users.user_id = donation.user_id WHERE campaign_id = ?;";

        SqlRowSet results = template.queryForRowSet(sql, campaign_id);

        while (results.next()){
            donorList.add(mapRowToDonor(results));
        }
        return donorList;
    }

    public String mapRowToDonor(SqlRowSet rowSet){
        String username = rowSet.getString("username");

        return username;
    }






}
