package com.unknown.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User {

	private int id;
	private String username;
	private String role;
	private int shares;
	private double dkp_earned;
	private double dkp_spent;
	private double dkp;
	private boolean active = true;
	private List<CharacterItem> charItems = new ArrayList<CharacterItem>();

	public User() {

	}

	public User(int id, String username, String role, boolean active, int shares, double dkp_earned, double dkp_spent, double dkp) {
		this.id = id;
		this.username = username;
		this.role = role;
		this.shares = shares;
		this.dkp = dkp;
		this.dkp_earned = dkp_earned;
		this.dkp_spent = dkp_spent;
		this.active = active;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public int getShares() {
		return shares;
	}

	public void setShares(int x) {
		shares = x;
	}

	public void inactivate() {
		active = false;
	}

	public void activate() {
		active = true;
	}

	public boolean isActive() {
		return active;
	}

	public String getRole() {
		return role;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public String toString() {
		return username;
	}

	public double getDKP() {
		return dkp;
	}

	public double getDKPSpent() {
		return dkp_spent;
	}

	public double getDKPEarned() {
		return dkp_earned;
	}

	public void addCharItems(Collection<CharacterItem> items) {
		charItems.addAll(items);
	}

	public List<CharacterItem> getCharItems() {
		return charItems;
	}
}
