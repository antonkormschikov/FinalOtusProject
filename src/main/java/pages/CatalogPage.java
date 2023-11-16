package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.checkerframework.framework.qual.DefaultQualifier;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CatalogPage extends AbsPage {
//    private static final Logger logger = (Logger) LogManager.getLogger(CatalogPage.class);
    public CatalogPage (WebDriver driver) {
        super(driver);
    }

    public void openTestingCoursePage(){
    WebElement element = driver.findElement(By.xpath("//section/div/div/div[text()='Тестирование']"));
    new Actions(driver).moveToElement(element).build().perform();
    element.click();

    }

    public List<WebElement> getCourses(){
     //   WebElement element =driver.findElement(By.xpath("//h1/div[text()='Каталог']"));
       // new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(element));
        return driver.findElements(By.xpath("//main/div/section/div/div/a"));
    }

    public void assertCountCourses(int countCourses){

        Assertions.assertEquals(countCourses,getCourses().size());
    }




}
