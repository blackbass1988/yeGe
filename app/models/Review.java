package models;

/**
 * @author blackbass <o.salionov@zmeke.com>
 */
public class Review {
    String name;
    String header;
    String comment;

    public Review(String name, String header, String comment) {
        this.name = name;
        this.header = header;
        this.comment = comment;
    }

    public void replace(String placeHolder, String placeHolderValue) {
        comment = comment.replaceAll(String.format("\\$\\{%s\\}", placeHolder), placeHolderValue);
    }

    public void cleanPunctuation() {
        comment =  comment.replaceAll("([^a-z0-9A-Zа-яА-Я ])([^a-z0-9A-Zа-яА-Я ])", "$1");

    }
}
