package edu.gadalov.webservice.validation;

public class CreditCardValidation {
	private static final String CARD_NUMBER_REGEXP = "[0-9]+";
	private static final String CARD_NAME_REGEXP = "[A-z]+";
	private static final String CARD_EXPIRY_DATE_REGEXP = "[0-9]{2}[/][0-9]{2}";
	private static final String ERROR_MESSAGE_CARD_NUMBER_LENGTH = "Card number must contain 16 digits";
	private static final String ERROR_MESSAGE_CARD_NUMBER_CONTENT = "Card number must contain only digits";
	private static final String ERROR_MESSAGE_CARD_NAME_CONTENT = "Card name must contain only letters";
	private static final String ERROR_MESSAGE_EXPIRY_DATE_CONTENT = "Expiry date must be like '12/17'";
	private static final String ERROR_MESSAGE_CARD_CODE_LENGTH = "Card code must contain 3 digits";
	private static final String ERROR_MESSAGE_CARD_CODE_CONTENT = "Card code must contain only digits";
	public String checkCardNumber(String cardNumber){
		String errorMessage = new String();
		if(cardNumber.length() != 16){
			errorMessage = ERROR_MESSAGE_CARD_NUMBER_LENGTH;
		}
		if(!cardNumber.matches(CARD_NUMBER_REGEXP)){
			errorMessage = ERROR_MESSAGE_CARD_NUMBER_CONTENT;
		}
		return errorMessage;
	}
	public String checkCardName(String name){
		String errorMessage = new String();
		if(!name.matches(CARD_NAME_REGEXP)){
			errorMessage = ERROR_MESSAGE_CARD_NAME_CONTENT;
		}
		return errorMessage;
	}
	public String checkCardExpiryDate(String date){
		String errorMessage = new String();
		if(!date.matches(CARD_EXPIRY_DATE_REGEXP)){
			errorMessage = ERROR_MESSAGE_EXPIRY_DATE_CONTENT;
		}
		return errorMessage;
	}
	public String checkCardCode(String code){
		String errorMessage = new String();
		if(code.length() != 3){
			errorMessage = ERROR_MESSAGE_CARD_CODE_LENGTH;
		}
		if(!code.matches(CARD_NUMBER_REGEXP)){
			errorMessage = ERROR_MESSAGE_CARD_CODE_CONTENT;
		}
		return errorMessage;
		
	}

}
