package pages;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.AbsPageObject;

import java.time.Duration;

public class AbsPage extends AbsPageObject {
    public AbsPage(WebDriver driver) {
        super(driver);
    }

    private final String BASE_URL = System.getProperty("base.url", "https://otus.ru");

    public void openPage(String path) {
        driver.get(BASE_URL + path);
    }

    public void scrollPageToEnd() {
       /* int windowHeightBefore = driver.manage().window().getPosition().y;
        int windowHeightAfter = windowHeightBefore + 1;*/
        int i = 1500;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(String.format("window.scrollBy(0,%d)", i));
        WebElement element = driver.findElement(By.xpath("//div[@class='dod_new-loader-wrapper js-dod_new-loader-wrapper']"));
//div[@class='dod_new-loader']

        //WebDriverWait webDriverWait = new WebDriverWait(driver,Duration.ofSeconds(5));

        while (!element.isDisplayed()){

                  js.executeScript(String.format("window.scrollBy(0,%d)", i));

        }
        }
}


