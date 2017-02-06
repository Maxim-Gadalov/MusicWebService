package edu.gadalov.webservice.entity;

import edu.gadalov.webservice.util.Unusable;

/**Entity class for interaction with database information and business logic.
 * User object.
 * @author Maxim Gadalov
 *
 */
public class User implements Unusable{
	private int id;
	private Role role;
	private String nickname;
	private String email;
	private String password;
	private Discount bonus;
	private String skype;
	private String phoneNumber;
	/**Create user object with following parameters
	 * @param id - id of object similar PRIMARY KEY (int)
	 * @param role - user role (String)
	 * @param nickname - user nickname (String)
	 * @param email - user email (String)
	 * @param password - encrypting password 
	 * @param bonus - discount @see {@link Discount#Discount(int, int)}
	 * @param skype - skype (String)
	 * @param phoneNumber - phone number (String)
	 */
	public User(int id, Role role, String nickname, String email, String password, Discount bonus,
			String skype, String phoneNumber) {	
		this.id = id;
		this.role = role;
		this.nickname = nickname;
		this.email = email;
		this.password = password;
		this.bonus = bonus;
		this.skype = skype;
		this.phoneNumber = phoneNumber;
	}
	/**
	 * Default constructor which create empty object.
	 */
	public User(){
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Discount getBonus() {
		return bonus;
	}
	public void setBonus(Discount bonus) {
		this.bonus = bonus;
	}
	public void setSkype(String skype){
		this.skype = skype;
	}
	public String getSkype(){
		return skype;
	}
	public void setPhoneNumber(String number){
		this.phoneNumber = number;
	}
	public String getPhoneNumber(){
		return phoneNumber;
	}
	@Override
	public boolean isUntapped() {
		return ((this.bonus == null) & (this.id == 0) & (this.email == null) &
				(this.nickname == null) & (this.password == null) & (this.phoneNumber == null) &
				(this.role == null) & (this.skype == null));
	}
}
