package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BasePage {
    WebDriver driver;

    public LoginPage setUpBrowser(){
        String broseName = this.getLoginDetails("browser");
        String url = this.getLoginDetails("url");
        if (broseName.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.get(url);
        }else if(broseName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.get(url);
        }
        return new LoginPage(driver);
    }

    public String getLoginDetails(String propName){
        String filePath = System.getProperty("user.dir");
        Properties properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream(filePath+ "/src/test/java/utils/loginDetails.properties");
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(propName);
    }
}
