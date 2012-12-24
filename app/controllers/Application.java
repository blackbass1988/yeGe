package controllers;

import models.Review;
import play.mvc.Controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Application extends Controller {


    static String productName = "EggPunch";
    static String[] positive = {"Охуенчик", "Вы лучшие!"};
    static String[] positive2 = {"Хардкора", "cисек", "писек", "холодца", "радости зарплату программистам",
            "разрешение на безвизовый въезд", "больше новых уровней" };


    static String[] author = {"Артур", "Timur", "La ji zao", "huida"};
    static String[] phrases = {"${positive}, ваша игра ${name}, даешь больше ${positive2}",
            "${name} - ${positive}", };
    static String[] headers = {"Вау", "Заебись", "Никогда лучше не слыхал!"};
    static Random rand = new Random();

    public static void index() {

        int countOfReviews = 100;
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

        Review review = new Review(author, header, getPhrase());
        review.replace("name", productName);
        review.replace("positive", positive);
        review.replace("positive2", positive2);

        return review;
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
}
