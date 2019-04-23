package model;

/**
 *
 */
public class Pornohub {

    public final String NAME = "pornohub";
    private final String URL = "https://www.pornhub.com";
    private final String ClassOfHrefVideos = "thumb";  // элементы класса, отвечающего за ссылки на нужные нам страницы с видео
    private final String ClassOfHeaderVideos = "thumb-under";  // элементы класса, отвечающего за подписи к видео

    public String getNAME() {
        return NAME;
    }

    public String getURL() {
        return URL;
    }

    public String getClassOfHrefVideos() {
        return ClassOfHrefVideos;
    }

    public String getClassOfHeaderVideos() {
        return ClassOfHeaderVideos;
    }
}
