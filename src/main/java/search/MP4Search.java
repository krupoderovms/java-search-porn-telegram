package search;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import service.Properties;
import service.SearchingMessage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Поиск ссылок на MP4 ролики
 */
public class MP4Search {

    private String url;               // базовый url сайта, прописан в service.Properties
    private String serchMsgFormated;  // Отформатированный поисковый запрос, готовый к работе
    private String urlSearch;         // Полный url поискового запроса


    // ОСНОВНОЙ МЕТОД по получению ссылок на ролики MP4
    public ArrayList<String> getHrefsOfMP4(String serchMsg) throws IOException {

        this.serchMsgFormated = new SearchingMessage(serchMsg).splitMessage();
        this.url = Properties.URL;
        this.urlSearch = url + "/search/" + serchMsgFormated;

        // Парсим веб-страницу, полученную после поиска по поисковому выражению пользователя и получаем массив элементов ЗАГОЛОВКОВ
        Elements hrefsElements = parseDocument(urlSearch);

        // Массив ссылок на MP4 (Разработан, но пока не используется в программе!)
        ArrayList<String> arrayMP4 = getHrefMP4(hrefsElements);

        return arrayMP4;
    }

    // Метод парсинга веб-страницы с помощью библиотеки JSOUP
    private Elements parseDocument(String urlSearch) throws IOException {
        // Получаем JSOUP обьект страницы по адресу нашего запроса
        Document doc = Jsoup.connect(urlSearch).get();
        Elements videoElements = doc.getElementsByAttributeValue("class", "thumb");
        return videoElements;
    }

    // Возвращает массив заголовков к видео
    public ArrayList<String> getHrefMP4(Elements videoElements) {
        // Массив JPG, которые будут переделаны далее в ссылки на mp4
        ArrayList<String> arrJPG = new ArrayList<String>();

        // Массив ссылок на MP4
        ArrayList<String> arrMP4 = new ArrayList<String>();

        // Достаем ссылку на картинку, которую потом переделаем в ссылку MP4. Они отличаются тем, что в них промежуточное звено
        // называется videopreview и последнего цифрового блока нет. Плюс файл заканчивается как _169.mp4
        for (Element mp4 : videoElements) {
            Elements element = mp4.select("img");
            String hf = element.attr("data-src");
            arrJPG.add(hf);

            StringBuilder sb = new StringBuilder();
            sb.append(hf);
            // Разбиваем ссылку на jpeg на массив по '/'
            String[] myArray = sb.toString().split("/");
            // Сюда мы помещаем все наши элементы
            ArrayList<String> arr = new ArrayList<String>();

            // Очищаем StringBuilder для следующего видео
            sb.delete(0, sb.length());

            // Помещаем в ArrayList все составные элементы ссылки на картинку, разбитые по '/'
            for (String s : myArray) {
                arr.add(s);
            }

            // Сюда мы будем собирать новый адрес - видеоролика mp4
            StringBuilder sb2 = new StringBuilder();

            for (int i = 0; i < arr.size() - 1; i++) {
                // Меняем промежуточное звено на нужное нам название
                if (arr.get(i).equals("thumbs169xnxx")) {
                    arr.set(i, "videopreview");
                }
                sb2.append(arr.get(i));

                // Ставит слэши везде кроме как в конце
                if (i != (arr.size() - 2)) {
                    sb2.append("/");
                }
            }

            // добавляем в конец ссылки расширение
            sb2.append("_169.mp4");

            arrMP4.add(sb2.toString());
        }
        return arrMP4;
    }
}
