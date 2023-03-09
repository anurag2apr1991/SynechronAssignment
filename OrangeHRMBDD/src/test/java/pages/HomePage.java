package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
	
	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void checkHeader(String expectedHeader) {
		wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(pageNameHeader));
		Assert.assertTrue(pageNameHeader.getText().trim().contains(expectedHeader));
	}
}
