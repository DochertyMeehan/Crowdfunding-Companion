package com.techelevator.dao;

import com.techelevator.model.VoteDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class VoteJdbcDao implements VoteDao {
    private JdbcTemplate template;

    public VoteJdbcDao(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public void addVote(VoteDto vote) {
        String sql = "INSERT INTO vote(proposal_id, vote_response) VALUES (?, ?);";
        template.update(sql, vote.getProposal_id(), vote.isVote_response());
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
