package service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Отправка пользователю простого сообщения
 */
public class SendTextMsg {

    public SendMessage sendTextMsg(Message message, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(false); // при true вылетают исключения
        sendMessage.disableWebPagePreview();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setParseMode("HTML");
        //sendMessage.setReplyToMessageId(message.getMessageId()); // подпись в заголовке поста (имя юзера)
        sendMessage.setText(s);

        return sendMessage;
    }

}
