package edu.gadalov.webservice.validation;

/**AudioTrack validation class
 * @author Maxim Gadalov
 *
 */
public class TrackValidation {
	private static final String ERROR_MESSAGE_INVALID_COST = "Cost is not valid, try to use . separator,"
			+ "also note that the cost should not contain more than 2 decimal places and and can not be greater than 99$";
	private static final String ILLEGAL_VALID_COST = "Cost can not be a 0";
    private static final String COST_REGEXP = "[0-9]{1,2}([.][0-9]{1,2})?";
	/**Check audio track cost validation
	 * @param cost - cost of the audio track
	 * @return empty String if cost valid, else return String errorMessage
	 */
	public String checkCostValidation(String cost){
		String errorMessage = new String();
		if(!cost.matches(COST_REGEXP)){
			errorMessage = ERROR_MESSAGE_INVALID_COST;
		} else if(cost.equals("0") || cost.equals("0.0")){
			errorMessage = ILLEGAL_VALID_COST;
		}
		return errorMessage;
	}

}
