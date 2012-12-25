package models;

import play.cache.Cache;
import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * @author blackbass <o.salionov@zmeke.com>
 */
@Entity(name = "place_holder")
public class PlaceHolder extends Model {

    @Required
    public String name;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "place_holder_id")
    public List<Phrase> phrases;


    public PlaceHolder(String group) {
        this.name = group;
        this.phrases = new ArrayList<>();
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
