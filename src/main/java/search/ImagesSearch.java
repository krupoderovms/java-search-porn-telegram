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
 * Поиск ЗАСТАВКИ (ОСНОВНОГО ПОСТЕРА) для данного роликов
 */
public class ImagesSearch {

    private String url;               // базовый url сайта, прописан в service.Properties
    private String serchMsgFormated;  // Отформатированный поисковый запрос, готовый к работе
    private String urlSearch;         // Полный url поискового запроса
    Document doc = null;

    // ОСНОВНОЙ МЕТОД получения базовой картинки для страницы с видеороликом
    public ArrayList<String> getImages(String serchMsg) {

        try {
            // Парсим страницу по переданному URL и находим ссылку на IMG заставку для ролика
            ArrayList<String> srcImage = parseDocument(serchMsg);
            return srcImage;

        } catch (Exception e) {
            // В случае если видео не существует (напр, было удалено с сайта) мы получим уведомление о том, какое конкретно видео не доступно
            return null;
        }
    }

    // Метод парсинга веб-страницы с помощью библиотеки JSOUP
    public ArrayList<String> parseDocument(String serchMsg) throws IOException {

        this.serchMsgFormated = new SearchingMessage(serchMsg).splitMessage();
        this.url = Properties.URL;
        this.urlSearch = url + "/search/" + serchMsgFormated;

        // Массив всех ссылок на видеоролики
        ArrayList<String> hrefsOfVideos = new HrefsWebpagesSearch().getHrefsOfVideos(serchMsgFormated);

        // Массив всех картинок по нашему запросу
        ArrayList<String> listImg = new ArrayList<String>();

        for (String href : hrefsOfVideos) {

            doc = Jsoup.connect(href).get();

            // Получаем элементы класса, отвечающего за ссылки на нужные нам страницы с видео
            Elements imageElements = doc.getElementsByAttributeValue("class", "video-pic");

            Element divImg = doc.select(".copy-link input").first();

            //Element img = doc.select("img").first();
            String src = divImg.attr("value");
            listImg.add(src);
        }
        return listImg;
    }
}
