package models;

import play.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static models.Phrase.getAllVariantsForPlaceHolder;
import static utils.StringUtils.getRandomFromArray;

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

    public Review(String productName, String author, String header, String comment) {
        this.name = author;
        this.header = header;
        this.comment = comment;
        replaceAll("name", productName);
    }

    public void replaceAll(String placeHolder, String placeHolderValue) {
        comment = comment.replaceAll(String.format("\\$\\{%s\\}", placeHolder), placeHolderValue);
    }

    public void cleanPunctuation() {
        comment =  comment.replaceAll("([^a-z0-9A-Zа-яА-Я${} ])([^a-z0-9A-Zа-яА-Я${} ])", "$1");
    }

    public void replaceAll() {
        List<PlaceHolder> placeHolders =  getAllPlaceHolders();
        Integer i = 0;
        Logger.info(placeHolders.toString());
        for (PlaceHolder placeHolder: placeHolders) {
            String[] variandsForPlaceHolder =  getAllVariantsForPlaceHolder(placeHolder.name);
            comment = comment.replaceFirst(String.format("\\$\\{%s\\}", placeHolder.name), getRandomFromArray(variandsForPlaceHolder));
        }
    }

    public List<PlaceHolder> getAllPlaceHolders() {
        List<PlaceHolder> placeHolders = new ArrayList<>();
        Pattern pattern = Pattern.compile("(?<=\\$\\{)(\\S+)(?=\\})");
        Matcher match = pattern.matcher(comment);
        while (match.find()) {
            for (int i = 0; i < match.groupCount(); i++) {
                placeHolders.add(new PlaceHolder(match.group(i)));
            }
        }
        return placeHolders;
    }
}
