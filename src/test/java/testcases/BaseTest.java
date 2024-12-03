package testcases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	static WebDriver driver;

	Properties p;

	@BeforeClass
	@Parameters({ "os", "browser" })
	public void setup(String os, String br) throws IOException {

		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);

		switch (br.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-search-engine-choice-screen");
			driver = new ChromeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions option2 = new FirefoxOptions();
			option2.addArguments("--disable-search-engine-choice-screen");
			driver = new FirefoxDriver();
			break;

		default:
			System.out.println("No such browser");
			

		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(p.getProperty("appURL"));
	}

	public String captureScreen(String tname) {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takescreenshot = (TakesScreenshot) driver;
		File sourceFile = takescreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);

		return targetFilePath;

	}

	public void tearDown() {
		driver.close();
	}

}
