package pages;

import org.openqa.selenium.WebDriver;
import pageobject.AbsPageObject;

public class AbsPage extends AbsPageObject {
    public AbsPage(WebDriver driver){
        super(driver);
    }
    private final String BASE_URL=System.getProperty("base.url","https://otus.ru");
    public void openPage(String path){
        driver.get(BASE_URL+path);
    }
}
