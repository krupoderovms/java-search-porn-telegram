import command.Find;
import command.MoreSelect;
import model.Videos;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import service.FixedKeyboard;
import service.Properties;
import service.SendTextMsg;
import service.SendingPhoto;
import service.keyboards.InlineKeyboard;

import java.util.ArrayList;

import static java.lang.Math.toIntExact;

/**
 * Бот отвечающий за отправку ссылок на видео в чат по запросу /http
 */
public class Bot extends AbilityBot {

    Find find = new Find();

    int lastIdOfVideo = 0;

    protected Bot(String botToken, String botUsername, DefaultBotOptions options) {
        super(botToken, botUsername, options);
    }

    @Override
    public int creatorId() {
        return 0;
    }

    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();

        if (message != null && message.hasText()) {
            String messageText = message.getText();

            // Команда find - поиск ссылок на видео с постерами по ключевым словам пользователя
            if (messageText.contains("/find")) {

                //find.setArraysData(message); // сделали выборку видео
                exeCute(find.findCommand(message));
                lastIdOfVideo = 5;
            }

            // Команда find - поиск видео по ключевым словам пользователя
            if (messageText.contains("/video")) {
                ArrayList<SendPhoto> sendPhoto = find.findCommand(message);
                for (SendPhoto send : sendPhoto) {
                    try {
                        execute(send);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }

            }

            // Команда more - выдача очередной порции видео (в определенном количестве Properties.NUMBER_OF_VIDEOS). Доступна после команды find
            else if (messageText.contains("/more")) {
                showMore(message);
            } else if (messageText.contains("More")) {
                showMore(message);
            } else if (messageText.contains("/start")) {
                // Стартовая информация для пользователей
                exeCute(new SendTextMsg().sendTextMsg(message, "Для поиска видео задайте команду /find и поисковый запрос. \nНапример, /find asian hot girls или /find горячие азатские девочки"));
                exeCute(new FixedKeyboard().install(message, "В меню доступна навигация по разделам видео. \nТам же есть список основных команд по управлению ботом."));
            }

            // Команда key - вызов меню для выбора рубрик (вызывает инлайн клавиатуру)
            else if (messageText.contains("/key")) {
                exeCute(new InlineKeyboard().send(update));  // Вызываем инлайн клаву
            } else if (messageText.contains("/stop")) {
                lastIdOfVideo = 0;
            }

            // При нажатии кнопки "О боте"
            else if (messageText.contains("О боте")) {
                exeCute(new SendTextMsg().sendTextMsg(message, "К сожалению тут ничего нет("));
            }

            // При нажатии кнопки "Категории"
            else if (messageText.contains("Категории")) {
                exeCute(new InlineKeyboard().send(update));  // Вызываем инлайн клаву
            }


            // При нажатии кнопки "Помощь"
            else if (messageText.contains("Помощь")) {
                exeCute(new SendTextMsg().sendTextMsg(message, "Команда   |   Описание"));
                exeCute(new SendTextMsg().sendTextMsg(message, "/start          |   Стартовая информация"));
                exeCute(new SendTextMsg().sendTextMsg(message, "/find           |   Поиск ссылок на видео. Напр, /find мамка админа"));
                exeCute(new SendTextMsg().sendTextMsg(message, "/more           |   Показать еще 5 видео, работает после команды /find или выбора категории"));
            }
        }

        // Если был зарегистрирован колбэк, обрабатываем его
        if (update.hasCallbackQuery()) {

            // Определяем данные колбэка (текст, id сообщения и чата)
            String call_data = update.getCallbackQuery().getData();
            long message_id = update.getCallbackQuery().getMessage().getMessageId();
            long chat_id = update.getCallbackQuery().getMessage().getChatId();

            if (call_data != null) {
                // заменяем исходный текст после нажатия на кнопку на новый текст
                String answer = "Ваш запрос выполняется...";
                EditMessageText new_message = new EditMessageText()
                        .setChatId(chat_id)
                        .setMessageId(toIntExact(message_id))
                        .setText(answer);

                try {
                    // отправляем новый текст в чат
                    execute(new_message); //

                    // вызываем перегруженный метод, выполняя поиск по getCallbackQuery().getData()
                    ArrayList<SendPhoto> sendPhoto = new Find().findCommand(update.getCallbackQuery().getMessage(), update.getCallbackQuery().getData());
                    for (SendPhoto send : sendPhoto) {
                        try {
                            execute(send);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }

                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void showMore(Message message) {
        MoreSelect moreSelect = new MoreSelect();
        Videos videos = moreSelect.outputMore(message, find.getArrayHeaders(), find.getArrayHrefs(), find.getArrUrlImg(), find.getArrMP4links(), lastIdOfVideo);
        for (int i = 0; i < videos.getArrayCaptions().size(); i++) {
            exeCute(new SendingPhoto().sendPhoto(message, videos.getArrayCaptions().get(i), videos.getArrayHref().get(i), videos.getArrUrlImg().get(i)));
        }
        // Проверка на конец диапозона выборки видео. После последнего видео выводим сообщение пользователю. Если размер массива текущей порции видео оказался не равен стандартному числу видео, которое установлено для 1 порции, то мы выводим сообщение
        if (videos.getArrayCaptions().size() != Properties.NUMBER_OF_VIDEOS) {
            exeCute(new SendTextMsg().sendTextMsg(message, "Конец подборки. Введите команду /find с новым запросом. Удачного просмотра!"));
        }
        lastIdOfVideo = lastIdOfVideo + Properties.NUMBER_OF_VIDEOS; //увеличиваем переменную на количество просмотренного видео, чтобы взять из общей выборки видео следующие по счету ролики
    }

    // Отправка пользователю видео подборки (фото и ссылка на ролик)
    private void sendMP4(Message message, String caption, String href) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(false); // при true вылетают исключения
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setParseMode("HTML");
        sendMessage.setText(href);
        try {
            execute(sendMessage);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void exeCute(SendMessage sendMessage) {
        try {
            execute(sendMessage);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void exeCute(SendPhoto sendPhoto) {
        try {
            execute(sendPhoto);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void exeCute(ArrayList<SendPhoto> photoList) {
        try {
            for (SendPhoto sendPhoto : photoList)
                execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}




