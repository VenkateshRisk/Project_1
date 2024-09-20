import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Test1 {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Set up ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();

        // Create an instance of ChromeDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void testPageTitle() {
        // Navigate to Google
        driver.get("https://transindia.int.uat-riskcovry.com/partner/signin/");

        /* Verify the title
        String title = driver.getTitle();
        Assert.assertEquals(title, "TransIndia");*/
    }
    @Test(priority = 2)
    public  void LoginWithValidCredentials(){
        testPageTitle();
        WebElement user_name= driver.findElement(By.xpath("/html/body/div/div/main/div[1]/form/div/div[2]/div/input"));
        user_name.sendKeys("testinternal@something.com");

        WebElement password= driver.findElement(By.xpath("/html/body/div/div/main/div[1]/form/div/div[3]/div/input"));
        password.sendKeys("yp$@m%NU2p");

        WebElement Login=driver.findElement(By.xpath("/html/body/div/div/main/div[1]/form/div/div[5]/button[2]/span"));
        Login.click();
        String ExpectedHomePageUrl="https://transindia.int.uat-riskcovry.com/partner/home/";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver -> driver.getCurrentUrl().equals(ExpectedHomePageUrl));
        String CurrentURL=driver.getCurrentUrl();
        Assert.assertEquals(CurrentURL, ExpectedHomePageUrl, "The current URL does not match the expected URL.");


    }
    @Test(priority = 3)
    public void LogoutWithValidCredentials(){
        testPageTitle();
        LoginWithValidCredentials();
        WebElement Logout=driver.findElement(By.xpath("/html/body/div/div/div[1]/header/div[2]/div[3]/img"));
        Logout.click();
        WebElement LogOutButton= driver.findElement(By.xpath("/html/body/div/div/div[1]/header/div[2]/div[3]/div/div"));
        LogOutButton.click();
        String LogoutpageUrl="https://transindia.int.uat-riskcovry.com/partner/signin/";
        WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(10));
        wait1.until(driver -> driver.getCurrentUrl().equals(LogoutpageUrl));
        String LogoutcurrentUrl= driver.getCurrentUrl();
        Assert.assertEquals(LogoutcurrentUrl,LogoutpageUrl,"The current URL does not match the expected URL.");


    }
    public void LoginWithInvalidCredentials(){
        System.out.println("We will implemented lateral");
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
       if (driver != null) {
            driver.quit();
        }
    }
}

