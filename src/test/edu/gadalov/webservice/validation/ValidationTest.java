package test.edu.gadalov.webservice.validation;

import org.junit.Test;

import edu.gadalov.webservice.validation.CreditCardValidation;
import edu.gadalov.webservice.validation.TrackValidation;
import edu.gadalov.webservice.validation.UserValidation;
import junit.framework.Assert;

public class ValidationTest {
	@Test
	public void checkTrackCost(){
		String cost1 = "1";
		String cost2 = "1.99";
		String cost3 = "0.99";
		String cost4 = "99.99";
		String wrongCost1 = "0";
		String wrongCost2 = "0.0";
		String wrongCost3 = "1,2";
		String wrongCost4 = "1000";
		String errorMessage = new String();
		String costError = "Cost is not valid, try to use . separator,"
			+ "also note that the cost should not contain more than 2 decimal places and and can not be greater than 99$";
		TrackValidation validation = new TrackValidation();
		errorMessage += validation.checkCostValidation(cost1);
		errorMessage += validation.checkCostValidation(cost2);
		errorMessage += validation.checkCostValidation(cost3);
		errorMessage += validation.checkCostValidation(cost4);
		Assert.assertEquals(new String(), errorMessage);
		Assert.assertEquals(validation.checkCostValidation(wrongCost1), "Cost can not be a 0");
		Assert.assertEquals(validation.checkCostValidation(wrongCost2), "Cost can not be a 0");
		Assert.assertEquals(validation.checkCostValidation(wrongCost3), costError);
		Assert.assertEquals(validation.checkCostValidation(wrongCost4), costError);
	}
	@Test
	public void checkUserEmailValidation(){
		String wrongEmail = "wrongemail@hostname51253.org785";
		String email = "gmaksim2012@gmail.com";
		UserValidation validation = new UserValidation();
		Assert.assertTrue(validation.emailValidity(email));
		Assert.assertFalse(validation.emailValidity(wrongEmail));
	}
	@Test
	public void creditCardValidation(){
		String creditCardNumber = "1234567891012345";
		String cvc = "123";
		String holderName = "Asfgas";
		String expityDate = "12/20";
		CreditCardValidation validation = new CreditCardValidation();
		String errorMessage = validation.creditCardValidation(creditCardNumber, cvc, expityDate, holderName, holderName);
		Assert.assertEquals(new String(), errorMessage);
		String wrongExpiryDate = "12\200";
		errorMessage = validation.creditCardValidation(creditCardNumber, cvc, wrongExpiryDate, holderName, holderName);
		Assert.assertEquals(errorMessage, "Expiry date must be like '12/17'; ");
	}

}
