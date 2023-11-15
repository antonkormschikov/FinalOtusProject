package pages;

//import org.assertj.core.api.Assertions;
//import org.assertj.core.api.SoftAssertions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import pages.CatalogPage;

public class CourcePage extends AbsPage{
    public CourcePage (WebDriver driver){super(driver);}
    private static final  Logger logger = (Logger) LogManager.getLogger(CourcePage.class);
    private final String sourceNameLocator = "//h1";
    private final String courseDescriptionLocator="//h1/following::div[1]";
    private final String courseDuration="//main/div/section/div/div/div[3]/p";
    private final String courseFormat="//main/div/section/div/div/div[4]/p";

    public void checkCard(){
        Assertions.assertTrue(driver.findElement(By.xpath(sourceNameLocator)).isDisplayed()
                                    &&driver.findElement(By.xpath(sourceNameLocator)).getText().length()>0,
                "sourceName is correct");
        Assertions.assertTrue(driver.findElement(By.xpath(courseDescriptionLocator)).isDisplayed()
                        &&driver.findElement(By.xpath(courseDescriptionLocator)).getText().length()>0,
                "courseDescription is correct");
        Assertions.assertTrue(driver.findElement(By.xpath(courseDuration)).isDisplayed()
                        &&driver.findElement(By.xpath(courseDuration)).getText().length()>0,
                "courseDuration is correct");
        Assertions.assertTrue(driver.findElement(By.xpath(courseFormat)).isDisplayed()
                        &&driver.findElement(By.xpath(courseFormat)).getText().length()>0,
                "courseFormat is correct");

    }

    public void assertCorrectCoursesDataInfo(){
        String link="";
        List<String>  hrefs  = new ArrayList<>();
        List<WebElement> coursesList = new CatalogPage(driver).getCourses();
        for (WebElement element:coursesList) {
            hrefs.add(element.getAttribute("href").toString());
        }
        int i=1;
        String allExeption="";
        for (String href:hrefs) {
            driver.get(href);
            try {
                checkCard();
                i++;
            } catch (Exception ex){
                allExeption+='\n' +(String.format("Ошибка на странице %d %s",i,href) + ' '+  ex.toString());
                System.out.println(String.format("Ошибка на странице %d %s",i,href) + ' '+  ex.toString());
            }
          //  if (allExeption.length()>0) {logger.info(allExeption);}
            Assertions.assertFalse(allExeption.length()>0,"info is correct");



        }

    }

}
