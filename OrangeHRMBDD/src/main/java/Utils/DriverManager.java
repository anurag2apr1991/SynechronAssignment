package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class DriverManager {
	
	public static WebDriver driver;
	
	private DriverManager() {}
	
	public static WebDriver getDriver() {
		if(driver == null) {
		String browser = ConfigReader.getProperty("browser");
        boolean headless = Boolean.parseBoolean(ConfigReader.getProperty("headless"));
        int pageLoadTime = Integer.parseInt(ConfigReader.getProperty("pageLoadTime"));
        int implicitWait = Integer.parseInt(ConfigReader.getProperty("implicitWait"));

        if (browser.trim().equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(headless);
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
        } else if (browser.trim().equalsIgnoreCase("firefox")) {
        	WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }else{
            throw new RuntimeException("NO DRIVER FOUND");
        }
        driver.manage().timeouts().pageLoadTimeout(pageLoadTime, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
        driver.manage().window().maximize();
		}
        return driver;
    }
	
    public static void quitDriver(){
        if(driver!=null){
            driver.quit();
            driver=null;
        }
    }
}
