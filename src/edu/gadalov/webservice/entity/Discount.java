package edu.gadalov.webservice.entity;

import edu.gadalov.webservice.util.Unusable;

/**Entity class for interaction with database information and business logic.
 * Discount object
 * @author Maxim Gadalov
 *
 */
public class Discount implements Unusable{
	private int id;
	private int bonus;
	/**Create object with following parameters
	 * @param id - id of object similar PRIMARY KEY (int)
	 * @param bonus - value of discount(int)
	 */
	public Discount(int id,int bonus){
		this.id = id;
		this.bonus = bonus;	
	}
	/**
	 * Default constructor which create empty object.
	 */
	public Discount(){
	}
	/**
	 * @return id of the object 
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id - set new id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return discount value
	 */
	public int getBonus() {
		return bonus;
	}
	/**
	 * @param bonus - new discount value
	 */
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	@Override
	public boolean isUntapped() {
		return ((this.id == 0) & (this.bonus == 0));
	}
}
