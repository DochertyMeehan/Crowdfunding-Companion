package com.techelevator.controller;

import com.techelevator.dao.CampaignDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.CampaignDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@CrossOrigin
public class AppController {
    @Autowired
    private CampaignDao dao;

    @RequestMapping(path="/all-campaigns", method = RequestMethod.GET)
    public List<CampaignDto> getAllCampaigns() {
        return dao.getCampaigns();
    }

    @RequestMapping(path="/campaign", method = RequestMethod.GET)
    public CampaignDto getCampaign(@RequestParam("campaign_id") int campaign_id) {
        return dao.getCampaign(campaign_id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/create-campaign", method = RequestMethod.POST)
    public CampaignDto createCampaign(@Valid @RequestBody CampaignDto campaign) {
        return dao.createCampaign(campaign);
    }

    @RequestMapping(path="/edit-campaign", method = RequestMethod.PUT)
    public CampaignDto editCampaign(@Valid @RequestBody CampaignDto campaign) {
        return dao.editCampaign(campaign);
    }

    @RequestMapping(path="/delete", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCampaign(Principal principal, @RequestParam("campaign_id") int campaign_id) {

        try {
            dao.deleteCampaign(principal.getName(), campaign_id);
            return ResponseEntity.ok("Campaign deleted successfully");
        } catch (DaoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    }

