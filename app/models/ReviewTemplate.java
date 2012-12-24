package models;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import java.util.List;

/**
 * @author blackbass <o.salionov@zmeke.com>
 */
@Entity(name = "review_template")
public class ReviewTemplate extends Model {

    @Required
    @MaxSize(199999999)
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
