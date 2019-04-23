package model;

/**
 * Портал xxnx.com
 */
public class Xxnx {

    public final String NAME = "xxnx";
    private final String URL = "https://www.xnxx.com";
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


