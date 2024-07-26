package Stepdefinitions;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import GenericUtilities.BaseClass;
import GenericUtilities.ExtentManager;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

@Listeners(GenericUtilities.ListenersImplementationClass.class)

public class Hooks {

    ExtentReports extent;
    public static ExtentTest test;

    @Before
    public void setUp(Scenario scenario) throws IOException {
        String browserName = "chrome"; // You can read this from a configuration file or property file
        BaseClass.initializeDriver(browserName);
        extent = ExtentManager.getReportInstance();
        test = extent.createTest(scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenshotPath = captureScreenshotAndAttach(scenario.getName());
            try {
                test.fail("Test failed: " + scenario.getName(),
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        extent.flush();
        if (BaseClass.driver != null) {
            BaseClass.driver.quit(); // Assuming driver is initialized and used in your tests
        }
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        
        extent.flush();
    }

    private String captureScreenshotAndAttach(String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String screenshotName = testName + "_" + timestamp + ".png";
        String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + screenshotName;

        try {
            File screenshotFile = ((TakesScreenshot) BaseClass.driver).getScreenshotAs(OutputType.FILE);
            Path targetPath = Paths.get(screenshotPath);
            Files.createDirectories(targetPath.getParent());
            Files.move(screenshotFile.toPath(), targetPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return screenshotPath;
    }

    private String getErrorMessage(Scenario scenario) {
        // Return any additional error message you need for the scenario
        return ""; // Example: scenario.getStatus().toString();
    }
}