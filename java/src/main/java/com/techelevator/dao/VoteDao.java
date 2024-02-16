package com.techelevator.dao;

import com.techelevator.model.VoteDto;

import java.util.List;

public interface VoteDao {

    public void addVote(VoteDto vote);
    List<VoteDto> getVotesByProposalId(int proposalId);
}
