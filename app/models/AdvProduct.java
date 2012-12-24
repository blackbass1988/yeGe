package models;

import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.List;

/**
 * @author blackbass <o.salionov@zmeke.com>
 */
@Entity(name = "adv_product")
public class AdvProduct extends Model {
    @Required
    public String name;


    public static String[] getAllProductsInStringAr() {

        List<AdvProduct> products = AdvProduct.all().fetch();
        String[] variants = new String[products.size()];
        int i = 0;
        for (AdvProduct product : products) {
            variants[i] = product.name;
            i++;
        }
        return variants;
    }


    public String toString(){
        return name;
    }
}
