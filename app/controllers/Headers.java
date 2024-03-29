package controllers;

import java.util.List;
import models.Header;
import play.mvc.Controller;
import play.i18n.Messages;
import play.data.validation.Validation;
import play.data.validation.Valid;

public class Headers extends Controller {
    public static void index() {
        List<Header> entities = models.Header.all().fetch();
        render(entities);
    }

    public static void create(Header entity) {
        render(entity);
    }

    public static void show(java.lang.Long id) {
        Header entity = Header.findById(id);
        render(entity);
    }

    public static void edit(java.lang.Long id) {
        Header entity = Header.findById(id);
        render(entity);
    }

    public static void delete(java.lang.Long id) {
        Header entity = Header.findById(id);
        entity.delete();
        index();
    }
    
    public static void save(@Valid Header entity) {
        if (validation.hasErrors()) {
            flash.error(Messages.get("scaffold.validation"));
            render("@create", entity);
        }
        entity.save();
        flash.success(Messages.get("scaffold.created", "Header"));
        index();
    }

    public static void update(@Valid Header entity) {
        if (validation.hasErrors()) {
            flash.error(Messages.get("scaffold.validation"));
            render("@edit", entity);
        }
        
              entity = entity.merge();
        
        entity.save();
        flash.success(Messages.get("scaffold.updated", "Header"));
        index();
    }
}
