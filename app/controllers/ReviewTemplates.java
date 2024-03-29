package controllers;

import java.util.List;
import models.ReviewTemplate;
import play.mvc.Controller;
import play.i18n.Messages;
import play.data.validation.Validation;
import play.data.validation.Valid;

public class ReviewTemplates extends Controller {
    public static void index() {
        List<ReviewTemplate> entities = models.ReviewTemplate.all().fetch();
        render(entities);
    }

    public static void create(ReviewTemplate entity) {
        render(entity);
    }

    public static void show(java.lang.Long id) {
        ReviewTemplate entity = ReviewTemplate.findById(id);
        render(entity);
    }

    public static void edit(java.lang.Long id) {
        ReviewTemplate entity = ReviewTemplate.findById(id);
        render(entity);
    }

    public static void delete(java.lang.Long id) {
        ReviewTemplate entity = ReviewTemplate.findById(id);
        entity.delete();
        index();
    }
    
    public static void save(@Valid ReviewTemplate entity) {
        if (validation.hasErrors()) {
            flash.error(Messages.get("scaffold.validation"));
            render("@create", entity);
        }
        entity.save();
        flash.success(Messages.get("scaffold.created", "ReviewTemplate"));
        index();
    }

    public static void update(@Valid ReviewTemplate entity) {
        if (validation.hasErrors()) {
            flash.error(Messages.get("scaffold.validation"));
            render("@edit", entity);
        }
        
              entity = entity.merge();
        
        entity.save();
        flash.success(Messages.get("scaffold.updated", "ReviewTemplate"));
        index();
    }
}
