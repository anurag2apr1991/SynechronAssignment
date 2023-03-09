package hooks;

import io.cucumber.java.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import Utils.DriverManager;

import static Utils.ConfigReader.getProperty;

public class AppHooks {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get(getProperty("url"));
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            String screenshotPath = "target/screenshots" + screenshotName + ".png";
            try {
                byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Screenshot");
                FileUtils.copyFile(((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE),
                        new File(screenshotPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        DriverManager.quitDriver();
    }
}
