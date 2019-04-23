package command;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import search.HeadersSearch;
import search.HrefsWebpagesSearch;
import search.ImagesSearch;
import search.MP4Search;
import service.SendingPhoto;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Команда поиска видео по ключевому запросу пользователя
 */
public class Find {

    // Массив caption
    ArrayList<String> arrayHeaders;

    // Заполняем массив href
    ArrayList<String> arrayHrefs;

    // Массив UrlImg
    ArrayList<String> arrUrlImg;

    // Массив ссылок на MP4 ролики
    ArrayList<String> arrMP4links;

    public Find() {
        this.arrayHeaders = new ArrayList<String>();
        this.arrayHrefs = new ArrayList<String>();
        this.arrUrlImg = new ArrayList<String>();
        this.arrMP4links = new ArrayList<String>();
    }

    // Метод отвечает за наполнение массива заголовков, ссылок на видео, ссылок на картинки и mp4
    public void setArraysData(Message message) {

        // Очищаем массивы с заголовками, фото и ссылками перед новым поисковым запросом
        if (!arrayHeaders.isEmpty()) {
            arrayHeaders.clear();
        }
        if (!arrayHrefs.isEmpty()) {
            arrayHrefs.clear();
        }
        if (!arrUrlImg.isEmpty()) {
            arrUrlImg.clear();
        }
        if (!arrMP4links.isEmpty()) {
            arrMP4links.clear();
        }

        // Поисковый запрос без /http. По этим поисковым словам будем выдергивать видео из поиска сайта с видосами
        String messageText = message.getText();
        String searchingMessage = messageText.replaceAll("/find", "");
        System.out.println(searchingMessage);
        try {
            arrayHeaders = new HeadersSearch().getHeadersOfVideos(searchingMessage);
            arrayHrefs = new HrefsWebpagesSearch().getHrefsOfVideos(searchingMessage);
            arrUrlImg = new ImagesSearch().getImages(searchingMessage);
            arrMP4links = new MP4Search().getHrefsOfMP4(searchingMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Возвращает массив объектов SendingPhoto, которые в классе Bot могут быть выполнены командой execute
    public ArrayList<SendPhoto> findCommand(Message message) {

        ArrayList<SendPhoto> arrayListSendPhoto = new ArrayList<SendPhoto>();

        // Метод возвращает массивы данных по нашему запросу (заголовки, ссылки на видео и картинки)
        setArraysData(message);

        for (int i = 0; i < 5; i++) {

            String caption = arrayHeaders.get(i);
            String href = arrayHrefs.get(i);
            String urlImg = arrUrlImg.get(i);
            String urlMP4 = arrMP4links.get(i);

            SendPhoto sendPhoto = new SendingPhoto().sendPhoto(message, caption, href, urlImg); // Отправляем фото с подписью и ссылкой
            arrayListSendPhoto.add(sendPhoto);

        }

        return arrayListSendPhoto;
    }


    // Перегруженный метод поиска видео (с двумя параметрами). Второй параметр введен для поиска видео после получения CallbackQuery().getData()
    public ArrayList<SendPhoto> findCommand(Message message, String keywordText) {

        ArrayList<SendPhoto> arrayListSendPhoto = new ArrayList<SendPhoto>();

        // Очищаем массивы с заголовками, фото и ссылками перед новым поисковым запросом
        if (!arrayHeaders.isEmpty()) {
            arrayHeaders.clear();
        }
        if (!arrayHrefs.isEmpty()) {
            arrayHrefs.clear();
        }
        if (!arrUrlImg.isEmpty()) {
            arrUrlImg.clear();
        }
        if (!arrMP4links.isEmpty()) {
            arrMP4links.clear();
        }

        // Поисковый запрос без /http. По этим поисковым словам будем выдергивать видео из поиска сайта с видосами
        String searchingMessage = keywordText;

        // Отправим клиенту ссылки на видео
        try {

            arrayHeaders = new HeadersSearch().getHeadersOfVideos(searchingMessage);
            arrayHrefs = new HrefsWebpagesSearch().getHrefsOfVideos(searchingMessage);
            arrUrlImg = new ImagesSearch().getImages(searchingMessage);
            arrMP4links = new MP4Search().getHrefsOfMP4(searchingMessage);

            //lastIdOfVideo = 5;

            for (int i = 0; i < 5; i++) {

                String caption = arrayHeaders.get(i);
                String href = arrayHrefs.get(i);
                String urlImg = arrUrlImg.get(i);
                String urlMP4 = arrMP4links.get(i);

                SendPhoto sendPhoto = new SendingPhoto().sendPhoto(message, caption, href, urlImg); // Отправляем фото с подписью и ссылкой
                arrayListSendPhoto.add(sendPhoto);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return arrayListSendPhoto;
    }

    public ArrayList<String> getArrayHeaders() {
        return arrayHeaders;
    }

    public ArrayList<String> getArrayHrefs() {
        return arrayHrefs;
    }

    public ArrayList<String> getArrUrlImg() {
        return arrUrlImg;
    }

    public ArrayList<String> getArrMP4links() {
        return arrMP4links;
    }
}
