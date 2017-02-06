package edu.gadalov.webservice.entity;

import edu.gadalov.webservice.util.Unusable;

/**Entity class for interaction with database information and business logic.
 * Role object.
 * @author Maxim Gadalov
 *
 */
public class Role implements Unusable{
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
	 * Default constructor which create empty object.
	 */
	public Role(){
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
	@Override
	public boolean isUntapped() {
		return ((this.id == 0) & (this.roleName == null));
	}
}
