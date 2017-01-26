package edu.gadalov.webservice.entity;
/**Entity class for interaction with database information and business logic.
 * Role object.
 * @author Maxim Gadalov
 *
 */
public class Role {
	private int id;
	private String roleName;
	/**Create object with following parameters
	 * @param id - id of object similar PRIMARY KEY (int)
	 * @param roleName - name of role (String )
	 */
	public Role(int id,String roleName){
		this.id = id;
		this.roleName = roleName;
	}
	/**
	 * @return id of the object
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id - new id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return role value (String)
	 */
	public String getRoleName() {
		return roleName;
	}
	/**
	 * @param roleName - new role value
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
