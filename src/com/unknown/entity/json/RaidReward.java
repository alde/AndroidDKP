package com.unknown.entity.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RaidReward {
	private int id;
	private String comment;
	private int shares;
	private final List<RaidChar> raidChars = new ArrayList<RaidChar>();
	private int raidId;

	public RaidReward() {

	}

	public RaidReward(String comment, int id, int raidId, int shares) {
		this.comment = comment;
		this.shares = shares;
		this.id = id;
		this.raidId = raidId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getShares() {
		return shares;
	}

	public void setShares(int shares) {
		this.shares = shares;
	}

	public void setRewardChars(Collection<RaidChar> chars) {
		raidChars.addAll(chars);
	}

	public List<RaidChar> getRewardChars() {
		return raidChars;
	}

	public int getRaidId() {
		return raidId;
	}

	public void setRaidId(int raidId) {
		this.raidId = raidId;
	}
}
