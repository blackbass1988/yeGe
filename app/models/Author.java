package models;

import play.cache.Cache;
import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.List;

/**
 * @author blackbass <o.salionov@zmeke.com>
 */
@Entity(name = "author")
public class Author extends Model {
    @Required
    public String name;

    public static String[] getAllAuthorsInStringArray() {

        List<Author> list = Cache.get("authors_list", List.class);
        if (list == null) {
            list = Author.all().fetch();
            Cache.set("authors_list", list, "10s");
        }
        String[] variants = new String[list.size()];
        int i = 0;
        for (Author product : list) {
            variants[i] = product.name;
            i++;
        }
        return variants;
    }


    public String toString(){
        return name;
    }
}
