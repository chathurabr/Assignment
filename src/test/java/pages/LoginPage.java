package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    WebDriver driver;

    By txtUsername = By.id("txtUsername");
    By txtPassword = By.id("txtPassword");
    By btnLogin = By.id("btnLogin");
    By spanMessage = By.id("spanMessage");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    private void typeUsername(String username){
        driver.findElement(txtUsername).clear();
        driver.findElement(txtUsername).sendKeys(username);
    }

    private void typePassword(String password){
        driver.findElement(txtPassword).clear();
        driver.findElement(txtPassword).sendKeys(password);
    }

    private void clickSubmit(){
        driver.findElement(btnLogin).click();
    }

    public HomePage validLogin(String username, String password){
        this.typeUsername(username);
        this.typePassword(password);
        this.clickSubmit();
        return new HomePage(driver);
    }

    public void invalidLogin(String username, String password){
        this.typeUsername(username);
        this.typePassword(password);
        this.clickSubmit();
    }

    public String getErrorMessage(){
        return driver.findElement(spanMessage).getText();
    }
}
