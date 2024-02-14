package com.techelevator.dao;

import com.techelevator.model.CampaignDto;
import com.techelevator.model.ProposalDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class ProposalJdbcDao implements ProposalDao{


    private JdbcTemplate template;

    public ProposalJdbcDao(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<ProposalDto> getProposals() {
        String sql = "SELECT * FROM proposal";
        List<ProposalDto> proposals = new ArrayList<>();

        SqlRowSet results = template.queryForRowSet(sql);

        while(results.next()) {
            ProposalDto proposal = mapRowToProposal(results);
            proposals.add(proposal);
        }

        return proposals;
    }

    public ProposalDto mapRowToProposal(SqlRowSet rowset) {
        int proposalId = rowset.getInt("proposal_id");
        int campaignId = rowset.getInt("campaign_id");
        String description = rowset.getString("description");
        String proposalName = rowset.getString("proposal_name");
        Boolean votePassed = rowset.getBoolean("vote_passed");
        String proposalStatus = rowset.getString("proposal_status");


        ProposalDto proposal = new ProposalDto(proposalId, campaignId, description, votePassed, proposalName, proposalStatus);
        return proposal;
    }



    @Override
    public ProposalDto getProposal(int proposalId) {
        String sql = "SELECT * FROM proposal WHERE proposal_id = ?";
        SqlRowSet result = template.queryForRowSet(sql,proposalId);

        ProposalDto proposal = null;
        if (result.next()) {
            proposal = mapRowToProposal(result);
        }
        return proposal;
    }

    @Override
    public ProposalDto createProposal(ProposalDto proposalToCreate) {
        return null;
    }

    @Override
    public void editProposal(ProposalDto proposalToEdit, String username) {

        String sql = "UPDATE proposal SET description = ?, proposal_name = ?, " +
                " proposal_status = ? WHERE proposal_id = ?;";

    }

    @Override
    public void deleteProposal(String name, int proposalId) {

    }
}
