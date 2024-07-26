package TestRunner;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.testng.annotations.Listeners;

import GenericUtilities.ExtentManager;


@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/Features",
    glue = {"Stepdefinitions", "Hooks"}, 
    plugin = {"pretty", "html:target/cucumber-reports.html",
    		"html:target/cucumber.html"} ,
    tags= "@CheckBrokenLinks" 
)
public class TestRunner {
	
	
	
	
	
	

    @AfterClass
    public static void tearDown() {
       
    }
}
