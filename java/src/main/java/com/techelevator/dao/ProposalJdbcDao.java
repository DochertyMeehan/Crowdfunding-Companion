package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.CampaignDto;
import com.techelevator.model.ProposalDto;
import org.springframework.dao.DataAccessException;
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
       int campaignid = proposalToCreate.getCampaign_id();
       String proposalname = proposalToCreate.getProposal_name();
       String description = proposalToCreate.getDescription();
       String proposal = proposalToCreate.getProposal_status();

        String sql = "INSERT INTO proposal (campaign_id, proposal_name, description,proposal_status,vote_passed)" +
        " VALUES ((SELECT campaign_id FROM campaign WHERE campaignName = ?), ?, ?, ?, NULL) RETURNING proposal_id;";
        int newProposalID = template.queryForObject(sql, Integer.class, campaignid, proposalname, description, proposal);

        return getProposal(newProposalID);
    }

    @Override
    public void editProposal(ProposalDto proposalToEdit, String username, int campaignId) {
        try {
            if (getUsernameByCampaignId(campaignId).equals(username)) {
                String sql = "UPDATE proposal SET description = ?, proposal_name = ?, " +
                        " proposal_status = ? WHERE proposal_id = ?;";
                template.update(sql,
                        proposalToEdit.getDescription(),
                        proposalToEdit.getProposal_name(),
                        proposalToEdit.getProposal_status(),
                        proposalToEdit.getProposal_id());
            }
        }catch(DataAccessException e){
                throw new DaoException("Cannot edit proposal that isn't yours");
            }
        }


    @Override
    public void deleteProposal(String name,int campaignId, int proposalId) {
        try {
            if (getUsernameByCampaignId(campaignId).equals(name)) {
                String sql = "DELETE FROM proposal WHERE proposal_id = ? AND username = ?";
                template.update(sql, proposalId, name);
            }
        } catch (DataAccessException e){
            throw new DaoException("Cannot delete proposal that isn't yours");
        }

    }

    public String getUsernameByCampaignId(int campaignId){
        String sql = "SELECT username FROM campaign WHERE campaign_id = ?;";
        String username = template.queryForObject(sql, String.class, campaignId);
        return username;
    }

}
