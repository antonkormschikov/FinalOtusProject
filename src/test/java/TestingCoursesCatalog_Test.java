import dev.failsafe.internal.util.Assert;
import factory.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.CatalogPage;
import waiters.Waiters;

public class TestingCoursesCatalog_Test {
    private static final Logger logger = (Logger) LogManager.getLogger(TestingCoursesCatalog_Test.class);

    private WebDriver driver;
    private Waiters waiters;

    @BeforeAll
    public static void manager(){

        //WebDriverManager.chromedriver().setup();
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
            //   driver.close();
            driver.quit();
        }
    }
    @Test
    public void checkCountTestingCourses(){
        CatalogPage catalogPage = new CatalogPage(driver);
        catalogPage.openPage("/");
        catalogPage.openTestingCoursePage();
        catalogPage.assertCountCourses(10, catalogPage.getCourses());

    }

}
