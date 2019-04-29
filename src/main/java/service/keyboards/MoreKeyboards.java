package service.keyboards;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;

public class MoreKeyboards {

    ReplyKeyboardMarkup replyKeyboardMarkup;
    ArrayList<KeyboardRow> keyboard;

    public MoreKeyboards() {

        this.replyKeyboardMarkup = new ReplyKeyboardMarkup();
        this.keyboard = new ArrayList<KeyboardRow>();
    }

    public SendMessage install(Message message, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setReplyMarkup(replyKeyboardMarkup); // устанавливаем клаву для пользователя

        // Создаем строки
        KeyboardRow keyboardFirstRow = new KeyboardRow();

        // Основные установки для клавиатуры
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        keyboard.clear();
        keyboardFirstRow.clear();

        keyboardFirstRow.add("More");
        keyboard.add(keyboardFirstRow);

        replyKeyboardMarkup.setKeyboard(keyboard); // обновляем клаву

        sendMessage.enableMarkdown(false); // при true вылетают исключения
        sendMessage.disableWebPagePreview();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setParseMode("HTML");
        sendMessage.setText(s);

        return sendMessage;

    }
}
