package test.edu.gadalov.webservice.util;

import org.junit.Assert;
import org.junit.Test;

import edu.gadalov.webservice.util.Bool;

public class BoolTest {
	@Test
	public void checkBool(){
		Bool bool = new Bool(true);
		Bool bool2 = new Bool();
		Assert.assertTrue(bool.getBoolValue());
		Assert.assertFalse(bool2.getBoolValue());
		bool.setFalseValue();
		bool2.setTrueValue();
		Assert.assertTrue(bool2.getBoolValue());
		Assert.assertFalse(bool.getBoolValue());
	}

}
