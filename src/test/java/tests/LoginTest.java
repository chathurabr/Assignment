package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest {
    WebDriver driver;

    LoginPage loginPage;
    HomePage homePage;


    @BeforeTest
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/");
        loginPage = new LoginPage(driver);
    }

    @Test(priority = 1)
    public void validUsernameAndInvalidPassword(){
        loginPage.invalidLogin(loginPage.getLoginDetails("username"),"1234");
        Assert.assertEquals(loginPage.getErrorMessage(),"Invalid credentials");
    }

    @Test(priority = 2)
    public void validUsernameAndEmptyPassword(){
        loginPage.invalidLogin(loginPage.getLoginDetails("username"),"");
        Assert.assertEquals(loginPage.getErrorMessage(),"Password cannot be empty");
    }

    @Test(priority = 3)
    public void invalidUsernameAndValidPassword(){
        loginPage.invalidLogin("test",loginPage.getLoginDetails("password"));
        Assert.assertEquals(loginPage.getErrorMessage(),"Invalid credentials");
    }

    @Test(priority = 4)
    public void invalidUsernameAndInvalidPassword(){
        loginPage.invalidLogin("test","test");
        Assert.assertEquals(loginPage.getErrorMessage(),"Invalid credentials");
    }

    @Test(priority = 5)
    public void invalidUsernameAndEmptyPassword(){
        loginPage.invalidLogin("test","");
        Assert.assertEquals(loginPage.getErrorMessage(),"Password cannot be empty");
    }


    @Test(priority = 6)
    public void EmptyUsernameEmptyPassword(){
        loginPage.invalidLogin("","");
        Assert.assertEquals(loginPage.getErrorMessage(),"Username cannot be empty");
    }

    @Test(priority = 7)
    public void loginToTheSystem(){
        homePage = loginPage.validLogin(loginPage.getLoginDetails("username"),loginPage.getLoginDetails("password"));
        Assert.assertEquals(homePage.getWelcomeLbl(),homePage.getLoginDetails("welcomeMsg"));
    }

    @AfterTest
    public void close(){
        driver.close();
    }
}
