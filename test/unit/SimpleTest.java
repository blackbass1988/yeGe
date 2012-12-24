package unit;

import models.Phrase;
import org.junit.Before;
import org.junit.Test;
import play.test.Fixtures;
import play.test.UnitTest;

/**
 * @author blackbass <o.salionov@zmeke.com>
 */
public class SimpleTest extends UnitTest {

    @Before
    public void before() {
        Fixtures.deleteAllModels();
    }

    @Test
    public void test() {
        Phrase phrase1 = new Phrase("positive", "Охуенчик"); phrase1.save();
        Phrase phrase2 = new Phrase("positive", "Вы лучшие!"); phrase2.save();
        Phrase phrase3 = new Phrase("positive2", "Хардкора"); phrase3.save();
        Phrase phrase4 = new Phrase("positive2", "cисек"); phrase4.save();
        Phrase phrase5 = new Phrase("positive2", "писек"); phrase5.save();
        Phrase phrase6 = new Phrase("positive2", "холодца"); phrase6.save();
        Phrase phrase7 = new Phrase("positive2", "разрешение на безвизовый въезд"); phrase7.save();
        Phrase phrase8 = new Phrase("positive2", "больше новых уровней"); phrase8.save();
        Phrase phrase9 = new Phrase("positive2", "зарплату программистам"); phrase9.save();
        Phrase phrase10 = new Phrase("positive2", "радости"); phrase10.save();

        Phrase phrase11 = new Phrase("howIt", "прекрасна"); phrase11.save();
        Phrase phrase12 = new Phrase("howIt", "лучше всех"); phrase12.save();
        Phrase phrase13 = new Phrase("howIt", "разрушает мой мозг"); phrase13.save();
        Phrase phrase14 = new Phrase("howIt", "заставила меня бросить жену и работу"); phrase14.save();

    }
}
