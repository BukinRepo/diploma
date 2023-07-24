package utils;

public class Urls {
    private static final String LOGIN_NAME = "jsonrpc" ;
    private static final String TOKEN = "abe72a2a856b457771ecfe8b871d8a232dae28ba7d43a5d1c3f2360a5df8";
    private static final String API_URL = "http://127.0.0.1/jsonrpc.php";

    public static String getLoginName(){
        return LOGIN_NAME;
    }

    public static String getToken(){
        return TOKEN;
    }

    public static String getApiUrl(){
        return API_URL;
    }
}
