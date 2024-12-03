package testcases;

import org.testng.annotations.Test;

import pageobjects.LoginPage;

public class TC001_Test_LoginPage extends BaseTest {

	LoginPage loginpage;

	@Test
	public void testLoginPageWithvalidData() {
		loginpage = new LoginPage(driver);
		loginpage.clickOnLoginHomePage();
		loginpage.setEmail("losuinda11@gamil.com");
		loginpage.setPassword("Mahoud1234");
		loginpage.clickonLogin();
		loginpage.validafterLogin();

	}

}
