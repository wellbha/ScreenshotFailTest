import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class failTestCaseScreenshot {
    WebDriver driver;

    public void clickElementBy(By by) {
        driver.findElement(by).click();
    }

    public void findElementBy(By by) {
        driver.findElement(by);
    }

    public void enterText(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }

    public void selectBy(By by) {
        driver.findElement(by).isSelected();
    }

    @BeforeMethod //run before every method
    public void openingBrowser() {
        //initializing the chrome driver and passing the url
        //pre conditions
        System.setProperty("webdriver.chrome.driver", "src\\BrowserDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");
    }
    @Test
    public void takeScreenShotForFailTest() {

        clickElementBy(By.linkText("Regiter"));
    }
    @AfterMethod //run after every method
    public void closingBrowser(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                File ts = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                //File source = ts.getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(ts, new File("./Screenshots/" + result.getName() + ".png"));
                System.out.println("Screenshot taken");
            } catch (Exception e) {
            }
            driver.quit();
        }
    }
}
