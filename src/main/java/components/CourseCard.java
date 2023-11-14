package components;

import org.openqa.selenium.WebDriver;
import pages.AbsPage;

public class CourseCard extends AbsPage{
     public CourseCard (WebDriver driver) {
         super(driver);}
    private String name;
    private String description;
    private String courseDuration;
    private String courseFormat;


}
