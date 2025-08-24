package Banking_System.System_Security;

import java.util.regex.Pattern;

public class SecureMethods {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"; //THIS CODE SNIPPET IS FROM AI

    public static boolean validateEmail(String email) {
        return Pattern.compile(EMAIL_REGEX).matcher(email).matches();
    }
    public static boolean validatePassword(String password) {
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$"; //THIS CODE SNIPPET IS FROM AI
        return Pattern.matches(passwordPattern, password);
    }

}
