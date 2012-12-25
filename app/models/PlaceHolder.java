package models;

import play.cache.Cache;
import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * @author blackbass <o.salionov@zmeke.com>
 */
@Entity(name = "place_holder")
public class PlaceHolder extends Model {

    @Required
    String name;

    public PlaceHolder(String group) {
        this.name = group;
    }


    public String toString() {
        return name;
    }

    public static PlaceHolder getPlaceHolder(String placeHolder) {
        PlaceHolder pHolder = Cache.get(String.format("place_holder_%s", placeHolder), PlaceHolder.class);
        if (pHolder == null) {
            pHolder = PlaceHolder.find("name = ?", placeHolder).first();
            if (pHolder == null) {
                pHolder = new PlaceHolder(placeHolder);
                pHolder.save();
            }
            Cache.set(String.format("place_holder_%s", placeHolder), pHolder, "10s");
        }
        return pHolder;
    }
}
