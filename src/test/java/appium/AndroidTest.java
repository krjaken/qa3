package appium;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;

public class AndroidTest {
    WebDriver driver;

    @Before
    public void setUp() throws Exception {

        //To create an object of Desired Capabilities
        DesiredCapabilities capability = new DesiredCapabilities();

        capability.setCapability(CapabilityType.VERSION, " 6.0");
        capability.setCapability("app", "C:\\Users\\Mable\\Downloads\\LoginApp.apk");
        capability.setCapability("deviceName", "testPhone");
        capability.setCapability("platformName", "Android");

        //driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capability);
    }

    @Test
    public void testApp() throws MalformedURLException {

        System.out.println("App launched");

        driver.findElement(By.id("com.loginmodule.learning:id/textInputEditTextEmail")).sendKeys("fcfcf@sv.gh");
        driver.findElement(By.id("com.loginmodule.learning:id/textInputEditTextPassword")).sendKeys("fcfcf");
        driver.findElement(By.id("com.loginmodule.learning:id/appCompatButtonLogin")).click();
    }

    @Test
    public void createAccount() {

    }
}
