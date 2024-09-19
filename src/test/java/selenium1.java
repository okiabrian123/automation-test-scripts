import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

@Test
public class selenium1 {
    WebDriver driver;
    public void loginTest(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://katalon-demo-cura.herokuapp.com");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        //click make appointment
        driver.findElement(By.id("btn-make-appointment")).click();

        //input name and password
        wait.until((ExpectedConditions.visibilityOfElementLocated(By.id("txt-username"))));
        driver.findElement(By.id("txt-username")).sendKeys("John Doe");
        driver.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");
        driver.findElement(By.id("btn-login")).click();

        //login check
        try{
            wait.until((ExpectedConditions.visibilityOfElementLocated(By.id("btn-book-appointment"))));
            String ActualResult = driver.findElement(By.id("btn-book-appointment")).getText();
            String ExpectedResult = "Book Appointment";
            Assert.assertEquals(ActualResult, ExpectedResult, "tombol tidak ditemukan");

            System.out.println("Login berhasil");

        } catch (Exception e) {
            System.out.println("Login gagal");
        }

        //close the browser
        driver.close();

    }

    public void loginTest2(){
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        Dimension size = new Dimension(500, 1000);
        driver.manage().window().setSize(size);

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        //input name and password
        wait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']"))));
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--main orangehrm-login-button']")).click();


        wait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[.='My Actions']"))));
        driver.findElement(By.xpath("//i[@class='oxd-icon bi-list oxd-topbar-header-hamburger']")).click();
        driver.findElement(By.xpath("//a[.='Admin']")).click();
        //close the browser


    }


}
