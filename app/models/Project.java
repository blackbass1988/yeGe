package models;

import play.cache.Cache;
import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author blackbass <o.salionov@zmeke.com>
 */
@Entity (name = "project")
public class Project extends Model {
    @Required
    public String name;

    @ManyToMany (cascade = CascadeType.PERSIST)
    @JoinTable (name = "project_review_template",
            inverseJoinColumns = @JoinColumn (name = "review_template_id"))
    public List<ReviewTemplate> templates;

    public Project(String name) {
        this.name = name;
        templates = new ArrayList<>();
    }

    public static String[] getAllProductsInStringAr() {

        List<Project> products = Cache.get("adv_product_list", List.class);
        if (products == null) {
            products = Project.all().fetch();
            Cache.set("adv_product_list", products, "10s");
        }
        String[] variants = new String[products.size()];
        int i = 0;
        for (Project product : products) {
            variants[i] = product.name;
            i++;
        }
        return variants;
    }


    public String toString() {
        return String.format("%s:{%s}", name, templates);
    }
}
