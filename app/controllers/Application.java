package controllers;

import models.Review;
import play.cache.Cache;
import play.mvc.Controller;

import java.util.LinkedList;
import java.util.List;

import static models.AdvProduct.getAllProductsInStringAr;
import static models.Author.getAllAuthorsInStringArray;
import static models.Header.getAllHeadersInStringArray;
import static models.ReviewTemplate.getReviewTemplatesInArray;
import static utils.StringUtils.getRandomFromArray;

public class Application extends Controller {

    public static void index() {
        Cache.clear();
        Integer countOfReviews = params.get("count", Integer.class);
        if (countOfReviews == null) {
            countOfReviews = 10;
        }
        List<Review> reviews = new LinkedList<>();
        for (int i = 0; i<countOfReviews; i++){
            reviews.add(generateReview());
        }

        render(reviews);
    }

    public static void info() {
        render();
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
        String productName = params.get("name");
        if (productName == null || productName.isEmpty()) {
            productName = getRandomFromArray(getAllProductsInStringAr());
        }
        return productName;
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
