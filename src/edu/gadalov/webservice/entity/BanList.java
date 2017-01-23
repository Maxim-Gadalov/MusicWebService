package edu.gadalov.webservice.entity;

public class BanList {
	private int id;
	private User user;
	private String reason;
	private int idAdmin;
	public BanList(int id, User user, String reason, int idAdmin) {
		this.id = id;
		this.user = user;
		this.reason = reason;
		this.idAdmin = idAdmin;
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
	public int getIdAdmin() {
		return idAdmin;
	}
	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return id;
	}

}
