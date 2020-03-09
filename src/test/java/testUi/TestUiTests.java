package testUi;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import testUI.Configuration;
import testUI.collections.UICollection;
import testUI.elements.UIElement;

import java.util.List;

import static testUI.UIOpen.open;
import static testUI.collections.TestUI.EE;
import static testUI.elements.TestUI.E;

public class TestUiTests {

    @Test
    public void first(){
        Configuration.automationType = Configuration.ANDROID_PLATFORM;
        open("https://www.aliexpress.com/");

        UIElement element = E(By.xpath("//input[@class='_2Emsu']"));
        element.click();
        element.sendKeys("tattoo");
        E(By.xpath("//*[@class='_3zdRA']")).click();

        E(By.xpath("//*[contains(@class, 'ic-filter-md')]")).click();

        UIElement minPrice = E(By.xpath("//input[@*='refineState.minPrice']"));
        minPrice.click();
        minPrice.sendKeys("10");

        UIElement maxPrice = E(By.xpath("//input[@*='refineState.maxPrice']"));
        maxPrice.click();
        maxPrice.sendKeys("20");

        UICollection elements = EE(By.xpath("//*[@role='button']"));

        UIElement doneBtn = elements.get(0);
        doneBtn.click();


        //driver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    @Test
    public void second(){
        Configuration.automationType = Configuration.ANDROID_PLATFORM;
        Configuration.androidAppPath = "src\\main\\resources\\LoginApp.apk";
        open();
        E(By.id("com.loginmodule.learning:id/textInputEditTextEmail")).sendKeys("fcfcf@sv.gh");
        E(By.id("com.loginmodule.learning:id/textInputEditTextPassword")).sendKeys("fcfcf");
        E(By.id("com.loginmodule.learning:id/appCompatButtonLogin")).click();
    }

    @Test
    public void three(){
        Configuration.automationType = Configuration.DESKTOP_PLATFORM;
        open("http://www.1a.lv");
    }
}
