package models;

import play.Logger;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.*;
import java.util.List;

/**
 * @author blackbass <o.salionov@zmeke.com>
 */
@Entity (name = "review_template")
public class ReviewTemplate extends Model {

    @Required
    @MaxSize (199999999)
    public String template;

    @ManyToMany
    @JoinTable (name = "project_review_template",
            inverseJoinColumns = @JoinColumn (name = "project_id"))
    public List<Project> projects;


    public ReviewTemplate(String template) {
        this.template = template;
    }

    public String toString() {
        return template;
    }

    public static String[] getReviewTemplatesInArray(Project project) {
        Logger.info("project 3 %s\n\n", project.toString());
        Logger.info("project id %s\n\n", project.id);

        List<ReviewTemplate> reviewTemplates = ReviewTemplate
                .find("select r from review_template r inner join r.projects p where p.id=:pid")
                .bind("pid", project.id)
                .fetch();
        String[] variants = new String[reviewTemplates.size()];
        int i = 0;
        for (ReviewTemplate reviewTemplate : reviewTemplates) {
            variants[i] = reviewTemplate.template;
            i++;
        }
        return variants;
    }

}
