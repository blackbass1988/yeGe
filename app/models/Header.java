package models;

import play.cache.Cache;
import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.List;

/**
 * @author blackbass <o.salionov@zmeke.com>
 */
@Entity(name = "header")
public class Header extends Model {
    @Required
    public String name;

    public static String[] getAllHeadersInStringArray() {

        List<Header> list = Cache.get("headers_list", List.class);
        if (list == null) {
            list = Header.all().fetch();
            Cache.set("headers_list", list, "10s");
        }
        String[] variants = new String[list.size()];
        int i = 0;
        for (Header product : list) {
            variants[i] = product.name;
            i++;
        }
        return variants;
    }


    public String toString(){
        return name;
    }
}
