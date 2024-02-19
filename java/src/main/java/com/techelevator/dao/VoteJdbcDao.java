package com.techelevator.dao;

import com.techelevator.model.VoteDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;

@Component
public class VoteJdbcDao implements VoteDao {
    private JdbcTemplate template;

    public VoteJdbcDao(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }


    @Override
    public void addVote(VoteDto vote) {


        LocalDate proposalDeadline = getProposalDeadlineByProposalId(vote.getProposal_id());

        if (getCampaignBalanceByProposalId(vote.getProposal_id()) == getCampaignGoalByProposalId(vote.getProposal_id())
                && LocalDate.now().isBefore(proposalDeadline)) {
            String sql = "INSERT INTO vote(proposal_id, vote_response) VALUES (?, ?);";
            template.update(sql, vote.getProposal_id(), vote.isVote_response());
        }

    }


    public double getCampaignBalanceByProposalId(int proposal_id){

            String sql = "select campaign.balance\n" +
                    "from campaign\n" +
                    "join proposal on campaign.campaign_id = proposal.campaign_id\n" +
                    "where proposal.proposal_id = ?;";

            return template.queryForObject(sql, Double.class ,proposal_id);
    }

    public double getCampaignGoalByProposalId(int proposal_id){
        String sql = "select campaign.amountGoal\n" +
                "from campaign\n" +
                "join proposal on campaign.campaign_id = proposal.campaign_id\n" +
                "where proposal.proposal_id = ?;";

        return template.queryForObject(sql, Double.class ,proposal_id);
    }

    public LocalDate getProposalDeadlineByProposalId(int proposal_id){
        String sql = "select proposal_deadline from proposal where proposal_id = ?;";

        return template.queryForObject(sql, LocalDate.class ,proposal_id);
    }


    @Override
    public int totalTrueVotesByProposalId(int proposalId) {
        String sql2 = "SELECT COUNT(*) AS total_true_votes\n" +
                "FROM vote\n" +
                "WHERE vote_response = TRUE AND proposal_id = ?;";
        int trueTally = template.update(sql2, proposalId);
        return trueTally;
    }

    @Override
    public int totalVotesByProposalId(int proposalId) {
        String sql3 = "SELECT COUNT(*) AS total_votes\n" +
                "FROM vote\n" +
                "WHERE proposal_id = ?;";
        int totalTally = template.update(sql3, proposalId);
        return totalTally;
    }

//    @Override
//    public List<VoteDto> getVotesByProposalId(int proposalId) {
//        return null;
//    }
}
