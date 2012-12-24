package models;

import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

/**
 * @author blackbass <o.salionov@zmeke.com>
 */

@Entity(name = "phrase")
public class Phrase extends Model {

    @ManyToOne
    @JoinColumn(name = "place_holder_id")
    @Required
    public PlaceHolder placeHolder;

    @Required
    public String variant;

    public Phrase(String placeHolder, String variant) {
        this.placeHolder = PlaceHolder.getPlaceHolder(placeHolder);
        this.variant = variant;
    }

    public static String[] getAllVariantsForPlaceHolder(String placeHolder) {

        List<Phrase> phraseList = Phrase.find("place_holder_id=?",
                PlaceHolder.getPlaceHolder(placeHolder).id).fetch();
        String[] variants = new String[phraseList.size()];
        int i = 0;
        for (Phrase phrase : phraseList) {
            variants[i] = phrase.variant;
            i++;
        }
        return variants;
    }

    public String toString(){
        return String.format("%s: %s", placeHolder.name, variant);
    }

}
