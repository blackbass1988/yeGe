package controllers;

import models.Author;
import play.data.validation.Valid;
import play.i18n.Messages;
import play.mvc.Controller;

import java.util.List;

public class Authors extends Controller {
    public static void index() {
        List<Author> entities = models.Author.all().fetch();
        render(entities);
    }

    public static void create(Author entity) {
        render(entity);
    }

    public static void show(java.lang.Long id) {
        Author entity = Author.findById(id);
        render(entity);
    }

    public static void edit(java.lang.Long id) {
        Author entity = Author.findById(id);
        render(entity);
    }

    public static void delete(java.lang.Long id) {
        Author entity = Author.findById(id);
        entity.delete();
        index();
    }
    
    public static void save(@Valid Author entity) {
        if (validation.hasErrors()) {
            flash.error(Messages.get("scaffold.validation"));
            render("@create", entity);
        }
        entity.save();
        flash.success(Messages.get("scaffold.created", "Author"));
        index();
    }

    public static void update(@Valid Author entity) {
        if (validation.hasErrors()) {
            flash.error(Messages.get("scaffold.validation"));
            render("@edit", entity);
        }
        
              entity = entity.merge();
        
        entity.save();
        flash.success(Messages.get("scaffold.updated", "Author"));
        index();
    }
}
