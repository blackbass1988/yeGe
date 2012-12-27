package models;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static models.Phrase.getAllVariantsForPlaceHolder;
import static models.ReviewTemplate.getReviewTemplatesInArray;
import static utils.StringUtils.getRandomFromArray;

/**
 * @author blackbass <o.salionov@zmeke.com>
 */
public class Review {
    String name;
    String header;
    String comment;
    Project project;

    public Review(Project project, String author, String header) {
        this.project = project;
        this.name = author;
        this.header = header;
        this.comment = getPhrase();
        replaceAll("name", project.name);
    }

    private String getPhrase() {
        return getRandomFromArray(getReviewTemplatesInArray(project));
    }

    public void replaceAll(String placeHolder, String placeHolderValue) {
        comment = comment.replaceAll(String.format("\\$\\{%s\\}", placeHolder), placeHolderValue);
    }

    public void cleanPunctuation() {
        comment = comment.replaceAll("([^a-z0-9A-Zа-яА-Я${} ])([^a-z0-9A-Zа-яА-Я${} ])+", "$1");
        Pattern pattern = Pattern.compile("([!\\?\\.])(\\ *)([a-zа-я])");
        Matcher matcher = pattern.matcher(comment);
        while (matcher.find()) {
            comment = comment.replace(matcher.group(), String.format("%s %s", matcher.group(1),
                    matcher.group(3)).toUpperCase());
        }
        pattern = Pattern.compile("([,;:-])(\\ *)([A-ZА-Я])");
        matcher = pattern.matcher(comment);
        while (matcher.find()) {
            comment = comment.replace(matcher.group(), String.format("%s %s", matcher.group(1),
                    matcher.group(3)).toLowerCase());
        }


//        comment =  comment.replaceAll("(?s)(?<=[!\\?\\.])\\ *([a-zа-я])", " $1".toUpperCase());
    }

    public void replaceAll() {
        List<PlaceHolder> placeHolders = getAllPlaceHolders();
        for (PlaceHolder placeHolder : placeHolders) {
            String[] variandsForPlaceHolder = getAllVariantsForPlaceHolder(placeHolder.name);
            String replaceByThis = getRandomFromArray(variandsForPlaceHolder);
            if (!replaceByThis.isEmpty()) {
                comment = comment.replaceFirst(String.format("\\$\\{%s\\}", placeHolder.name), getRandomFromArray(variandsForPlaceHolder));
            }
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

    public String getComment() {
        return comment;
    }
}
