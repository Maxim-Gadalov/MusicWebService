package edu.gadalov.webservice.entity;
/**Entity class for interaction with database information and business logic.
 * Ban object.
 * @author Maxim Gadalov
 *
 */
public class BanList {
	private int id;
	private User user;
	private String reason;
	private int idAdmin;
	/**Create object with following parameters
	 * @param id - id of object similar PRIMARY KEY (int)
	 * @param user - user who was of will be banned @see {@link User#User(int, Role, String, String, String, Discount, String, String)}
	 * @param reason - reason of ban (String)
	 * @param idAdmin - id admin who banned user
	 */
	public BanList(int id, User user, String reason, int idAdmin) {
		this.id = id;
		this.user = user;
		this.reason = reason;
		this.idAdmin = idAdmin;
	}
	/**
	 * @return User @see {@link User#User(int, Role, String, String, String, Discount, String, String)} 
	 */
	public User getUser() {
		return user;
	}
	/**set new user object
	 * @param user - User @see {@link User#User(int, Role, String, String, String, Discount, String, String)}
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/** get reason of ban
	 * @return the reason of ban(String)
	 */
	public String getReason() {
		return reason;
	}
	/** set new reason of ban
	 * @param reason set new reason of ban(String) 
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**get admin id(int)
	 * @return id admin who banned user @see {@link User#User(int, Role, String, String, String, Discount, String, String)}
	 */
	public int getIdAdmin() {
		return idAdmin;
	}
	/**set new admin id
	 * @param idAdmin set admin id(int)
	 */
	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}
	/**set a new id
	 * @param id - set id to current object
	 */
	public void setId(int id){
		this.id = id;
	}
	/**get current id of the object
	 * @return id (int)
	 */
	public int getId(){
		return id;
	}

}
