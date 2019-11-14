package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Random;

public class Tester {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver",
                "F:\\Downloads\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");
        
        sleep(2);
        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("asd");
        element = driver.findElement(By.name("password"));
        element.sendKeys("asd12345");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();
        
        sleep(2);
        element = driver.findElement(By.id("error"));
        
        sleep(2);
        element = driver.findElement(By.linkText("back to home"));
        element.click();
        
        sleep(2);
        element = driver.findElement(By.linkText("register new user"));
        element.click();
        
        sleep(2);
        
        Random r = new Random();
        element = driver.findElement(By.name("username"));
        element.sendKeys("arto"+r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("asd");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("asd");
        element = driver.findElement(By.name("signup"));
        
        sleep(2);
        element.submit();
        
        sleep(2);
        element = driver.findElement(By.xpath("//*[contains(text(), 'Welcome to Ohtu Application!')]"));
        
        sleep(2);
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        
        sleep(2);
        element = driver.findElement(By.linkText("logout"));
        element.click();
        
        sleep(2);
        element = driver.findElement(By.linkText("login"));
        
        sleep(3);
        
        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
