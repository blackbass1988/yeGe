package models;

import play.cache.Cache;
import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.*;
import java.util.List;

/**
 * @author blackbass <o.salionov@zmeke.com>
 */

@Entity(name = "phrase")
public class Phrase extends Model {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "place_holder_id", insertable=false, updatable=false)
    @Required
    PlaceHolder placeHolder;

    @Required
    public String variant;

    public Phrase(String placeHolder, String variant) {
        this.placeHolder = PlaceHolder.getPlaceHolder(placeHolder);
        this.variant = variant;
    }

    public Phrase(PlaceHolder placeHolder, String variant) {
        this.placeHolder = placeHolder;
        this.variant = variant;
    }


    public static String[] getAllVariantsForPlaceHolder(String placeHolder) {
        List<Phrase> phraseList = Cache.get(String.format("phrase_list_%s", placeHolder), List.class);
        if (phraseList == null) {
            phraseList = Phrase.find("place_holder_id=?",
                    PlaceHolder.getPlaceHolder(placeHolder).id).fetch();
            Cache.set(String.format("phrase_list_%s", placeHolder), phraseList, "10s");
        }
        String[] variants = new String[phraseList.size()];
        int i = 0;
        for (Phrase phrase : phraseList) {
            variants[i] = phrase.variant;
            i++;
        }

        return variants;
    }

    public String toString() {
        return String.format("%s: %s", placeHolder.name, variant);
    }

}
