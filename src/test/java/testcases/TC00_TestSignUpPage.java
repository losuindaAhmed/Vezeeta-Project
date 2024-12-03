package testcases;

import org.testng.annotations.Test;

import pageobjects.SingUpPage;

public class TC00_TestSignUpPage extends BaseTest {

	SingUpPage signuppage;

	@Test
	public void testSignUp() {
		signuppage = new SingUpPage(driver);
		signuppage.clickOnSignUp();
		signuppage.adddata("los", "01221604888", "losuinda@gamil.com");
		signuppage.selectGender('F');
		signuppage.addBirthDate("1980", "Aprile", "10");
		signuppage.setPassword("Mahoud123");
		signuppage.clickonJoinNow();

	}
}
