package apiUtils.apiPayload;

public class APIPayload {

    public static String loginAPIPayload(String email, String password){
        String payload = "{\r\n"
                + "    \"userEmail\": \""+email+"\",\r\n"
                + "    \"userPassword\": \""+password+"\"\r\n"
                + "}";
        return payload;
    }
}
