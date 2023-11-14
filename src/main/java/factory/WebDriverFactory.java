package factory;
import data.BrowserData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverFactory {
    private final String BROWSER_NAME = System.getProperty("browser","firefox");


    public WebDriver create() {
        BrowserData browserData = BrowserData.valueOf(BROWSER_NAME.toUpperCase());
        switch (browserData) {
            case CHROME: {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                return new ChromeDriver(chromeOptions);
            }
            case FIREFOX: {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                return  new FirefoxDriver(firefoxOptions);
            }
        }

 return null;
}
}