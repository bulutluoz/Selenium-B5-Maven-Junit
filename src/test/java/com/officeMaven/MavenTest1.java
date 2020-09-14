package com.officeMaven;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class MavenTest1 {

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver =new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //1.” https://www.thetrainline.com” Adresine gidin
        //2. Return Radio Button’unu secin
        //3. Return Radio Button’unu secildigini kontrol edin
        //4. ReturnDate input kutusunun erisilebilir oldugunu control edin
        //5. The One Way radio butonunun secili olmadigini control edin

        driver.get("https://www.thetrainline.com");
        //2. Return Radio Button’unu secin
        WebElement returnButton = driver.findElement(By.xpath("//input[@id='return']"));
        returnButton.click();

        //3. Return Radio Button’unu secildigini kontrol edin

        if (returnButton.isSelected()){
            System.out.println("Return butonu secildi Test PASS");
        }else System.out.println("Return butonu secilmedi Test FAILED");


        //4. ReturnDate input kutusunun erisilebilir oldugunu control edin
        WebElement returndateInputKutusu = driver.findElement(By.xpath("//input[@id='page.journeySearchForm.inbound.title']"));
        if (returndateInputKutusu.isEnabled()){
            System.out.println("Donus tarihi kutusu erisilebilir.Test PASS");
        } else System.out.println("Donus tarihi kutusu erisilebilir degil .Test FAILED");

        //5. The One Way radio butonunun secili olmadigini control edin

        WebElement tekYonradioButonu = driver.findElement(By.xpath("//input[@id='single']"));

        if (!tekYonradioButonu.isSelected()){
            System.out.println("Tek yon butonu secili degil. Test PASS");
        } else System.out.println("Tek yon butonu secili. Test FAILED");


        driver.close();
    }
}
