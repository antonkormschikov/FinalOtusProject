package pages;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.AbsPageObject;

import java.time.Duration;
import java.util.List;

public class AbsPage extends AbsPageObject {
    public AbsPage(WebDriver driver) {
        super(driver);
    }

    private final String BASE_URL = System.getProperty("base.url", "https://otus.ru");

    public void openPage(String path) {
        driver.get(BASE_URL + path);
    }

    public void scrollPageToEnd() {
        List<WebElement> elements = driver.findElements(By.xpath("//a[@class='dod_new-event']"));
        int countElementsBefore=elements.size();
        int countElementsAfter=35;
        int pixels = 5000;
        JavascriptExecutor js = (JavascriptExecutor) driver;

        while (countElementsBefore<countElementsAfter){
        countElementsBefore=countElementsAfter;
                  js.executeScript(String.format("window.scrollBy(0,%d)", pixels));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            countElementsAfter=driver.findElements(By.xpath("//a[@class='dod_new-event']")).size();
        }
        }
}


