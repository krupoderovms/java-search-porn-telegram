package secret;

public class BotConfig {

    private static final String BOT_NAME = "Your_bot_name";
    private static final String BOT_TOKEN = "Your_bot_token";

    private static String PROXY_HOST = "..." /* proxy host */;
    private static Integer PROXY_PORT = 3128 /* proxy port */;

    public static String getBotName() {
        return BOT_NAME;
    }

    public static String getBotToken() {
        return BOT_TOKEN;
    }

    public static String getProxyHost() {
        return PROXY_HOST;
    }

    public static Integer getProxyPort() {
        return PROXY_PORT;
    }
}
