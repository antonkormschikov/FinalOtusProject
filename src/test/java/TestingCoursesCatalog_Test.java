import dev.failsafe.internal.util.Assert;
import factory.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.CatalogPage;
import pages.CourcePage;
import waiters.Waiters;

import java.time.Duration;
import java.util.List;

public class TestingCoursesCatalog_Test {
    private static final Logger logger = (Logger) LogManager.getLogger(TestingCoursesCatalog_Test.class);

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
    //@Disabled
    @Test
    public void CountTestingCoursesTest(){
        CatalogPage catalogPage = new CatalogPage(driver);
        catalogPage.openPage("/catalog/courses?categories=testing");
       // catalogPage.openTestingCoursePage();
        catalogPage.assertCountCourses(10);
    }
    @Test
    public void courseCardTest(){
        CatalogPage catalogPage = new CatalogPage(driver);
        catalogPage.openPage("/catalog/courses?categories=testing");
        new CourcePage(driver).assertCorrectCoursesDataInfo();



    }


}
