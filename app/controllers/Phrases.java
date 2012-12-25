package controllers;

import java.util.List;
import models.Phrase;
import play.mvc.Controller;
import play.i18n.Messages;
import play.data.validation.Validation;
import play.data.validation.Valid;

public class Phrases extends Controller {
    public static void index() {
        List<Phrase> entities = models.Phrase.all().fetch();
        render(entities);
    }

    public static void create(Phrase entity) {
        render(entity);
    }

    public static void show(java.lang.Long id) {
        Phrase entity = Phrase.findById(id);
        render(entity);
    }

    public static void edit(java.lang.Long id) {
        Phrase entity = Phrase.findById(id);
        render(entity);
    }

    public static void delete(java.lang.Long id) {
        Phrase entity = Phrase.findById(id);
        entity.delete();
        index();
    }
    
    public static void save(@Valid Phrase entity) {
        if (validation.hasErrors()) {
            flash.error(Messages.get("scaffold.validation"));
            render("@create", entity);
        }
        entity.save();
        flash.success(Messages.get("scaffold.created", "Phrase"));
        index();
    }

    public static void update(@Valid Phrase entity) {
        if (validation.hasErrors()) {
            flash.error(Messages.get("scaffold.validation"));
            render("@edit", entity);
        }
        
              entity = entity.merge();
        
        entity.save();
        flash.success(Messages.get("scaffold.updated", "Phrase"));
        index();
    }
}
