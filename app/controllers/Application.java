package controllers;

import models.Dictionary;
import models.Project;
import models.Review;
import play.Logger;
import play.cache.Cache;
import play.mvc.Controller;

import java.util.LinkedList;
import java.util.List;

import static models.Author.getAllAuthorsInStringArray;
import static models.Header.getAllHeadersInStringArray;
import static utils.StringUtils.getRandomFromArray;

public class Application extends Controller {

    public static void index() {
        Cache.clear();
        Project activeProject = null;
        Long projectId = params.get("project", Long.class);
        if (projectId != null) {
            activeProject = Project.find("id=?", projectId).first();
        }
        List<Project> projects = Project.all().fetch();
        if (activeProject == null) {
            activeProject = Project.all().first();
        }

        Integer countOfReviews = params.get("count", Integer.class);
        if (countOfReviews == null) {
            countOfReviews = 10;
        }
        List<Review> reviews = new LinkedList<>();
        Logger.info("acount of res %s", countOfReviews);
        for (int i = 0; i<countOfReviews; i++){
            Logger.info("active project %s", activeProject);
            Review review = generateReview(activeProject);
//            if (!review.getComment().isEmpty()) {
                reviews.add(review);
//            }
        }
        Logger.info("reviews : %s", reviews);
        render(reviews, projects, activeProject);
    }

    public static void info() {
        render();
    }

    public static void getTempatesForProject(Long project){
        Project proj = Project.findById(project);
        if (proj != null) {
            List<Dictionary> dict = Dictionary.getFromReviewTemplates(proj.templates);
            renderJSON(dict);
        } else {
            renderJSON("{}");
        }
    }

    static Review generateReview(Project activeProject) {
        String header = getHeader();
        String author = getAuthor();
        Review review = new Review(activeProject, author, header);
        review.replaceAll();
        review.cleanPunctuation();
        return review;
    }

    private static String getAuthor() {
        return getRandomFromArray(getAllAuthorsInStringArray());
    }

    private static String getHeader() {
        return getRandomFromArray(getAllHeadersInStringArray());
    }
}
