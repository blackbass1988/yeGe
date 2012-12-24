package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.List;

/**
 * @author blackbass <o.salionov@zmeke.com>
 */
@Entity(name = "review_template")
public class ReviewTemplate extends Model {

    public String template;

    public ReviewTemplate(String template) {
        this.template = template;
    }

    public String toString() {
        return template;
    }

    public static String[] getReviewTemplatesInArray() {
        List<ReviewTemplate> reviewTemplates = ReviewTemplate.all().fetch();
        String[] variants = new String[reviewTemplates.size()];
        int i = 0;
        for (ReviewTemplate reviewTemplate : reviewTemplates) {
            variants[i] = reviewTemplate.template;
            i++;
        }
        return variants;
    }

}
