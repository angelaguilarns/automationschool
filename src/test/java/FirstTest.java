import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
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
        /*driver.findElement(By.xpath("//*[contains(text(),'Context Menu')]")).click();//This element is imposible to click searching by css
        Assert.assertTrue(driver.findElement(By.cssSelector(".example")).isDisplayed());

         Actions action = new Actions(driver);
        WebElement box = driver.findElement(By.cssSelector("#hot-spot"));
        action.contextClick(box).perform();

        Alert alert = driver.switchTo().alert();
        alert.accept();

        driver.navigate().back();*/

        //Digest autentication
        driver.get("http://admin:admin@the-internet.herokuapp.com/digest_auth");
        String message = driver.findElement(By.cssSelector("p")).getText();
        System.out.println(message);
        driver.navigate().back();

        //Drag and drop --Note: Does not work for some reason
        /*driver.findElement(By.xpath("//*[contains(text(),'Drag and Drop')]")).click();
        WebElement from = driver.findElement(By.xpath("//*[@id='column-a']"));
        WebElement to = driver.findElement(By.xpath("//*[@id='column-b']"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(from,to).build().perform();

        driver.navigate().back();*/

        //Multiple windows
        driver.findElement(By.xpath("//*[contains(text(),'Multiple Windows')]")).click();
        String parentWindow = driver.getWindowHandle();
        driver.findElement(By.cssSelector("#content a")).click();

        for (String winHandle : driver.getWindowHandles()){
            if (!winHandle.equalsIgnoreCase(parentWindow)){
                System.out.println("New window open");
                driver.switchTo().window(winHandle);
                Assert.assertTrue(driver.findElement(By.cssSelector(".example")).isDisplayed());
                driver.close();
            }
        }

        driver.switchTo().window(parentWindow);
        System.out.println("Switched back to parent window");

        driver.navigate().back();
        /*------------------End my own test------------------*/

        driver.close();
    }
}
