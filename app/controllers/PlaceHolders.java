package controllers;

import java.util.List;
import models.PlaceHolder;
import play.mvc.Controller;
import play.i18n.Messages;
import play.data.validation.Validation;
import play.data.validation.Valid;

public class PlaceHolders extends Controller {
    public static void index() {
        List<PlaceHolder> entities = models.PlaceHolder.all().fetch();
        render(entities);
    }

    public static void create(PlaceHolder entity) {
        render(entity);
    }

    public static void show(java.lang.Long id) {
        PlaceHolder entity = PlaceHolder.findById(id);
        render(entity);
    }

    public static void edit(java.lang.Long id) {
        PlaceHolder entity = PlaceHolder.findById(id);
        render(entity);
    }

    public static void delete(java.lang.Long id) {
        PlaceHolder entity = PlaceHolder.findById(id);
        entity.delete();
        index();
    }
    
    public static void save(@Valid PlaceHolder entity) {
        if (validation.hasErrors()) {
            flash.error(Messages.get("scaffold.validation"));
            render("@create", entity);
        }
        entity.save();
        flash.success(Messages.get("scaffold.created", "PlaceHolder"));
        index();
    }

    public static void update(@Valid PlaceHolder entity) {
        if (validation.hasErrors()) {
            flash.error(Messages.get("scaffold.validation"));
            render("@edit", entity);
        }
        
              entity = entity.merge();
        
        entity.save();
        flash.success(Messages.get("scaffold.updated", "PlaceHolder"));
        index();
    }
}
