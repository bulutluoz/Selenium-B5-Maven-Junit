package com.officeMaven;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

        /*
            1. "https://facebook.com"  Adresine gidin
            2. “create new account”  butonuna basin
            3. “firstName” giris kutusuna bir isim yazin
            4. “surname” giris kutusuna bir soyisim yazin
            5. “email” giris kutusuna bir email yazin
            6. “email” onay kutusuna emaili tekrar yazin
            7. Bir sifre girin
            8. Tarih icin gun secin
            9. Tarih icin ay secin
            10. Tarih icin yil secin
            11. Cinsiyeti secin
            12. Isaretlediginiz cinsiyetin secili, diger cinsiyet kutusunun secili olmadigini test edin.
            13. Sayfayi kapatin

         */

public class JUnitTest1 {
    WebDriver driver; // bu satirda driver i olusturdum

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(); // bu satirda driver a deger atadik
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void test01() throws InterruptedException {
        //1. "https://facebook.com"  Adresine gidin

        driver.get("https://facebook.com");

        // 2. “create new account”  butonuna basin

        WebElement signUpButonu = driver.findElement(By.cssSelector("#u_0_2"));
        signUpButonu.click();

        // 3. “firstName” giris kutusuna bir isim yazin

        WebElement isimKutusu = driver.findElement(By.xpath("//input[@name='firstname']"));
        Faker faker = new Faker();

        isimKutusu.sendKeys(faker.name().firstName());

        // 4. “surname” giris kutusuna bir soyisim yazin
        WebElement soyisimKutusu= driver.findElement(By.xpath("//input[@name='lastname']"));
        soyisimKutusu.sendKeys(faker.name().lastName());

        // 5. “email” giris kutusuna bir email yazin

        WebElement emailKutusu = driver.findElement(By.xpath("//input[@name='reg_email__']"));

        String fakeEmail= faker.internet().emailAddress();
        emailKutusu.sendKeys(fakeEmail);


        // 6. “email” onay kutusuna emaili tekrar yazin
        WebElement emailOnayKutusu = driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']"));
        emailOnayKutusu.sendKeys(fakeEmail);


        // 7. Bir sifre girin

        WebElement sifreKutusu = driver.findElement(By.xpath("//input[@name='reg_passwd__']"));
        sifreKutusu.sendKeys(faker.internet().password());

        //8. Tarih icin gun secin
        WebElement tarihGun= driver.findElement(By.xpath("//select[@name='birthday_day']"));
        Select select =new Select(tarihGun);
        select.selectByIndex(5);


        //9. Tarih icin ay secin
        WebElement tarihAy = driver.findElement(By.xpath("//select[@name='birthday_month']"));
        Select select1 = new Select(tarihAy);
        select1.selectByValue("8");

        //10. Tarih icin yil secin
        WebElement tarihYil = driver.findElement(By.xpath("//select[@name='birthday_year']"));
        Select select2 = new Select(tarihYil);
        select2.selectByVisibleText("1999");
        Thread.sleep(3000);
        WebElement cinsiyetSec = driver.findElement(By.xpath("//input[@id='u_1_5']"));
        cinsiyetSec.click();

        // git init
        // 12. Isaretlediginiz cinsiyetin secili, diger cinsiyet kutusunun secili olmadigini test edin.

        WebElement digerCinsiyet = driver.findElement(By.xpath("//input[@id='u_1_4']"));

        Assert.assertTrue(cinsiyetSec.isSelected());
        Assert.assertFalse(digerCinsiyet.isSelected());

    }
    //13. Sayfayi kapatin
    @After
    public void tearDown(){
        //driver.close();
    }


}
