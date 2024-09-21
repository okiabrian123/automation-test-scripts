import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;

public class LoginTesting {
    FormLogin formLogin;
    CheckingParamater checkingParamaterValidData;
    CheckingParamater checkingParamaterNotValidData;

    @BeforeMethod
    public void Setup(){
         formLogin = new FormLogin.Builder()
                .setUsername("")
                .setUsernameXPath("//input[@id='user-name']")
                .setPassword("")
                .setPasswordXPath("//input[@id='password']")
                .setButtonXPath("//input[@id='login-button']")
                .build();

         checkingParamaterValidData = new CheckingParamater.Builder()
                .setWaitUntilXPath("//span[@class='title']")
                .setActualXPath("//span[@class='title']")
                .setExpected("Products")
                .build();

         checkingParamaterNotValidData = new CheckingParamater.Builder()
                .setWaitUntilXPath("//div[@class='error-message-container error']")
                .setActualXPath("//div[@class='error-message-container error']//h3")
                .setExpected("")
                .build();
    }

    @Test
    public void LG001(){
        WebDriver driver =tools.setupDriver();
        WebDriverWait wait =tools.accessLoginPage(15,driver);
        tools.checking(driver,wait,"//div[@class='login_logo']","//div[@class='login_logo']","Swag Labs");
    }

    @Test
    public void LG002(){
        formLogin.setUsername("standard_user");
        formLogin.setPassword("secret_sauce");

        tools.loginChecking(15,formLogin,checkingParamaterValidData);
    }

    @Test
    public void LG003(){
        formLogin.setUsername("standard_user");
        formLogin.setPassword("secret_123");

        checkingParamaterNotValidData.setExpected("Epic sadface: Username and password do not match any user in this service");

        tools.loginChecking(15,formLogin,checkingParamaterNotValidData);
    }

    @Test
    public void LG004(){
        formLogin.setUsername("standard_user");
        formLogin.setPassword("");

        checkingParamaterNotValidData.setExpected("Epic sadface: Password is required");

        tools.loginChecking(15,formLogin,checkingParamaterNotValidData);
    }

    @Test
    public void LG005(){
        formLogin.setUsername("' OR '1'='1");
        formLogin.setPassword("' OR '1'='1");

        checkingParamaterNotValidData.setExpected("Epic sadface: Username and password do not match any user in this service");

        tools.loginChecking(15,formLogin,checkingParamaterNotValidData);
    }

    @Test
    public void LG006(){
        formLogin.setUsername("standard_user");
        formLogin.setPassword("secret_123");

        checkingParamaterNotValidData.setExpected("Epic sadface: Username and password do not match any user in this service");


        for (int i = 0; i < 3; i++) {
            System.out.println("Attempt : "+(i+1));
            tools.loginChecking(15,formLogin,checkingParamaterNotValidData);
        }

        formLogin.setUsername("standard_user");
        formLogin.setPassword("secret_sauce");

        checkingParamaterNotValidData.setExpected("suspend");

        tools.loginChecking(15,formLogin, checkingParamaterNotValidData);
    }

}
