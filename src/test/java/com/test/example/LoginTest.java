package com.test.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.swing.plaf.synth.SynthTextAreaUI;

public class LoginTest {
    FormLogin formLogin;
    CheckingParamater checkingParamaterValidData;
    CheckingParamater checkingParamaterNotValidData;
    WebDriver driver;

    @BeforeTest
    public void Setup(){

        // Mendapatkan platform atau browser dari properti sistem (misalnya, dari profil Maven)
        String browser = System.getProperty("browser"); // Default ke Chrome jika tidak ada properti
        if (browser == null){
            System.out.println("Need to setting browser(Safari, Chrome, Firefox)\nExample : mvn test -Psafari or mvn test -Dbrowser=safari");
        }

        driver = tools.SetupDriver(browser);
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
        System.out.println("\nLG001 :");
        WebDriverWait wait =tools.accessLoginPage(15,driver);
        tools.checking(driver,wait,"//div[@class='login_logo']","//div[@class='login_logo']","Swag Labs");
    }

    @Test
    public void LG002(){
        System.out.println("\nLG001 :");
        formLogin.setUsername("standard_user");
        formLogin.setPassword("secret_sauce");

        tools.loginChecking(driver,15,formLogin,checkingParamaterValidData);
    }

    @Test
    public void LG003(){
        System.out.println("\nLG001 :");
        formLogin.setUsername("standard_user");
        formLogin.setPassword("secret_123");

        checkingParamaterNotValidData.setExpected("Epic sadface: Username and password do not match any user in this service");

        tools.loginChecking(driver,15,formLogin,checkingParamaterNotValidData);
    }

    @Test
    public void LG004(){
        System.out.println("\nLG001 :");
        formLogin.setUsername("standard_user");
        formLogin.setPassword("");

        checkingParamaterNotValidData.setExpected("Epic sadface: Password is required");

        tools.loginChecking(driver,15,formLogin,checkingParamaterNotValidData);
    }

    @Test
    public void LG005(){
        System.out.println("\nLG001 :");
        formLogin.setUsername("' OR '1'='1");
        formLogin.setPassword("' OR '1'='1");

        checkingParamaterNotValidData.setExpected("Epic sadface: Username and password do not match any user in this service");

        tools.loginChecking(driver,15,formLogin,checkingParamaterNotValidData);
    }

    @Test
    public void LG006(){
        System.out.println("\nLG001 :");
        formLogin.setUsername("standard_user");
        formLogin.setPassword("secret_123");

        checkingParamaterNotValidData.setExpected("Epic sadface: Username and password do not match any user in this service");


        for (int i = 0; i < 3; i++) {
            System.out.println("Attempt : "+(i+1));
            tools.loginChecking(driver,5,formLogin,checkingParamaterNotValidData);
        }

        formLogin.setUsername("standard_user");
        formLogin.setPassword("secret_sauce");

        checkingParamaterNotValidData.setExpected("suspend");

        tools.loginChecking(driver,5,formLogin, checkingParamaterNotValidData);
    }
    @AfterTest
    public void close(){
        if (driver != null) {
            driver.quit(); // Closes all browser windows and ends the WebDriver session
        }
    }

}
