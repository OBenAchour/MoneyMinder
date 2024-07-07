package Utils;

public class UserSession {

    private static String email;

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        UserSession.email = email;
    }
}
