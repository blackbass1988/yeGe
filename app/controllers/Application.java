package controllers;

import models.Review;
import play.mvc.Controller;

import java.util.LinkedList;
import java.util.List;

import static models.AdvProduct.getAllProductsInStringAr;
import static models.Author.getAllAuthorsInStringArray;
import static models.Header.getAllHeadersInStringArray;
import static models.ReviewTemplate.getReviewTemplatesInArray;
import static utils.StringUtils.getRandomFromArray;

public class Application extends Controller {


    static String[] productName = {"EggPunch", "FunnyLandia", "Texas Poker"};
    static String[] author = {"Артур", "Timur", "La ji zao", "Felix", "Oleg",  "Sergey", "Alex", "Sandr"};
//    static String[] phrases = {"${positive}, ваша игра ${name} ${howIt}, даешь ${positive2}",
//            "${name} - ${positive}",
//            "нуежели ${name} раньше не могли выпустить? Даешь ${positive2}, ${positive2}, ${positive2}, ${positive2}"
//    };

    static String[] headers = {"Вау", "Заебись", "Никогда лучше не слыхал!"};


    public static void index() {

        int countOfReviews = 10;
        List<Review> reviews = new LinkedList<>();
        for (int i = 0; i<countOfReviews; i++){
            reviews.add(generateReview());
        }

        render(reviews);
    }

    static Review generateReview() {

        String header = getHeader();
        String author = getAuthor();
        Review review = new Review(getProductName(), author, header, getPhrase());
        review.replaceAll();
        review.cleanPunctuation();
        return review;
    }

    private static String getProductName() {
        return getRandomFromArray(getAllProductsInStringAr());
    }

    private static String getPhrase() {
        return getRandomFromArray(getReviewTemplatesInArray());
    }


    private static String getAuthor() {
        return getRandomFromArray(getAllAuthorsInStringArray());
    }

    private static String getHeader() {
        return getRandomFromArray(getAllHeadersInStringArray());
    }
}
