BEGIN TRANSACTION;

DROP TABLE IF EXISTS users,campaign,campaign_user,donation,proposal,vote;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE campaign(
	campaign_id SERIAL,
	username varchar(50) NOT NULL,
	campaignName varchar(50) NOT NULL UNIQUE,
	campaignType varchar(20) NOT NULL UNIQUE,
	description varchar(300) NOT NULL,
	amountGoal numeric (13,2) NOT NULL, 
	balance numeric (13,2) NOT NULL,
	CONSTRAINT PK_campaign PRIMARY KEY (campaign_id),
	CONSTRAINT FK_campaign FOREIGN KEY (username) REFERENCES users(username)
);


CREATE TABLE campaign_user (
    campaign_user_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    campaign_id INT NOT NULL,
    CONSTRAINT FK_campaign_user_user FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT FK_campaign_user_campaign FOREIGN KEY (campaign_id) REFERENCES campaign(campaign_id),
    CONSTRAINT UQ_user_campaign UNIQUE (user_id, campaign_id)
);

CREATE TABLE donation (
	donation_id SERIAL PRIMARY KEY,
	user_id INT NOT NULL,
	campaign_id INT NOT NULL,
	amount numeric (13,2) NOT NULL,
	CONSTRAINT FK_donation_user_user FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT FK_donation_campaignId FOREIGN KEY (campaign_id) REFERENCES campaign(campaign_id)
);

CREATE TABLE proposal (
	proposal_id SERIAL PRIMARY KEY,
	campaign_id INT NOT NULL,
	description varchar(300) NOT NULL,
	CONSTRAINT FK_proposal_campaignId FOREIGN KEY (campaign_id) REFERENCES campaign(campaign_id) 
);

CREATE TABLE vote (
	vote_id SERIAL PRIMARY KEY,
	proposal_id INT NOT NULL,
	vote_response BOOLEAN NOT NULL,
	CONSTRAINT FK_vote_campaignId FOREIGN KEY (proposal_id) REFERENCES campaign(campaign_id)
);


COMMIT TRANSACTION;
