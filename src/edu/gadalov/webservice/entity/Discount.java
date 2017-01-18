package edu.gadalov.webservice.entity;

public class Discount {
	private int id;
	private int bonus;
	public Discount(int id,int bonus){
		this.id = id;
		this.bonus = bonus;	
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBonus() {
		return bonus;
	}
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
}
