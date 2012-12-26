package controllers;

import models.*;
import play.Logger;
import play.mvc.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * @author blackbass <o.salionov@zmeke.com>
 */
public class Administration extends Controller {
    public static void index() {
        render();
    }

    public static void newValue(String value, String type, String parent, Long parentId) {
        List<Dictionary> dict = new ArrayList<>();
        switch (parent) {
            case "placeholder":
                PlaceHolder placeHolder = PlaceHolder.findById(parentId);
                placeHolder.phrases.add(new Phrase(placeHolder, value));
                placeHolder.save();
                Logger.info(placeHolder.phrases.toString());
                List<Phrase> phraseList = placeHolder.phrases;
                dict = Dictionary.getFromPhraseList(phraseList);
                renderJSON(dict);
                break;
            case "project":
                Project project = Project.findById(parentId);
                project.templates.add(new ReviewTemplate(value));
                project.save();
                List<ReviewTemplate> reviewTemplates = project.templates;
                dict = Dictionary.getFromReviewTemplates(reviewTemplates);
                renderJSON(dict);
                break;
            default:
                renderJSON(dict);
        }
    }
}
