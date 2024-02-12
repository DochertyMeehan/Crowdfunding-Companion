package com.techelevator.dao;

import com.techelevator.model.CampaignDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component

public class CampaignJdbcDao implements CampaignDao {

    private JdbcTemplate template;

    public CampaignJdbcDao(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<CampaignDto> getCampaigns() {
        String sql = "SELECT * FROM campaign";
        List<CampaignDto> campaigns = new ArrayList<>();

        SqlRowSet results = template.queryForRowSet(sql);

        while(results.next()) {
            CampaignDto campaign = mapRowToCampaign(results);
            campaigns.add(campaign);
        }

        return campaigns;
    }
    public CampaignDto mapRowToCampaign(SqlRowSet rowset) {
        int id = rowset.getInt("campaign_id");
        String username = rowset.getString("username");
        String campaignName = rowset.getString("campaignName");
        String campaignType = rowset.getString("campaignType");
        String description = rowset.getString("description");
        double amountGoal = rowset.getDouble("amountGoal");
        double balance = rowset.getDouble("balance");

        CampaignDto campaign = new CampaignDto(id, username, campaignName, campaignType, description, amountGoal, balance);
        return campaign;
    }

    @Override
    public CampaignDto getCampaign(int campaignId) {
        String sql = "SELECT * FROM campaign WHERE campaign_id = ?";
        SqlRowSet result = template.queryForRowSet(sql,campaignId);

        CampaignDto campaign = null;
        if (result.next()) {
            campaign = mapRowToCampaign(result);
        }
        return campaign;
    }

    @Override
    public CampaignDto createCampaign(CampaignDto campaignToCreate) {
        String username = campaignToCreate.getUsername();
        String campaignName = campaignToCreate.getCampaignName();
        String campaignType = campaignToCreate.getCampaignType();
        String description = campaignToCreate.getDescription();
        double amountGoal = campaignToCreate.getAmountGoal();
        double balance = campaignToCreate.getBalance();

        String sql = "INSERT INTO campaign(username, campaignName, campaignType, description, amountGoal, balance) VALUES (?, ?, ?, ?, ?, ?) RETURNING campaign_id";
        int newCampaignID = template.queryForObject(sql, Integer.class, username, campaignName, campaignType, description, amountGoal, balance);

        return getCampaign(newCampaignID);
    }

    @Override
    public CampaignDto editCampaign(CampaignDto campaignToEdit) {
        String sql = "UPDATE campaign SET campaignName = ?, campaignType = ?, description = ?, amountGoal = ?, balance = ? WHERE campaign_id = ?";
        template.update(sql, campaignToEdit.getCampaignName(), campaignToEdit.getCampaignType(), campaignToEdit.getDescription(), campaignToEdit.getAmountGoal(), campaignToEdit.getBalance(), campaignToEdit.getCampaign_id());
        return getCampaign(campaignToEdit.getCampaign_id());
    }

    @Override
    public void deleteCampaign(int campaignId) {
        String sql = "DELETE FROM campaign WHERE campaign_id = ?";
        template.update(sql, campaignId);
    }
}
