import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class FirstTest {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("http://the-internet.herokuapp.com/login");

        driver.findElement(By.name("username")).sendKeys("tomsmith");

        driver.findElement(By.cssSelector(".large-6 #password")).sendKeys("SuperSecretPassword!");

        driver.findElement(By.className("radius")).click();

        Assert.assertTrue(driver.findElement(By.id("flash")).isDisplayed());

        driver.close();
    }
}
