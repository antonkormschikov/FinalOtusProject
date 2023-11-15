package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class CalendarPage extends AbsPage{
    public CalendarPage (WebDriver driver) {super(driver);}
    private static final Logger logger = (Logger) LogManager.getLogger(CalendarPage.class);

    public void goToCalendar(){
        WebElement popUpEdu = driver.findElement(By.xpath("//div[span[@title='Обучение']]"));
        waiters.waitElementVisible(popUpEdu);
        new Actions(driver).moveToElement(popUpEdu).build().perform();
        driver.findElement(By.xpath("//a[text()='Календарь мероприятий']")).click();
    }

    public void assertFutureEventDate(){
        List<WebElement> elements = driver.findElements(By.xpath("//a[@class='dod_new-event']"));
        String link="";
        String dateElement="";
        System.out.println(elements.size());
        for (WebElement element : elements) {
            link=element.getAttribute("href");
            dateElement=driver.findElement(By.xpath
                    (String.format("//a[@href='%s' and @class='dod_new-event']" +
                            "/div/div[@class='dod_new-event__bottom']/div/span[1]" +
                            "/span[@class='dod_new-event__date-text']",link))).getText();
            System.out.println(String.format("link=%s || date=%s",link,dateElement ));


        }

    }



}

