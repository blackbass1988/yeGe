package controllers;

import models.Review;
import play.mvc.Controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Application extends Controller {


    static String[] productName = {"EggPunch", "FunnyLandia", "Texas Poker"};

    static String[] positive = {"Охуенчик", "Вы лучшие!"};
    static String[] positive2 = {"Хардкора", "cисек", "писек", "холодца", "радости зарплату программистам",
            "разрешение на безвизовый въезд", "больше новых уровней" };

    static String[] howIt = {"прекрасна", "лучше всех", "разрушает мой мозг", "заставила меня бросить жену и работу" };


    static String[] author = {"Артур", "Timur", "La ji zao", "Felix", "Oleg",  "Sergey", "Alex", "Sandr"};
    static String[] phrases = {"${positive}, ваша игра ${name} ${howIt}, даешь ${positive2}",
            "${name} - ${positive}",
            "нуежели ${name} раньше не могли выпустить? Даешь ${positive2}, ${positive2}, ${positive2}, ${positive2}",

    };
    static String[] headers = {"Вау", "Заебись", "Никогда лучше не слыхал!"};
    static Random rand = new Random();

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
        String positive = getPositive();
        String positive2 = getPositive2();
        String howIt = getHowIt();

        Review review = new Review(author, header, getPhrase());
        review.replace("name", getProductName());
        review.replace("positive", positive);
        review.replace("positive2", positive2);
        review.replace("howIt", howIt);
        review.cleanPunctuation();
        return review;
    }

    private static String getProductName() {
        return getRandomFromArray(productName);
    }

    private static String getPhrase() {
        return getRandomFromArray(phrases);
    }

    private static String getAuthor() {
        return getRandomFromArray(author);
    }

    private static String getRandomFromArray(String[] array){
        return array[rand.nextInt(array.length)];
    }

    private static String getHeader() {
        return getRandomFromArray(headers);
    }

    public static String getPositive() {
        return getRandomFromArray(positive);
    }
    public static String getPositive2() {
        return getRandomFromArray(positive2);
    }

    public static String getHowIt() {
        return getRandomFromArray(howIt);
    }
}
