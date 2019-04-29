package service;

/**
 * Данный класс позволяет преобразовать запрос клиента к формату нужному для поиска на сайте (вместо пробелов расставляет знак плюса)
 */
public class SearchingMessage {

    // Поисковый запрос от клиента
    private String searchMessage;

    public SearchingMessage(String searchMessage) {
        this.searchMessage = searchMessage;
    }

    // Метод разбивающий предложение на отдельные слова и вставляющий плюс между ними
    public String splitMessage() {

        String splitMessageComplete = searchMessage.replaceAll(" ", "+");

        return splitMessageComplete;
    }

}
