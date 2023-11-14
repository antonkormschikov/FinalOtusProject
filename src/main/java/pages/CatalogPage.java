package pages;

import org.checkerframework.framework.qual.DefaultQualifier;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CatalogPage extends AbsPage {
    public CatalogPage (WebDriver driver) {
        super(driver);
    }

    public void openTestingCoursePage(){
       WebElement element= driver.findElement(By.xpath("//section/div/div/div[text()='Тестирование']"));
       waiters.waitElementVisible(element);
       element.click();
    }

    public List getCourses(){
        List<WebElement> elements = driver.findElements(By.xpath("//main/div/section/div/div/a"));
        for (WebElement element : elements) {
            System.out.println(element.getAttribute("href").toString());

        }

        return elements;
    }

    public void assertCountCourses(int coursesCount,List coursesList){
        Assertions.assertEquals(coursesCount,coursesList.size());
    }


}
