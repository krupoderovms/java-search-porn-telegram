package service;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Возвращает объект SendingPhoto
 */
public class SendingPhoto {

    // Возвращает объект SendingPhoto с заголовком, ссылкой на вебстраницу с видео
    public SendPhoto sendPhoto(Message message, String caption, String href, String urlImg) {
        SendPhoto sendPhotoRequest = new SendPhoto();
        sendPhotoRequest.setChatId(message.getChatId());
        sendPhotoRequest.setParseMode("HTML");
        String captionHref = "<a href='"+href+"'>"+caption+"</a>";
        sendPhotoRequest.setCaption(captionHref);
        sendPhotoRequest.setPhoto(urlImg);
        return sendPhotoRequest;

    }
}
