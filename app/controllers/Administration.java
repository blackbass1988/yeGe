package controllers;

import models.Dictionary;
import models.Phrase;
import models.PlaceHolder;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

/**
 * @author blackbass <o.salionov@zmeke.com>
 */
public class Administration extends Controller {
    public static void index() {
        render();
    }

    public static void newValue(String value, String type, String parent, Long parentId) {
        Logger.info("hello");
        switch (parent) {
            case "placeholder":
                PlaceHolder placeHolder = PlaceHolder.findById(parentId);
                placeHolder.phrases.add(new Phrase(placeHolder, value));
                placeHolder.save();
                Logger.info(placeHolder.phrases.toString());
                List<Phrase> phraseList = placeHolder.phrases;
                List<Dictionary> dict = Dictionary.getFromPhraseList(phraseList);
                renderJSON(dict);
                break;
            default:
                renderJSON("{}");
        }
    }
}
