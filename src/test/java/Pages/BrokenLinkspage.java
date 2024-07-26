package Pages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BrokenLinkspage {

    private WebDriver driver;

    public BrokenLinkspage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void checkAllLinks() {
        List<WebElement> links = driver.findElements(By.tagName("a"));
        links.addAll(driver.findElements(By.tagName("img")));

        System.out.println("Total links and images: " + links.size());

        for (WebElement element : links) {
            String url = element.getAttribute("href");
            if (url == null || url.isEmpty()) {
                continue; // Skip elements without a valid href attribute
            }
            String elementName = getElementName(element);
            checkLink(url, elementName);
        }
    }

    private String getElementName(WebElement element) {
        // Get text or name attribute of the element
        String elementName = element.getText();
        if (elementName == null || elementName.isEmpty()) {
            elementName = element.getAttribute("name");
        }
        if (elementName == null || elementName.isEmpty()) {
            elementName = element.getAttribute("id");
        }
        if (elementName == null || elementName.isEmpty()) {
            elementName = "Unnamed element";
        }
        return elementName;
    }

    public void checkLink(String url, String elementName) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("HEAD");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode >= 400) {
                System.out.println(elementName + " with URL " + url + " is a broken link with response code: " + responseCode);
            }
            connection.disconnect();
        } catch (IOException e) {
            System.out.println(elementName + " with URL " + url + " is a broken link with exception: " + e.getMessage());
        }
    }
}
