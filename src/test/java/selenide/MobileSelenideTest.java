package selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

@Slf4j
public class MobileSelenideTest {

    @Before
    public void setUp() {
        System.setProperty("selenide.browser", "Chrome");
        Configuration.browser = WebDriverRunner.CHROME;
    }

    @Test
    public void first() {
        Configuration.browserSize = "360x740";
        open("https://www.1a.lv");
        sleep(50000);
    }

    @Test
    public void second() {
        System.setProperty("chromeoptions.mobileEmulation",
                "deviceName=Nexus 5");
        open("https://www.1a.lv");
        sleep(50000);
    }

    @Test
    public void theart() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "6.0");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "testPhone");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        capabilities.setCapability("chromedriverExecutable", "C:\\Users\\Mable\\Downloads\\chromedriver.exe");


        URL url = null;
        try {
            url = new URL("http://127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(url, capabilities);

        driver.get("http://www.1a.lv");

        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    @Test
    public void fore() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "6.0");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "testPhone");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        capabilities.setCapability("chromedriverExecutable", "C:\\Users\\Mable\\Downloads\\chromedriver.exe");


        URL url = null;
        try {
            url = new URL("http://127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(url, capabilities);

        driver.get("https://www.aliexpress.com/");

        WebElement element = driver.findElement(By.xpath("//input[@class='_2Emsu']"));
        element.click();
        element.sendKeys("tattoo");
        driver.findElement(By.xpath("//*[@class='_3zdRA']")).click();

        driver.findElement(By.xpath("//*[contains(@class, 'ic-filter-md')]")).click();

        WebElement minPrice = driver.findElement(By.xpath("//input[@*='refineState.minPrice']"));
        minPrice.click();
        minPrice.sendKeys("10");

        WebElement maxPrice = driver.findElement(By.xpath("//input[@*='refineState.maxPrice']"));
        maxPrice.click();
        maxPrice.sendKeys("20");

        List<AndroidElement> elements = driver.findElements(By.xpath("//*[@role='button')]"));
        WebElement doneBtn = elements.get(0);
        doneBtn.click();


        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }
}
