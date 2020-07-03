import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class FirstTest {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("http://the-internet.herokuapp.com/login");

        driver.findElement(By.name("username")).sendKeys("tomsmith");

        driver.findElement(By.cssSelector(".large-6 #password")).sendKeys("SuperSecretPassword!");

        driver.findElement(By.className("radius")).click();

        Assert.assertTrue(driver.findElement(By.id("flash")).isDisplayed());

        /*------------------Start my own test------------------*/
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com");

        //Checkbox
        driver.findElement(By.xpath("//*[contains(text(),'Checkboxes')]")).click();//This element is imposible to click searching by css
        Assert.assertTrue(driver.findElement(By.cssSelector(".example")).isDisplayed());

        List<WebElement> checkboxes = driver.findElements(By.cssSelector("#checkboxes > input"));
        for (WebElement checkbox : checkboxes) {
            if (!(checkbox.isSelected())) {
                checkbox.click();
            } else {
                System.out.println("Checkbox already selected");
            }
        }
        driver.navigate().back();

        //Context Menu
        driver.findElement(By.xpath("//*[contains(text(),'Context Menu')]")).click();//This element is imposible to click searching by css
        Assert.assertTrue(driver.findElement(By.cssSelector(".example")).isDisplayed());

        Actions action = new Actions(driver);
        WebElement box = driver.findElement(By.cssSelector("#hot-spot"));
        action.contextClick(box).perform();

        Alert alert = driver.switchTo().alert();
        alert.accept();

        driver.navigate().back();
        /*------------------End my own test------------------*/

        driver.close();
    }
}
