package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//a[@id='Header_nav_link_Login--false']")
	private WebElement loginHomePage;

	@FindBy(xpath = "//input[@id='formik-input__input--email']")
	private WebElement txtEmail;

	@FindBy(xpath = "//button[contains(@id,'Generated_Button_ID')]")
	private WebElement buttonLogin;

	@FindBy(xpath = "//span[@id='login-page__remeber-me-checkbox--checkmark']")
	private WebElement checkRemberMe;

	@FindBy(xpath = "//input[@id='formik-input__input--password']")
	private WebElement txtPassword;

	// span[@class='ActiveUserWidgetstyle__Trigger-sc-1j8nb09-1 jccCbi']
	
	
	//span[contains(@class,'ActiveUserWidgetstyle__Trigger')]

	@FindBy(xpath = "//span[contains(@class,'ActiveUserWidgetstyle__Trigger')]")
	private WebElement username;

	public void setEmail(String email) {
		txtEmail.sendKeys(email);

	}

	public void clickOnLoginHomePage() {
		loginHomePage.click();
	}

	public void setPassword(String password) {
		txtPassword.sendKeys(password);

	}

	public void clickonLogin() {
		buttonLogin.click();

	}

	public void testcheckRememberMe() {
		if (!checkRemberMe.isSelected()) {
			checkRemberMe.click();

		}
	}

	@FindBy(xpath = "//span[contains(text(),'Logout')]")
	private WebElement logout;

	public void validafterLogin() {
		username.click();
		System.out.println(username.getText());
		
		if (logout.isDisplayed()) {
			Assert.assertTrue(true);
			
		}
		else

		Assert.fail();

	}
}
