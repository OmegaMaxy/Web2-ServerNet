package domain.server;

import domain.model.Server;
import domain.model.DomainException;

import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SessieTest {
    private WebDriver driver;
    private final String host_url = "http://localhost:8080";
    @Before
    public void prepare() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        this.driver = new ChromeDriver();
        //this.headless();
    }
    public void headless() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1400,800");
        this.driver = new ChromeDriver(options);
    }

    @Test
    public void ActiveMenuItem() {
        this.driver.get(this.host_url + "/Controller?route=create");
        assertEquals(this.driver.findElement(By.className("active")).getText(), "Add server");
    }

    @Test
    public void SessionCreationTest() throws InterruptedException {
        // visit basket page
        //firstBrowser
        // expected:
        this.driver.get(this.host_url + "/Controller?route=shop-basket");
        //-fluentWait(By.className("card-title"), this.driver);
        Thread.sleep(2000);
        try {
            assertEquals(this.driver.findElement(By.className("card-title")).getText(), "No session");
        } catch (NoSuchElementException e) {
            fail("Session was already made, or page did not load correctly."); // fails because of cross browser session or something??
        }
        // after that visit shop page
        this.driver.get(this.host_url + "/Controller?route=shop");
        //-fluentWait(By.className("card-header"), this.driver);
        Thread.sleep(2000);
        assertEquals(this.driver.findElements(By.className("card-header")).get(0).getText(), "Shop");
        // session should be created
        this.driver.get(this.host_url + "/Controller?route=shop-basket");
        //-fluentWait(By.tagName("main"), this.driver);
        Thread.sleep(2000);
        try {
            assertEquals(this.driver.findElement(By.tagName("main")).getText(), "Your shopping cart is empty at the moment. Add some items  here.");
        } catch (NoSuchElementException e) {
            fail("Session was empty, or page did not load correctly.");
        }
        this.driver.close();
    }
    @Test
    public void SessionCannotBeCrossBrowserTest() throws InterruptedException {
        WebDriver firstBrowser = new ChromeDriver();
        WebDriver secondBrowser = new ChromeDriver();
        //firstBrowser
        // visit basket page | at first no session
        firstBrowser.navigate().to(this.host_url + "/Controller?route=shop-basket");
        //-fluentWait(By.className("card-title"), firstBrowser);
        Thread.sleep(2000);
        try {
            assertEquals(firstBrowser.findElement(By.className("card-title")).getText(), "No session");
        } catch (NoSuchElementException e) {
            fail("Session was already made, or page did not load correctly.");
        }
        // create session on shop page
        firstBrowser.navigate().to(this.host_url + "/Controller?route=shop");
        //-fluentWait(By.className("card-header"), firstBrowser);
        Thread.sleep(2000);
        assertEquals(firstBrowser.findElements(By.className("card-header")).get(0).getText(), "Shop");
        // session should be created
        firstBrowser.navigate().to(this.host_url + "/Controller?route=shop");
        //-fluentWait(By.tagName("main"), firstBrowser);
        Thread.sleep(2000);
        try {
            assertEquals(firstBrowser.findElement(By.tagName("main")).getText(), "Your shopping cart is empty at the moment. Add some items  here.");
        } catch (NoSuchElementException e) {
            fail("Session was empty, or page did not load correctly.");
        }
        //secondBrowser
        // no session at start
        secondBrowser.navigate().to(this.host_url + "/Controller?route=shop-basket");
        Thread.sleep(2000);

        try {
            assertEquals(secondBrowser.findElement(By.className("card-title")).getText(), "No session");
        } catch (NoSuchElementException e) {
            fail("Session was already made, or page did not load correctly.");
        }
        firstBrowser.close();
        secondBrowser.close();
    }
    /* helper */
    public WebElement fluentWait(final By locator, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(locator));

        /*Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });*/

        return driver.findElement(locator);
        //return foo;
    };



    @After
    public void close() {
        this.driver.close();
    }
}