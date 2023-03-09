package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{
	
	@FindBy (xpath = "//input[@name='username']")
	private WebElement usernameInp;
	
	@FindBy (xpath = "//input[@name='password']")
	private WebElement passwordInp;
	
	@FindBy (xpath = "//button[@type='submit']")
	private WebElement loginBtn;
	
	@FindBy (xpath = "//p[contains(@class,'forgot')]")
	private WebElement forgotPasswordBtn;
	
	@FindBy (xpath = "//p[contains(@class,'alert')]")
	private WebElement invalidLoginMsg;
	
	@FindBy (xpath = "//a[contains(@href,'orangehrm.com')]")
	private WebElement orangeHRMWebsiteLink;
	
	@FindBy (xpath = "//a[contains(@href,'linkedin.com')]")
	private WebElement orangeHRMLinkedInLink;
	
	@FindBy (xpath = "//a[contains(@href,'facebook.com')]")
	private WebElement orangeHRMFacebookLink;
	
	@FindBy (xpath = "//a[contains(@href,'twitter.com')]")
	private WebElement orangeHRMTwitterLink;
	
	@FindBy (xpath = "//a[contains(@href,'youtube.com')]")
	private WebElement orangeHRMYoutubeLink;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void enterUsername(String username) {
		usernameInp.sendKeys(username);
	}
	
	public void enterPassword(String password) {
		passwordInp.sendKeys(password);
	}
	
	public void clickLoginBtn() {
		loginBtn.click();
	}
	
	public void verifyInvalidLoginMsg() {
		Assert.assertTrue(invalidLoginMsg.isDisplayed());
		Assert.assertEquals(invalidLoginMsg.getText().trim(),"Invalid credentials");
	}
	
	public void clickForgotPasswordBtn() {
		forgotPasswordBtn.click();
	}
}
