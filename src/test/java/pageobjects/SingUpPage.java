package pageobjects;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SingUpPage extends BasePage {

	public SingUpPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//a[@id='Header_button_link_Sign Up--false']")
	private WebElement buttonSignupOnHomepage;

	@FindBy(xpath = "//input[@id='formik-input__input--fullName']")
	private WebElement textYourName;

	@FindBy(xpath = "//input[@id='phoneNumber']")
	private WebElement textPhoneNumber;

	@FindBy(xpath = "//input[@id='formik-input__input--email']")
	private WebElement textEmailAdress;

	@FindBy(xpath = "//span[@id='formik-form__radio-button--male--selector']")
	private WebElement genderMale;

	@FindBy(xpath = "//span[@id='formik-form__radio-button--female--selector']")
	private WebElement genderFemale;

	@FindBy(xpath = "//input[@id='date-input__input']")
	private WebElement birthDateButton;

	@FindBy(xpath = "//button[contains(text(),'‹')]")
	private WebElement yearBackButton;

	@FindBy(xpath = "//div[@class='react-calendar__decade-view__years']")
	private WebElement yearsMatrix;

	@FindBy(xpath = "//div[@class='react-calendar__decade-view__years']//button")
	private List<WebElement> listYear;

	@FindBy(xpath = "//div[@class='react-calendar__viewContainer']//button")
	private List<WebElement> listMonth;

	@FindBy(xpath = "//div[@class='react-calendar__month-view__days']//button")
	private List<WebElement> listDay;

	@FindBy(xpath = "//input[@id='formik-input__input--password']")
	private WebElement txtPassword;

	@FindBy(xpath = "//button[contains(@id,'Generated_Button_ID_954')]")
	private WebElement butonJoinNow;

	public void clickOnSignUp() {

		buttonSignupOnHomepage.click();

	}

	public void adddata(String name, String phonenumber, String email) {
		textYourName.sendKeys(name);
		textPhoneNumber.sendKeys(phonenumber);
		textEmailAdress.sendKeys(email);

	}

	public void selectGender(char gender) {
		switch (gender) {
		case 'F':
		case 'f': {
			genderFemale.click();
			break;
		}
		case 'M':
		case 'm': {
			genderMale.click();
			break;
		}
		}

	}

	public void clickonYearBackButton() {
		yearBackButton.click();

	}

	public void clickonBirthdate() {
		birthDateButton.click();
	}

	public void selectMonth(String month) {
		for (WebElement months : listMonth) {
			if (months.getText().equalsIgnoreCase(month)) {
				months.click();
				return;
			}
		}
		throw new NoSuchElementException("Month not found: " + month);
	}

	public void selectDay(String day) {
		for (WebElement days : listDay) {
			if (days.getText().equals(day)) {
				days.click();
				return;
			}
		}
		throw new NoSuchElementException("Day not found: " + day);
	}

	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}

	public void clickonJoinNow() {
		butonJoinNow.click();
	}

	public void findYear(String year) {
		boolean repeat = true;
		birthDateButton.click();
		while (repeat) {
			for (WebElement years : listYear) {
				if (years.getText().contains(year)) {
					years.click();
					repeat = false;
				} else {
					yearBackButton.click();
				}

			}

		}

	}

	public void addBirthDate(String year, String month, String day) {
		findYear(year);
		selectMonth(month);
		selectDay(day);
	}
}
