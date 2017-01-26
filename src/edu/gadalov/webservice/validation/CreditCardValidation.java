package edu.gadalov.webservice.validation;

/**Credit Card validation class
 * @author Maxim Gadalov
 *
 */
public class CreditCardValidation {
	private static final String CARD_NUMBER_REGEXP = "[0-9]+";
	private static final String CARD_NAME_REGEXP = "[A-z]+";
	private static final String CARD_EXPIRY_DATE_REGEXP = "[0-9]{2}[/][0-9]{2}";
	private static final String ERROR_MESSAGE_CARD_NUMBER_LENGTH = "Card number must contain 16 digits; ";
	private static final String ERROR_MESSAGE_CARD_NUMBER_CONTENT = "Card number must contain only digits; ";
	private static final String ERROR_MESSAGE_CARD_NAME_CONTENT = "Card name must contain only letters; ";
	private static final String ERROR_MESSAGE_EXPIRY_DATE_CONTENT = "Expiry date must be like '12/17'; ";
	private static final String ERROR_MESSAGE_CARD_CODE_LENGTH = "Card code must contain 3 digits; ";
	private static final String ERROR_MESSAGE_CARD_CODE_CONTENT = "Card code must contain only digits; ";
	/**Check credit card number validation. Credit card number must contain only digits
	 * @param cardNumber - String credit card number
	 * @return empty String if credit card number valid, else return String errorMessage
	 */
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
	/**Check credit card holder name validation. Holder name must contain only letters.
	 * @param name - String credit card holder name
	 * @return empty String if holder name valid, else return errorMessage
	 */
	public String checkCardName(String name){
		String errorMessage = new String();
		if(!name.matches(CARD_NAME_REGEXP)){
			errorMessage = ERROR_MESSAGE_CARD_NAME_CONTENT;
		}
		return errorMessage;
	}
	/**Check credit card expire date validation
	 * @param date - String expire date
	 * @return empty String if expire date is valid, else return String ErrorMessage
	 */
	public String checkCardExpiryDate(String date){
		String errorMessage = new String();
		if(!date.matches(CARD_EXPIRY_DATE_REGEXP)){
			errorMessage = ERROR_MESSAGE_EXPIRY_DATE_CONTENT;
		}
		return errorMessage;
	}
	/**Check credit card code validation. Code must contain only digits.
	 * @param code - String code
	 * @return empty String if credit card code is valid, else return String errorMessage
	 */
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
	/**Check credit card validation.
	 * @param cardNumber - String card number
	 * @param cvc - String card code
	 * @param expiryDate - String expire date
	 * @param firstName - String first name
	 * @param lastName - String last name
	 * @return empty String if credit card is valid, else return String errorMessage(s).
	 */
	public String creditCardValidation(String cardNumber,String cvc,String expiryDate, String firstName,String lastName){
		String errorMessage = new String();
		errorMessage += checkCardCode(cvc);
		errorMessage += checkCardExpiryDate(expiryDate);
		errorMessage += checkCardName(firstName);
		errorMessage += checkCardName(lastName);
		errorMessage += checkCardNumber(cardNumber);
		return errorMessage;
	}

}
