package edu.gadalov.webservice.util;

/**The Bool class wraps primitive type boolean in an object.
 * This class helps programmer set non-final boolean value in the anonymous class.
 * @author Maxim Gadalov
 *
 */
public class Bool {
	private static final boolean TRUE = true;
	private static final boolean FALSE = false;
	private boolean bool;
	/**Constructor with boolean primitive type.set primitive boolean value to the Bool object.
	 * @param bool primitive type boolean
	 */
	public Bool(boolean bool){
		this.bool = bool;
	}
	/**Default constructor. Create object with default FALSE value.
	 * 
	 */
	public Bool(){
		this.bool = FALSE;
	}
	/**Set boolean value to the current object.
	 * @param bool primitive type boolean
	 */
	public void setBoolValue(boolean bool){
		this.bool = bool;
	}
	/**Return primitive type boolean of current object. 
	 * @return primitive type boolean
	 */
	public boolean getBoolValue(){
		return bool;
	}
	/**
	 * Set current object TRUE value.
	 */
	public void setTrueValue(){
		this.bool = TRUE;
	}
	/**
	 * Set current object FALSE value.
	 */
	public void setFalseValue(){
		this.bool = FALSE;
	}
}
