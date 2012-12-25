package models;

import models.Phrase;
import models.ReviewTemplate;

import java.lang.ClassValue;
import java.lang.Long;
import java.lang.String;
import java.util.List;
import java.util.ArrayList;

/**
 * @author blackbass <o.salionov@zmeke.com>
 */
public class Dictionary {

    public static List<Dictionary> getFromPhraseList(List<Phrase> phraseList) {
        List<Dictionary> dictionary = new ArrayList<>();
        for (Phrase phrase: phraseList) {
            dictionary.add(new Dictionary(phrase.id, phrase.variant));
        }

        return dictionary;
    }

    public Long id;
    public String value;

    public Dictionary(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public static List<Dictionary> getFromReviewTemplates(List<ReviewTemplate> templates) {
        List<Dictionary> dictionary = new ArrayList<>();
        for (ReviewTemplate template: templates) {
            dictionary.add(new Dictionary(template.id, template.template));
        }

        return dictionary;
    }
}
