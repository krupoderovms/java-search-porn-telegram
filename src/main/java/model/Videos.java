package model;

import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;

/**
 * Created by javastream on 26.02.2019.
 */
public class Videos {

    private ArrayList<Message> message;
    private ArrayList<String> arrayCaptions;
    private ArrayList<String> arrayHref;
    private ArrayList<String> arrUrlImg;
    private ArrayList<String> urlMP4;
    private String endOfArray;

    public Videos() {
        this.message = new ArrayList<Message>();
        this.arrayCaptions = new ArrayList<String>();
        this.arrayHref = new ArrayList<String>();
        this.arrUrlImg = new ArrayList<String>();
        this.urlMP4 = new ArrayList<String>();
    }

    public ArrayList<Message> getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message.add(message);
    }

    public ArrayList<String> getArrayCaptions() {
        return arrayCaptions;
    }

    public void setArrayCaptions(String arrayCaptions) {
        this.arrayCaptions.add(arrayCaptions);
    }

    public ArrayList<String> getArrayHref() {
        return arrayHref;
    }

    public void setArrayHref(String arrayHref) {
        this.arrayHref.add(arrayHref);
    }

    public ArrayList<String> getArrUrlImg() {
        return arrUrlImg;
    }

    public void setArrUrlImg(String arrUrlImg) {
        this.arrUrlImg.add(arrUrlImg);
    }

    public ArrayList<String> getUrlMP4() {
        return urlMP4;
    }

    public void setUrlMP4(String urlMP4) {
        this.urlMP4.add(urlMP4);
    }

    public String getEndOfArray() {
        return endOfArray;
    }

    public void setEndOfArray(String endOfArray) {
        this.endOfArray = endOfArray;
    }
}
