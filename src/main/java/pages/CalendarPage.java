package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import sun.security.mscapi.CPublicKey;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class CalendarPage extends AbsPage{
    public CalendarPage (WebDriver driver) {super(driver);}
    private static final Logger logger = (Logger) LogManager.getLogger(CalendarPage.class);
    private final String eventLocator="//a[@class='dod_new-event']";
    private final String dateLocatorTemplate="//a[@href='%s' and @class='dod_new-event']/div/div[@class='dod_new-event__bottom']/div/span[1]/span[@class='dod_new-event__date-text']";
    private final String formatLocatorTemplate="//a[@href='%s' and @class='dod_new-event']//div[@class='dod_new-type__text']";

    public void goToCalendar(){
        WebElement popUpEdu = driver.findElement(By.xpath("//div[span[@title='Обучение']]"));
        waiters.waitElementVisible(popUpEdu);
        new Actions(driver).moveToElement(popUpEdu).build().perform();
        driver.findElement(By.xpath("//a[text()='Календарь мероприятий']")).click();
    }
    public LocalDate convertDate(String srcDate){
        String[] monthNames = { "января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря" };
        int month=0;
        int day=Integer.parseInt(srcDate.split(" ")[0]);
        int i=0;
      while (i< monthNames.length){
          if (srcDate.split(" ")[1].equals(monthNames[i])){
              break;
          } else {i++;}
      }
      month=i+1;
      LocalDate localDate ;
      if ((month==1||month==2||month==3)
              &&(LocalDate.now().getMonthValue()==12||LocalDate.now().getMonthValue()==11||LocalDate.now().getMonthValue()==10))
      {
          localDate=LocalDate.of(LocalDate.now().getYear()+1,month,day);

      } else {localDate=LocalDate.of(LocalDate.now().getYear(),month,day);}

        return localDate;
    }

    public void assertFutureEventDate(){
        List<WebElement> elements = driver.findElements(By.xpath(eventLocator));
        String link="";
        String dateElement="";
        System.out.println(elements.size());
        String allExeption="";
        for (WebElement element : elements) {
            link=element.getAttribute("href");
            dateElement=driver.findElement(By.xpath
                    (String.format(dateLocatorTemplate,link))).getText();
            LocalDate localDate;
            try{
                localDate=convertDate(dateElement);
                if (!(LocalDate.now().isEqual(convertDate(dateElement))
                        ||LocalDate.now().isBefore(convertDate(dateElement)))){
                    allExeption+='\n' +String.format("Ошибка даты %s",link) ;
                }
            } catch (Exception e){
                allExeption+='\n' +String.format("Ошибка даты %s исключение: %s",link,e.toString()) ;
            }
            if (allExeption.length()>0) {
                logger.info(allExeption);
            }

            Assertions.assertTrue(allExeption.length()==0,"Date should be after or equal now");

        }

    }

    public void changeEventType(){
        driver.findElement(By.xpath("//div[span[contains(text(),'Ближайшие мероприятия')]]/div")).click();
        driver.findElement(By.xpath("//div[span[contains(text(),'Ближайшие мероприятия')]]/div/div/a[@title='Открытый вебинар']")).click();
    }

    public void assertEventType(){
        List<WebElement> elements = driver.findElements(By.xpath(eventLocator));
        String link="";
        String formatElement="";
        System.out.println(elements.size());
        String allExeption="";
        for (WebElement element : elements) {
            link=element.getAttribute("href");
            try{
            formatElement=driver.findElement(By.xpath
                    (String.format(formatLocatorTemplate,link))).getText();
            if (!formatElement.equals("Открытый вебинар")) {
                allExeption += '\n' + String.format("Ошибка формата события %s", link);
            }
            } catch (Exception e){
                allExeption+='\n' +String.format("Ошибка формата события %s исключение: %s",link,e.toString()) ;
            }
            if (allExeption.length()>0) {
                logger.info(allExeption);
            }

    }



}
}

