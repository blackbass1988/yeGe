package models;

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
        PlaceHolder pHolder = PlaceHolder.find("name = ?", placeHolder).first();
        if (pHolder == null) {
            pHolder = new PlaceHolder(placeHolder);
            pHolder.save();
        }
        return pHolder;
    }
}
