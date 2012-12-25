package controllers;

import models.Project;
import play.data.validation.Valid;
import play.i18n.Messages;
import play.mvc.Controller;

import java.util.List;

public class Projects extends Controller {
    public static void index() {
        List<Project> entities = models.Project.all().fetch();
        render(entities);
    }

    public static void create(Project entity) {
        render(entity);
    }

    public static void show(java.lang.Long id) {
        Project entity = Project.findById(id);
        render(entity);
    }

    public static void edit(java.lang.Long id) {
        Project entity = Project.findById(id);
        render(entity);
    }

    public static void delete(java.lang.Long id) {
        Project entity = Project.findById(id);
        entity.delete();
        index();
    }
    
    public static void save(@Valid Project entity) {
        if (validation.hasErrors()) {
            flash.error(Messages.get("scaffold.validation"));
            render("@create", entity);
        }
        entity.save();
        flash.success(Messages.get("scaffold.created", "Project"));
        index();
    }

    public static void update(@Valid Project entity) {
        if (validation.hasErrors()) {
            flash.error(Messages.get("scaffold.validation"));
            render("@edit", entity);
        }
        
              entity = entity.merge();
        
        entity.save();
        flash.success(Messages.get("scaffold.updated", "Project"));
        index();
    }
}
