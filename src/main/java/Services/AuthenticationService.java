package Services;

public interface AuthenticationService {

    /**
     * Attempts to authenticate a user with the given credentials.
     * Returns true if authentication succeeds, false otherwise.
     *
     * @param username The username or email of the user trying to authenticate.
     * @param password The password of the user trying to authenticate.
     * @return true if authentication succeeds, false otherwise.
     */
    boolean authenticate(String username, String password);

    /**
     * Retrieves the email of the currently logged-in user.
     * Returns null if no user is currently logged in.
     *
     * @return The email of the currently logged-in user, or null if no user is logged in.
     */
    String getLoggedInUserEmail();

    /**
     * Logs out the currently logged-in user.
     * Clears any session or authentication state.
     */
    void logout();
}
