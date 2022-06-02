package utils;

import org.apache.commons.codec.binary.Base64;

public class AuthenticationHandler {
    public static String encodeCredStr (String email, String token) {
        if (email == null || token == null )
            throw new IllegalArgumentException("ERR: email or token is null");
        String credential = email.concat(":").concat(token);
        byte[] encodeCredential = Base64.encodeBase64(credential.getBytes());
        return new String(encodeCredential);
    }
}
