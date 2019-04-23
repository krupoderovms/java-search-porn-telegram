package service.keyboards;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Отправка инлайн-клавиатуры
 */
public class InlineKeyboard {

    public SendMessage send(Update update) {

        String categoriesText = "Выберите категорию из представленных ниже"; // к этому сообщению в чате будут прикреплены все кнопки

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(); //Создаем объект разметки клавиатуры

        // Создаем кнопки
        InlineKeyboardButton inlineBtn_1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineBtn_2 = new InlineKeyboardButton();
        InlineKeyboardButton inlineBtn_3 = new InlineKeyboardButton();
        InlineKeyboardButton inlineBtn_4 = new InlineKeyboardButton();
        InlineKeyboardButton inlineBtn_5 = new InlineKeyboardButton();
        InlineKeyboardButton inlineBtn_6 = new InlineKeyboardButton();
        InlineKeyboardButton inlineBtn_7 = new InlineKeyboardButton();
        InlineKeyboardButton inlineBtn_8 = new InlineKeyboardButton();

        // Прикрепляем к ним текст на кнопке и колбэки (отклики на нажатие)
        inlineBtn_1.setText("Ass").setCallbackData("ass");
        inlineBtn_2.setText("Asian").setCallbackData("asian");
        inlineBtn_3.setText("German").setCallbackData("german");
        inlineBtn_4.setText("Russian").setCallbackData("russian");
        inlineBtn_5.setText("Japanese").setCallbackData("japanese");
        inlineBtn_6.setText("Lesbian").setCallbackData("lesbian");
        inlineBtn_7.setText("POV").setCallbackData("pov");
        inlineBtn_8.setText("Amateur").setCallbackData("amateur");

        // Создаем массив строк, в которые будут помещены те или иные кнопки
        List<InlineKeyboardButton> keybRow1 = new ArrayList<InlineKeyboardButton>();
        List<InlineKeyboardButton> keybRow2 = new ArrayList<InlineKeyboardButton>();

        // Помещаем в строки внопки
        keybRow1.add(inlineBtn_1);
        keybRow1.add(inlineBtn_2);
        keybRow1.add(inlineBtn_3);
        keybRow1.add(inlineBtn_4);
        keybRow2.add(inlineBtn_5);
        keybRow2.add(inlineBtn_6);
        keybRow2.add(inlineBtn_7);
        keybRow2.add(inlineBtn_8);

        // Создаем массив всех рядов кнопок
        List<List<InlineKeyboardButton>> rowList = new ArrayList<List<InlineKeyboardButton>>();
        rowList.add(keybRow1);
        rowList.add(keybRow2);

        inlineKeyboardMarkup.setKeyboard(rowList);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setText(categoriesText);

        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        return sendMessage;

    }


}
