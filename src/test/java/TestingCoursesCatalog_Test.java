import factory.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.CalendarPage;
import pages.CatalogPage;
import pages.CourcePage;
import waiters.Waiters;


public class TestingCoursesCatalog_Test {
    private Logger logger = (Logger) LogManager.getLogger(TestingCoursesCatalog_Test.class);

    private WebDriver driver;
    private Waiters waiters;

    @BeforeAll
    public static void manager(){

        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
    }
    @BeforeEach
    public void init(){

        driver = new WebDriverFactory().create();
        this.waiters = new Waiters(driver);
    }
    @AfterEach
    public void close(){
        if (driver != null) {
               driver.close();
            driver.quit();
        }
    }

    @Test
    public void countTestingCoursesTest(){logger.info("----Тест проверка количества курсов---");
        CatalogPage catalogPage = new CatalogPage(driver);
        catalogPage.openPage("/catalog/courses?categories=testing");
        catalogPage.assertCountCourses(10);
    }

    @Test
    public void courseCardTest(){logger.info("----Тест Валидация информации в карточке курса---");
        CatalogPage catalogPage = new CatalogPage(driver);
        catalogPage.openPage("/catalog/courses?categories=testing");
        new CourcePage(driver).assertCorrectCoursesDataInfo();
    }

    @Test
    public void calendarDateValidateTest(){ logger.info("----Тест Валидация дат предстоящих событий---");
        CalendarPage calendarPage = new CalendarPage(driver);
        calendarPage.openPage("/");
        calendarPage.goToCalendar();
        calendarPage.scrollPageToEnd();
        calendarPage.assertFutureEventDate();
    }

    @Test
    public void checkTypeEvent(){logger.info("----Тест Валидация типа предстоящих событий(тип Открытый вебинар)---");
        CalendarPage calendarPage = new CalendarPage(driver);
        calendarPage.openPage("/");
        calendarPage.goToCalendar();
        calendarPage.changeEventType();
        calendarPage.scrollPageToEnd();
        calendarPage.assertEventType("Открытый вебинар");
    }


}
