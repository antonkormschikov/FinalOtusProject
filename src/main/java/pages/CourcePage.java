package pages;

//import org.assertj.core.api.Assertions;
//import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CourcePage extends AbsPage{
    public CourcePage (WebDriver driver){super(driver);}

    private final String sourceNameLocator = "//h1";
    private final String courseDescriptionLocator="//h1/following::div[1]";
    private final String courseDuration="";
    private final String courseFormat="";

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

}
