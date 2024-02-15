package com.techelevator.model;

public class DonationDto {

    private int user_id;
    private int campaign_id;

    private double amount;

    public DonationDto(int user_id, int campaign_id, double amount) {
        this.user_id = user_id;
        this.campaign_id = campaign_id;
        this.amount = amount;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCampaign_id() {
        return campaign_id;
    }

    public void setCampaign_id(int campaign_id) {
        this.campaign_id = campaign_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
