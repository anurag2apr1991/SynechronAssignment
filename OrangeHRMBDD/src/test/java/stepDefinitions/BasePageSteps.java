package stepDefinitions;

import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import Utils.DriverManager;
import pages.BasePage;

public class BasePageSteps {

	protected WebDriver driver = DriverManager.getDriver();
	protected BasePage basePage;
	
	@Then ("all the button works at the home page")
	public void all_the_button_works_at_the_home_page() {
		basePage = new BasePage(driver);
		basePage.checkBasePageElements();
	}
	
	@Then ("user is logout")
	public void user_is_logout() {
		basePage.logout();
	}
}
