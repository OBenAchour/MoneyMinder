package Services;

public class SimpleAuthenticationService implements AuthenticationService {

    private String loggedInUserEmail; // Store the email of the logged-in user

    @Override
    public boolean authenticate(String username, String password) {
        // Replace with your actual authentication logic (e.g., database lookup, API call)
        if ("user@example.com".equals(username) && "password123".equals(password)) {
            loggedInUserEmail = username; // Set the logged-in user email
            return true;
        }
        return false;
    }

    @Override
    public String getLoggedInUserEmail() {
        return loggedInUserEmail; // Return the email of the logged-in user
    }

    @Override
    public void logout() {
        loggedInUserEmail = null; // Clear the logged-in user state
    }
}
