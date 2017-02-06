package edu.gadalov.webservice.util;

/**A class implements the Unusable interface to make Object override isUntapped() method.
 * This interface helps the programmer avoid NullPointerException using their method instead of checking for NULL.
 * ATTENTION : All objects that implement this interface must be declared as new objects!
 * @author Maxim Gadalov
 *
 */
public interface Unusable {
	/**This method verifies whether the object is used.
	 * Return true if and only if the object was created without any fields(Default Constructor) and was not changed in the process, else - otherwise.
	 * @return boolean
	 */
	boolean isUntapped();

}
