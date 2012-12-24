package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.List;

/**
 * @author blackbass <o.salionov@zmeke.com>
 */
@Entity(name = "header")
public class Header extends Model {
    public String name;

    public static String[] getAllHeadersInStringArray() {

        List<Header> list = Header.all().fetch();
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
