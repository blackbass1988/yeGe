package controllers;

import play.mvc.Controller;

/**
 * @author blackbass <o.salionov@zmeke.com>
 */
public class Administration extends Controller {
    public static void index() {
        render();
    }

    public static void newInstance(String type) {
        switch (type) {
            case "author":
                redirect("/authors/list");
            case "header":
                redirect("/headers/list");
            case "phrase":
                redirect("/phrases/list");
            case "placeholder":
                redirect("/placeholders/list");
            case "project":
                redirect("/projects/list");
            case "template":
                redirect("/reviewtemplates/list");
            default: renderText("Неизвестный тип %s", type);
        }

    }
}
