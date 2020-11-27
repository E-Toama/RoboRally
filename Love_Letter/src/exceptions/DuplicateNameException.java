package exceptions;

/**
 * Simple Exception that is thrown if user tries to choose a name that is already taken.
 * Caught in WelcomeView, displays message as notification to user
 * @author Josef
 */
public class DuplicateNameException extends Exception {
    public DuplicateNameException(String message) {
        super(message);
    }
}
