package com.officeMaven;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class MavenTest2 {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //1. “https://www.saucedemo.com” Adresine gidin
        //2. Username kutusuna “standard_user” yazdirin
        //3. Password kutusuna “secret_sauce” yazdirin
        //4. Login tusuna basin
        //5. Ilk urunun ismini kaydedin ve bu urunun sayfasina gidin
        //6. Add to Cart butonuna basin
        //7. Alisveris sepetine tiklayin
        //8. Sectiginiz urunun basarili olarak sepete eklendigini control edin
        //9. Sayfayi kapatin


        //1. “https://www.saucedemo.com” Adresine gidin
        driver.get("https://www.saucedemo.com");
        //2. Username kutusuna “standard_user” yazdirin
        WebElement usernameKutusu = driver.findElement(By.cssSelector("#user-name"));
        usernameKutusu.sendKeys("standard_user");


        //3. Password kutusuna “secret_sauce” yazdirin
        WebElement passwordBox = driver.findElement(By.id("password"));
        passwordBox.sendKeys("secret_sauce");


        //4. Login tusuna basin
        WebElement loginButonu = driver.findElement(By.cssSelector("#login-button"));
        loginButonu.click();


        //5. Ilk urunun ismini kaydedin ve bu urunun sayfasina gidin
        //<div class="inventory_item_name">Sauce Labs Backpack</div>
        // //*[.='Sauce Labs Backpack']
        WebElement ilkUrun = driver.findElement(By.className("inventory_item_name"));
        String ilkUrunAdi = ilkUrun.getText();
        ilkUrun.click();

        //6. Add to Cart butonuna basin

        WebElement addToCart = driver.findElement(By.xpath("//button[@class='btn_primary btn_inventory']"));
        addToCart.click();
        //7. Alisveris sepetine tiklayin
        //<span class="fa-layers-counter shopping_cart_badge">1</span>
        WebElement sepet = driver.findElement(By.xpath("//span[@class='fa-layers-counter shopping_cart_badge']"));
        sepet.click();

        //8. Sectiginiz urunun basarili olarak sepete eklendigini control edin
        WebElement sepettekkiUrunAdi=driver.findElement(By.partialLinkText("Sauce Labs Backpack"));
        if (sepettekkiUrunAdi.getText().equals(ilkUrunAdi)){
            System.out.println("Sectiginiz urun ile sepetteki urun ayni. Test PASS");
        } else System.out.println("Sectiginiz urun ile sepetteki urun farkli. Test FAILED");

        //9. Sayfayi kapatin
        driver.close();
    }
}
