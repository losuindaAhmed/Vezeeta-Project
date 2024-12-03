package pageobjects;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ResultPage extends BasePage {

	public ResultPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(css = "span.SortHeaderstyle__SortDesktopDoctors-sc-1ew8wk5-5")
	private WebElement resultDrCounts;

	@FindBy(id = "sort")
	private WebElement sortMenu;

	@FindBy(id = "sort-header__radio-button--2--label")
	private WebElement priceLTH;

	@FindBy(id = "sort-header__radio-button--3--label")
	private WebElement priceHTL;

	@FindBy(xpath = "//div[contains(@id,'doctor-card') and not(.//div[@class='DoctorCardSubComponentsstyle__SponsoredText-sc-1vq3h7c-30 VPBCe'])]")
	private List<WebElement> drsList;

	@FindBy(xpath = "//div[@class='Paginationstyle__PaginationConatiner-sc-4txdoy-0 cJnBek']/a")
	private List<WebElement> pagesList;

	@FindBy(xpath = "//div[contains(@id,'doctor-card_')]")
	private List<WebElement> drCard;

	// Dynamic locators as Strings
	private final String drCardByName = "//h4[contains(text(),'%s')]";
	private final String drCardID = "//div[@data-testid='doctor-card-%d'and not(.//div[@class='DoctorCardSubComponentsstyle__SponsoredText-sc-1vq3h7c-30 VPBCe'])]//span[@data-testid='doctor-card-%d_fees-row_value']";
	private final String requiredPage = "//div[@class='Paginationstyle__PaginationConatiner-sc-4txdoy-0 cJnBek']//div[@id='search-doctors-page__Pagination-page-no--%d']";

	// Methods

	public int checkCountsOfResults() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOf(resultDrCounts));
		String countText = resultDrCounts.getText();
		String numericalPart = countText.replaceAll("\\D+", ""); // Extract numerical part
		return Integer.parseInt(numericalPart);
	}

	public int checkCountsOfDrCards() {
		return drCard.size();
	}

	public void sortPricesIncreasing() {
		sortMenu.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(priceLTH));
		priceLTH.click();
	}

	public void sortPricesDecreasing() {
		sortMenu.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(priceHTL));
		priceHTL.click();
	}

	private int[] getPricesList() {
		int[] pricesList = new int[drsList.size()];

		for (int i = 0; i < drsList.size(); i++) {
			try {
				String dynamicXpath = String.format(drCardID, i, i);
				WebElement priceElement = driver.findElement(By.xpath(dynamicXpath));
				String priceText = priceElement.getText().replace(",", ""); // Remove special characters
				pricesList[i] = Integer.parseInt(priceText);
			} catch (Exception e) {
				System.out.println("No price is visible for doctor card " + i);
				e.printStackTrace();
			}
		}
		return pricesList;
	}

	public boolean isIncreasing() {
		int[] prices = getPricesList();
		for (int i = 0; i < prices.length - 1; i++) {
			if (prices[i] > prices[i + 1])
				return false;
		}
		return true;
	}

	public boolean isDecreasing() {
		int[] prices = getPricesList();
		for (int i = 0; i < prices.length - 1; i++) {
			if (prices[i] < prices[i + 1])
				return false;
		}
		return true;
	}

	public boolean loopOnPages(int ordering) throws InterruptedException {
		boolean success = false;

		List<WebElement> fullPagesList = pagesList;
		if (!fullPagesList.isEmpty()) {
			int lastPageCount = Integer.parseInt(fullPagesList.get(fullPagesList.size() - 1).getText());

			for (int i = 2; i <= lastPageCount; i++) {
				String dynamicXpath = String.format(requiredPage, i);
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXpath)));

				driver.findElement(By.xpath(dynamicXpath)).click();

				success = ordering == 1 ? isIncreasing() : isDecreasing();
				if (!success)
					return false;
			}
		} else {
			if (checkCountsOfResults() > 0) {
				success = ordering == 1 ? isIncreasing() : isDecreasing();
			} else {
				System.out.println("No Doctors available, please check the Test Data");
			}
		}
		return success;
	}
}
