package controllers;

import models.Phrase;
import models.PlaceHolder;
import play.Logger;
import play.mvc.Controller;

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
                Phrase phrase = new Phrase(placeHolder, value);
                phrase.save();
                placeHolder.refresh();
                renderJSON(placeHolder.phrases);
//                break;
            default:
                renderJSON("{}");
        }
    }
}
