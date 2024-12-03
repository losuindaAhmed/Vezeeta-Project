package pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class AccountSearchPage extends BasePage {

	public AccountSearchPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// Locators using @FindBy annotation
	@FindBy(xpath = "//span[@id='UserWidgetMenuTrigger']/span")
	private WebElement accountName;

	@FindBy(id = "selectSpecialityDropdown")
	private WebElement specialityDropDownButton;

	@FindBy(css = "span[data-testid='search-bar__dropdown-speciality__value']")
	private WebElement specialitySelectedValue;

	@FindBy(css = "span[data-testid='search-bar__dropdown-city__value']")
	private WebElement citySelectedValue;

	@FindBy(xpath = "//div[@id='generated_envelopeId_speciality']/div/div[2]/span/ul/li")
	private List<WebElement> specialityMenu;

	@FindBy(xpath = "//div[@id='generated_envelopeId_speciality']//ul[@data-testid='search-bar__dropdown-speciality__paginated-menu__list']")
	private WebElement specialityMenuList;

	@FindBy(css = "div.PaginatedMenustyle__PaginationHeader-sc-1r0r53i-3 div[data-testid='search-bar__dropdown-speciality__paginated-menu__arrow--next']")
	private WebElement specialityNextButton;

	@FindBy(css = "div.PaginatedMenustyle__PaginationHeader-sc-1r0r53i-3 div[data-testid='search-bar__dropdown-speciality__paginated-menu__arrow--previous']")
	private WebElement specialityPreviousButton;

	@FindBy(id = "selectCityyDropdown")
	private WebElement cityDropDownButton;

	@FindBy(xpath = "//div[@id='generated_envelopeId_city']/div/div[2]/span/ul/li")
	private List<WebElement> cityMenu;

	@FindBy(xpath = "//div[@id='generated_envelopeId_city']/div/div[2]/span/ul")
	private WebElement cityMenuList;

	@FindBy(css = "div.PaginatedMenustyle__PaginationHeader-sc-1r0r53i-3 div[data-testid='search-bar__dropdown-city__paginated-menu__arrow--next']")
	private WebElement cityNextButton;

	@FindBy(css = "div.PaginatedMenustyle__PaginationHeader-sc-1r0r53i-3 div[data-testid='search-bar__dropdown-city__paginated-menu__arrow--previous']")
	private WebElement cityPreviousButton;

	@FindBy(id = "selectAreaDropdown")
	private WebElement areaDropDownButton;

	@FindBy(xpath = "//ul[@data-testid='search-bar__dropdown-area__paginated-menu__list']/li")
	private List<WebElement> areaList;

	@FindBy(css = "input[data-testid='search-bar__text-input-doctorNameText__text-input']")
	private WebElement searchByNameInput;

	@FindBy(xpath = "//div[@data-testid='search-bar__search-button']")
	private WebElement searchButton;

	// Methods
	public String getAccountName() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(accountName));
		return accountName.getText();
	}

	public String checkSelectedSpeciality() {
		return specialitySelectedValue.getText();
	}

	public String checkSelectedCity() {
		return citySelectedValue.getText();
	}

	private void clickNextPage(WebElement nextButton) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", nextButton);
	}

//	private void clickPreviousPage(WebElement previousButton) {
//		JavascriptExecutor executor = (JavascriptExecutor) driver;
//		executor.executeScript("arguments[0].click();", previousButton);
//	}

	public void selectSpeciality(String requiredSpeciality) {
		boolean repeatFlag = true;
		specialityDropDownButton.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElements(specialityMenu));

		while (repeatFlag) {
			wait.until(ExpectedConditions.visibilityOfAllElements(specialityMenu));
			if (specialityMenuList.getText().contains(requiredSpeciality)) {
				driver.findElement(By.xpath(
						"//ul[@data-testid='search-bar__dropdown-speciality__paginated-menu__list']/li[contains(text(),'"
								+ requiredSpeciality + "')]"))
						.click();
				repeatFlag = false;
			} else {
				clickNextPage(specialityNextButton);
				wait.until(ExpectedConditions.visibilityOfAllElements(specialityMenu));
			}
		}
	}

	public void selectCity(String requiredCity) {
		boolean repeatFlag = true;
		cityDropDownButton.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOfAllElements(cityMenu));

		while (repeatFlag) {
			wait.until(ExpectedConditions.visibilityOfAllElements(cityMenu));
			if (cityMenuList.getText().contains(requiredCity)) {
				driver.findElement(
						By.xpath("//div[@id='generated_envelopeId_city']/div/div[2]/span/ul/li[contains(text(),'"
								+ requiredCity + "')]"))
						.click();
				repeatFlag = false;
			} else {
				clickNextPage(cityNextButton);
				wait.until(ExpectedConditions.visibilityOfAllElements(cityMenu));
			}
		}
	}

	public void selectArea() {
		areaDropDownButton.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElements(areaList));

		Random random = new Random();
		int randomIndex = random.nextInt(1, areaList.size());
		areaList.get(randomIndex).click();
	}

	public void searchByName(String drName) throws InterruptedException {
		searchByNameInput.sendKeys(drName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
		wait.until(ExpectedConditions.attributeToBe(searchByNameInput, "value", drName));
		Thread.sleep(1500);
	}

	public ResultPage clickSearchButton() {
		searchButton.click();
		return new ResultPage(driver);
	}

}
