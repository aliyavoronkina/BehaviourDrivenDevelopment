package data;

public class DataHelper {

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo() {
        return new AuthInfo("petya", "123qwerty");
    }

    public static String getVerificationCodeFor(AuthInfo authInfo) {
        return "12345";
    }
}