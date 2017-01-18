package edu.gadalov.webservice.entity;

public class BanList {
	private User user;
	private String reason;
	private String admin;
	public BanList(User user, String reason, String admin) {
		this.user = user;
		this.reason = reason;
		this.admin = admin;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}

}
