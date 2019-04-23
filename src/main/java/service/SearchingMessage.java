package service;

/**
 * Данный класс позволяет преобразовать запрос клиента к формату нужному для поиска на сайте (вместо пробелов расставляет знак плюса)
 */
public class SearchingMessage {

    // Поисковый запрос от клиента
    private String searcheMessage;

    public SearchingMessage(String searcheMessage) {
        this.searcheMessage = searcheMessage;
    }

    // Метод разбивающий предложение на отдельные слова и вставляющий плюс между ними
    public String splitMessage() {

       String splitMessageComplete = searcheMessage.replaceAll(" ", "+");

        return splitMessageComplete;
    }

}
