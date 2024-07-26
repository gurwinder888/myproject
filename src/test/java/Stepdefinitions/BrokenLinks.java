package Stepdefinitions;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import GenericUtilities.BaseClass;
import GenericUtilities.ScenarioContext;
import Pages.BrokenLinkspage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.aventstack.extentreports.Status;
import io.cucumber.java.Scenario;

public class BrokenLinks {

	WebDriver driver = BaseClass.getDriver();
	BrokenLinkspage lp = new BrokenLinkspage(driver);
	Hooks hooks;

	@Given("Open browser and navigate to website {string}")
	public void open_broswer(String url) {

		try {

			driver.get(url);
			Hooks.test.log(Status.PASS, "User navigates to Website");

		} catch (Exception e) {
			Hooks.test.log(Status.FAIL, "Failed navigates to Website" + e.getMessage());
			ScenarioContext.setException(e);
			throw e;
		}

	}

	@When("Broken link")
	public void Check_Broken_Link() {
		try {

			lp.checkAllLinks();
			Hooks.test.log(Status.PASS, "Broken Link Checked");

		} catch (Exception e) {
			Hooks.test.log(Status.FAIL, "Failed to Check broken link" + e.getMessage());
			ScenarioContext.setException(e);
			throw e;
		}
	}
@Then("Test Status")
public void test_status() {
	System.out.println("Passed");
}
}
