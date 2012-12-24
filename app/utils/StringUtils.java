package utils;

import java.util.Random;

/**
 * @author blackbass <o.salionov@zmeke.com>
 */
public class StringUtils {
    static Random rand = new Random();

    public static String getRandomFromArray(String[] array){
        if (array.length > 0) {
            return array[rand.nextInt(array.length)];
        } else {
            return "";
        }
    }

}
