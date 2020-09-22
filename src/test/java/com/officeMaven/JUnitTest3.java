package com.officeMaven;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class JUnitTest3 {
    // 1) “http://webdriveruniversity.com/Popup-Alerts/index.html”  adresine gidin
    // 2) “CLICK ME of JavaScript Alert” butonuna basin
    // 3) Pop up yazisini alin
    // 4) “I am an alert box!“ yazisinin ciktigini test edin
    // 5) Tamam diyerek Pop Up’I kapatin

    // Onceki class’a bir test method ekleyin
    //   1) “http://webdriveruniversity.com/Popup-Alerts/index.html” sayfasina gidin
    //   2) “CLICK ME of JavaScript Confirm Box” butonuna tiklayin
    //   3) Pop up’ta cikan yaziyi alin
    //   4) Pop up yazisinin “Press a button“ olmadigini control edin
    //   5) Cancel diyerek Pop up’i kapatin
    //   6) “You pressed Cancel!” yazisinin ciktigini dogrulayin

    WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }



    @Test
    public void test1(){
        // 1) “http://webdriveruniversity.com/Popup-Alerts/index.html”  adresine gidin
        driver.get("http://webdriveruniversity.com/Popup-Alerts/index.html");

        // 2) “CLICK ME of JavaScript Alert” butonuna basin
        driver.findElement(By.id("button1")).click();

        // 3) Pop up yazisini alin

        String aktuelPopUpYazisi=driver.switchTo().alert().getText();

        // 4) “I am an alert box!“ yazisinin ciktigini test edin
        String beklenenPopUpYazisi="I am an alert box!";

        Assert.assertEquals(aktuelPopUpYazisi,beklenenPopUpYazisi);

        // 5) Tamam diyerek Pop Up’i kapatin
        driver.switchTo().alert().accept();
    }


    @Test
    public void test2(){
        // Onceki class’a bir test method ekleyin
        //   1) “http://webdriveruniversity.com/Popup-Alerts/index.html” sayfasina gidin
        driver.get("http://webdriveruniversity.com/Popup-Alerts/index.html");

        //   2) “CLICK ME of JavaScript Confirm Box” butonuna tiklayin
        driver.findElement(By.id("button4")).click();

        //   3) Pop up’ta cikan yaziyi alin
        String aktuelPopUpYazisi=driver.switchTo().alert().getText();

        //   4) Pop up yazisinin “Press a button“ olmadigini control edin
        String beklenenPopUpYazisi="Press a button";
        Assert.assertFalse(aktuelPopUpYazisi.equals(beklenenPopUpYazisi));


        //   5) Cancel diyerek Pop up’i kapatin

        driver.switchTo().alert().dismiss();

        //   6) “You pressed Cancel!” yazisinin ciktigini dogrulayin
        WebElement cancelledYazisi= driver.findElement(By.id("confirm-alert-text"));
        String aktuelCanceledYazisi= cancelledYazisi.getText();
        String beklenenCancelledYazisi="You pressed Cancel!";
        Assert.assertEquals(aktuelCanceledYazisi,beklenenCancelledYazisi);

    }


    @After
    public void tearDown(){
        //driver.close();
    }

}
