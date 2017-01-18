package edu.gadalov.webservice.validation;

public class TrackValidation {
	private static final String ERROR_MESSAGE_INVALID_COST = "Cost is not valid, try to use . separator,"
			+ "also note that the cost should not contain more than 2 decimal places and and can not be greater than 99$";
    private static final String COST_REGEXP = "[0-9]{1,2}([.][0-9]{1,2})?";
	public String checkCostValidation(String cost){
		String errorMessage = new String();
		if(!cost.matches(COST_REGEXP)){
			errorMessage = ERROR_MESSAGE_INVALID_COST;
		}
		return errorMessage;
	}

}
