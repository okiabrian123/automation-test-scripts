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

public class selenium {
    WebDriver driver;
    public void setupDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    public WebDriverWait accessLoginPage(){
        driver.get("https://www.saucedemo.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait;
    }

    public void fillLoginForm(String usernameXPath,String Username,String passwordXPath,String Password,String buttonLoginXpath){
        driver.findElement(By.xpath(usernameXPath)).sendKeys(Username);
        driver.findElement(By.xpath(passwordXPath)).sendKeys(Password);
        driver.findElement(By.xpath(buttonLoginXpath)).click();
    }

    public void checking(WebDriverWait wait,String waitUntilXPath,String actualXPath,String Expected){
        try{
            wait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath(waitUntilXPath))));

            String ActualResult = driver.findElement(By.xpath(actualXPath)).getText();
            String ExpectedResult = Expected;
            Assert.assertEquals(ActualResult, ExpectedResult, "Fail");
            System.out.println("Pass");
        } catch (Exception e) {
            System.out.println("Fail");
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        } finally {
            // Ensure that the WebDriver instance is closed regardless of whether an exception occurred
            if (driver != null) {
                driver.quit(); // Closes all browser windows and ends the WebDriver session
            }
        }
    }

    @Test
    public void LG001(){
            setupDriver();
            WebDriverWait wait =accessLoginPage();
            checking(wait,"//div[@class='login_logo']","//div[@class='login_logo']","Swag Labs");
    }

    @Test
    public void LG002(){
            setupDriver();
            WebDriverWait wait =accessLoginPage();
            wait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='login_logo']"))));
            String username = "standard_user";
            String password = "secret_sauce";
            fillLoginForm("//input[@id='user-name']",username,"//input[@id='password']",password,"//input[@id='login-button']");

            //login check
            checking(wait,"//span[@class='title']","//span[@class='title']","Products");
    }

    @Test
    public void LG003(){
        setupDriver();
        WebDriverWait wait =accessLoginPage();
        wait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='login_logo']"))));
        String username = "standard_user";
        String password = "secret_123";
        fillLoginForm("//input[@id='user-name']",username,"//input[@id='password']",password,"//input[@id='login-button']");

        //login check
        checking(wait,"//div[@class='error-message-container error']","//div[@class='error-message-container error']//h3","Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void LG004(){
        setupDriver();
        WebDriverWait wait =accessLoginPage();
        wait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='login_logo']"))));
        String username = "standard_user";
        String password = "";
        fillLoginForm("//input[@id='user-name']",username,"//input[@id='password']",password,"//input[@id='login-button']");

        //login check
        checking(wait,"//div[@class='error-message-container error']","//div[@class='error-message-container error']//h3","Epic sadface: Password is required");
    }

    @Test
    public void LG005(){
        setupDriver();
        WebDriverWait wait =accessLoginPage();
        wait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='login_logo']"))));
        String username = "' OR '1'='1";
        String password = "' OR '1'='1";
        fillLoginForm("//input[@id='user-name']",username,"//input[@id='password']",password,"//input[@id='login-button']");

        //login check
        checking(wait,"//div[@class='error-message-container error']","//div[@class='error-message-container error']//h3","Epic sadface: Username and password do not match any user in this service");
    }

}
