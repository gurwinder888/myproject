package GenericUtilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

@SuppressWarnings("deprecation")
public class ExtentManager {
	
    private static ExtentReports extent;
    private static ExtentTest test;
    
    public static ExtentReports getReportInstance() {
        if (extent == null) {
            String reportName = "TestReport_" + System.currentTimeMillis() + ".html";
            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/Extent-Reports/" + reportName);
            htmlReporter.config().setDocumentTitle("Automation Test Report");
            htmlReporter.config().setReportName("Evey Test Report");
            htmlReporter.config().setTheme(Theme.STANDARD);
            
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
            extent.setSystemInfo("OS", "Macs");
            extent.setSystemInfo("Environment", "Dev");
            extent.setSystemInfo("gurwinder_100", "Gurwinder");
        }
        return extent;
    }
    
    public static ExtentTest createTest(String testName) {
        test = extent.createTest(testName);
        return test;
    }
}
