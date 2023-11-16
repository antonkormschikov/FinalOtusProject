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
    @Disabled
    @Test
    public void countTestingCoursesTest(){
        CatalogPage catalogPage = new CatalogPage(driver);
        catalogPage.openPage("/catalog/courses?categories=testing");
       // catalogPage.openTestingCoursePage();
        catalogPage.assertCountCourses(10);
    }

    @Test
    public void courseCardTest(){logger.info("----Тест Валидация информации в карточке курса---");
        CatalogPage catalogPage = new CatalogPage(driver);
        catalogPage.openPage("/catalog/courses?categories=testing");
        new CourcePage(driver).assertCorrectCoursesDataInfo();
    }
    @Disabled
    @Test
    public void calendarDateValidateTest(){ logger.info("----Тест Валидация дат предстоящих событий---");
        CalendarPage calendarPage = new CalendarPage(driver);
        calendarPage.openPage("/");logger.info("1. Переход на главную страницу");
        calendarPage.goToCalendar();logger.info("2. Открытие страницы Календарь мероприятий");
        calendarPage.scrollPageToEnd();logger.info("3. Загрузка всех мероприятий");
        calendarPage.assertFutureEventDate();logger.info("4. Валидация дат предстоящих событий");
    }
    @Disabled
    @Test
    public void checkTypeEvent(){logger.info("----Тест Валидация типа предстоящих событий(тип Открытый вебинар)---");
        CalendarPage calendarPage = new CalendarPage(driver);
        calendarPage.openPage("/");logger.info("----Тест Валидация дат предстоящих событий---");
        calendarPage.goToCalendar();logger.info("----Тест Валидация дат предстоящих событий---");
        calendarPage.changeEventType();logger.info("----Тест Валидация дат предстоящих событий---");
        calendarPage.scrollPageToEnd();logger.info("----Тест Валидация дат предстоящих событий---");
        calendarPage.assertEventType("Открытый вебинар");logger.info("----Тест Валидация дат предстоящих событий---");
    }


}
