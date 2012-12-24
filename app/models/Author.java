package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.List;

/**
 * @author blackbass <o.salionov@zmeke.com>
 */
@Entity(name = "author")
public class Author extends Model {
    public String name;

    public static String[] getAllAuthorsInStringArray() {

        List<Author> list = Author.all().fetch();
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
