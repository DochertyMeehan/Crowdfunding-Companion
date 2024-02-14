package com.techelevator.dao;

import com.techelevator.model.ProposalDto;

import java.util.List;

public interface ProposalDao {


    public List<ProposalDto> getProposals();

    public ProposalDto getProposal(int proposalId);

    public ProposalDto createProposal(ProposalDto proposalToCreate);

    public void editProposal(ProposalDto proposalToEdit, String username);

    public void deleteProposal(String name, int proposalId);


}
