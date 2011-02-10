package com.unknown.entity.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Raid {

	private String raidname;
	private String comment;
	private String date;
	private int id;
	private List<RaidItem> raidItems = new ArrayList<RaidItem>();
        private List<RaidChar> raidChars = new ArrayList<RaidChar>();
        private List<RaidReward> raidRewards = new ArrayList<RaidReward>();

        public Raid() {
                
        }

	public Raid(String raidname, String comment, String date, int id) {
		this.raidname = raidname;
		this.comment = comment;
		this.date = date;
		this.id = id;
	}
        public void setDate(String date) {
                this.date = date;
        }

        public void setComment(String comment) {
                this.comment = comment;
        }
        public void setId(int id) {
                this.id = id;
        }

        public void setRaidname(String raidname) {
                this.raidname = raidname;
        }

	public String getRaidname() {
		return raidname;
	}

	public String getComment() {
		return comment;
	}

	public String getDate() {
		return date;
	}

	public int getId() {
		return id;
	}

	public void setRaidItems(Collection<RaidItem> items){
		raidItems.addAll(items);
	}

	public List<RaidItem> getRaidItems() {
		return raidItems;
	}

        public void setRaidChars(Collection<RaidChar> chars) {
                raidChars.addAll(chars);
        }

        public List<RaidChar> getRaidChars() {
                return raidChars;
        }

        public List<RaidReward> getRaidRewards() {
                return raidRewards;
        }
        public void setRaidRewards(Collection<RaidReward> rewards) {
                raidRewards.addAll(rewards);
        }
}
