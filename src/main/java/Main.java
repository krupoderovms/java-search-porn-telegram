import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import secret.BotConfig;

public class Main {

//    private static String PROXY_HOST = "162.243.108.141" /* proxy host */;
//    private static Integer PROXY_PORT = 31095 /* proxy port */;

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        // Set up Http proxy
        DefaultBotOptions botOptions = ApiContext
                .getInstance(DefaultBotOptions.class);
        botOptions.setProxyHost("127.0.0.1");
        botOptions.setProxyPort(9150);
// Select proxy type: [HTTP|SOCKS4|SOCKS5] (default: NO_PROXY)
        botOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);


        try {
            telegramBotsApi.registerBot(new Bot(BotConfig.getBotToken(), BotConfig.getBotName(), botOptions));
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
