package edu.gadalov.webservice.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**Encryption password class.
 * @author Maxim Gadalov
 *
 */
public class EncryptionPassword {
	private static final Logger LOG = LogManager.getLogger(EncryptionPassword.class);
	private static final String ENCRYPTION_ALGORITHM = "MD5";
	/**Return encrypted value. MD5 algorithm.
	 * @param st - String password
	 * @return encrypted String
	 */
	public static String encrypt(String st) {
	    MessageDigest messageDigest = null;
	    byte[] digest = new byte[0]; 
	    try {
	        messageDigest = MessageDigest.getInstance(ENCRYPTION_ALGORITHM);
	        messageDigest.reset();
	        messageDigest.update(st.getBytes());
	        digest = messageDigest.digest();
	    } catch (NoSuchAlgorithmException e) {
	        LOG.warn("the algorithm encryption is invalid"+e);
	    }
	    BigInteger bigInt = new BigInteger(1, digest);
	    String md5Hex = bigInt.toString(16);
	    while( md5Hex.length() < 32 ){
	        md5Hex = "0" + md5Hex;
	    }
	    return md5Hex;
	}
}
