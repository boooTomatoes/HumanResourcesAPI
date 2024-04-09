package utils;

import java.util.Base64;

public class Authorization {
    public static String[] decode(String headerString) {
        String base64Credentials = headerString.substring("Basic".length()).trim();
        String credentials = new String(Base64.getDecoder().decode(base64Credentials));
        return credentials.split(":", 2);
    }
}
