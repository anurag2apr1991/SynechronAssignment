package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;
import Utils.DriverManager;

import static Utils.ConfigReader.*;
import static Utils.Utilities.*;

public class LoginSteps {

	private WebDriver driver = DriverManager.getDriver();
	private LoginPage login;
	private HomePage home;

	@When("user enters username and password")
	public void user_enters_username_and_password() {
		login = new LoginPage(driver);
		login.enterUsername(getProperty("username"));
		login.enterPassword(getProperty("password"));
	}

	@And("user press login button")
	public void user_press_login_button() {
		login.clickLoginBtn();
	}

	@Then("user is navigated to the home page")
	public void user_is_navigated_to_the_home_page() {
		home = new HomePage(driver);
		home.checkHeader("Dashboard");
	}

	@When("user enters invalidUsername and invalidPassword")
	public void user_enters_invalid_username_and_invalid_password() {
		login = new LoginPage(driver);
		login.enterUsername(randomStringGenerator(10));
		login.enterPassword(randomStringGenerator(10));
	}

	@Then("it appears invalid creadential message")
	public void it_appears_invalid_creadential_message() {
		login.verifyInvalidLoginMsg();
	}
	
	@When("user press forgot password button")
	public void user_press_forgot_password_button() {
		login = new LoginPage(driver);
		login.clickForgotPasswordBtn();
	}
}
