package domain.server;

import domain.model.DomainException;

import org.junit.Before;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class ServerTest {
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
    public void clear() {
        this.driver.navigate().to(host_url + "/Controller?route=admin-clear");
    }

    @Test
    public void CreateServerTest() {
        this.clear();
        this.driver.get(host_url + "/Controller?route=create");
        assertEquals("Create server", this.driver.findElement(By.cssSelector(".card-header")).getText());

        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.name("serverName"))).sendKeys("Helsinki-3120-15");
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.name("serverLocation"))).sendKeys("Helsinki");
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.name("amountOfServices"))).sendKeys("15");
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.name("amountOfRAM"))).sendKeys("3120");
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.tagName("button"))).click();

        assertEquals("View server", this.driver.findElement(By.cssSelector(".card-header")).getText());
        WebElement _tbody = this.driver.findElement(By.cssSelector("table > tbody > tr"));
        assertEquals("Helsinki-3120-15", _tbody.findElement(By.cssSelector("td:nth-child(2)")).getText());
        assertEquals("Helsinki", _tbody.findElement(By.cssSelector("td:nth-child(3)")).getText());
        assertEquals("15 Services", _tbody.findElement(By.cssSelector("td:nth-child(4)")).getText());
        assertEquals("3120MB", _tbody.findElement(By.cssSelector("td:nth-child(5)")).getText());
    }

    @Test
    public void CreateServerWithEmptyNameThrowsException() {
        this.clear();
        this.driver.navigate().to(host_url + "/Controller?route=create");
        assertEquals("Create server", this.driver.findElement(By.cssSelector(".card-header")).getText());

        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.name("serverName"))).sendKeys("");
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.name("serverLocation"))).sendKeys("Helsinki");
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.name("amountOfServices"))).sendKeys("15");
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.name("amountOfRAM"))).sendKeys("3120");
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.tagName("button"))).click();
        assertEquals("Name cannot be empty!", new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".errorMessages p"))).getText());

    }
    @Test
    public void CreateServerWithEmptyLocationThrowsExceptionTest() {
        this.clear();
        this.driver.navigate().to(host_url + "/Controller?route=create");
        assertEquals("Create server", this.driver.findElement(By.cssSelector(".card-header")).getText());

        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.name("serverName"))).sendKeys("Helsinki-3120-15");
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.name("serverLocation"))).sendKeys("");
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.name("amountOfServices"))).sendKeys("15");
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.name("amountOfRAM"))).sendKeys("3120");
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.tagName("button"))).click();

        assertEquals("Location cannot be empty!", new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".errorMessages p"))).getText());

    }
    @Test
    public void CreateServerWithTooMuchServicesThrowsExceptionTest() {
        this.clear();
        this.driver.navigate().to(host_url + "/Controller?route=create");
        assertEquals("Create server", this.driver.findElement(By.cssSelector(".card-header")).getText());

        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.name("serverName"))).sendKeys("Helsinki-3120-1500");
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.name("serverLocation"))).sendKeys("Helsinki");
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.name("amountOfServices"))).sendKeys("1500");
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.name("amountOfRAM"))).sendKeys("3120");
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.tagName("button"))).click();
        // redirects to /view server
        assertEquals("The amount of services must be positive, and lower than 1000!", new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".errorMessages p"))).getText());
    }


    public void help_create_server() {
        this.driver.navigate().to(host_url + "/Controller?route=create");
        //WebElement _form = this.driver.findElement(By.tagName("form")));

        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.name("serverName"))).sendKeys("Helsinki-3120-15");
        //new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.name("serverName"))).sendKeys("Helsinki-3120-15");
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.name("serverLocation"))).sendKeys("Helsinki");
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.name("amountOfServices"))).sendKeys("15");
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.name("amountOfRAM"))).sendKeys("3120");

        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.tagName("button"))).click();
        //new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.tagName("button"))).click();
    }
    @Test
    public void EditServerTest() {
        this.clear();
        this.help_create_server();
        this.driver.navigate().to(host_url + "/Controller?route=edit&id=1");
        assertEquals("Edit server", this.driver.findElement(By.cssSelector(".card-header")).getText());

        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.name("serverName"))).clear();
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.name("serverName"))).sendKeys("New server name");
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.name("serverLocation"))).clear();
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.name("serverLocation"))).sendKeys("New server location");
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.name("amountOfServices"))).clear();
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.name("amountOfServices"))).sendKeys("51");
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.name("amountOfRAM"))).clear();
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.name("amountOfRAM"))).sendKeys("1280");
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type=submit]"))).click();

        assertEquals("View server", this.driver.findElement(By.cssSelector(".card-header")).getText());
        WebElement _tbody = new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.cssSelector("table > tbody > tr:last-child")));

        assertEquals("New server name", _tbody.findElement(By.cssSelector("td:nth-child(2)")).getText());
        assertEquals("New server location", _tbody.findElement(By.cssSelector("td:nth-child(3)")).getText());
        assertEquals("51 services", _tbody.findElement(By.cssSelector("td:nth-child(4)")).getText().toLowerCase());
        assertEquals("1280MB", _tbody.findElement(By.cssSelector("td:nth-child(5)")).getText());
    }
    @Test
    public void EditServerWithNegativeAmountOfServicesThrowsException() throws DomainException {
        this.clear();
        this.help_create_server();
        this.driver.navigate().to(host_url + "/Controller?route=edit&id=1");
        assertEquals("Edit server", this.driver.findElement(By.cssSelector(".card-header")).getText());

        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.name("amountOfServices"))).clear();
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.name("amountOfServices"))).sendKeys("-10");
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type=submit]"))).click();

        assertEquals("The amount of services must be positive, and lower than 1000!", new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".errorMessages p"))).getText());

    }

    @Test
    public void DeleteServerResultsInEmptyList() throws DomainException {
        this.clear();
        this.help_create_server();
        this.driver.navigate().to(host_url + "/Controller?route=overview");
        assertEquals(1, this.driver.findElements(By.cssSelector("tbody > tr")).size());
        this.driver.navigate().to(host_url + "/Controller?route=delete&id=1");
        assertEquals(true, new WebDriverWait(driver,30).until(ExpectedConditions.textToBe(By.cssSelector(".card-title"), "Are you sure you want to delete this server?")));

        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type=submit]"))).click();

        assertEquals(0, this.driver.findElements(By.cssSelector("tbody > tr")).size());
    }

    @Test
    public void SearchServerTest() {
        // problem: (name: query) doesn't get assigned a value, sendKeys on element doesn't work for some reason
        this.clear();
        this.help_create_server();
        this.driver.navigate().to(host_url + "/Controller?route=search");
        assertEquals("Explore", this.driver.findElement(By.cssSelector(".card-header")).getText());

        System.out.println("DP 0:" + this.driver.getPageSource());
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.name("query"))).click();
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.name("query"))).sendKeys("Helsinki");
        System.out.println("DP 1: " + this.driver.getPageSource());
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type=submit]"))).click();

        System.out.println("DP 2: " + this.driver.getPageSource());

        WebElement _tbody = this.driver.findElement(By.cssSelector("table > tbody > tr:first-child"));

        assertEquals("Helsinki-3120-15", _tbody.findElement(By.cssSelector("td:nth-child(2)")).getText());
        assertEquals("Helsinki", _tbody.findElement(By.cssSelector("td:nth-child(3)")).getText());
        assertEquals("15 services", _tbody.findElement(By.cssSelector("td:nth-child(4)")).getText().toLowerCase());
        assertEquals("3120MB", _tbody.findElement(By.cssSelector("td:nth-child(5)")).getText());
    }

    @Test
    public void SearchServerCreatesCookieTest() {
        this.clear();
        this.help_create_server();
        this.driver.navigate().to(host_url + "/Controller?route=search");
        assertEquals("Explore", this.driver.findElement(By.cssSelector(".card-header")).getText());

        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.name("query"))).sendKeys("Helsinki");
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type=submit]"))).click();
        this.driver.navigate().to(host_url + "/Controller?route=search");

        assertEquals(true, new WebDriverWait(driver,30).until(ExpectedConditions.textToBe(By.cssSelector("section p"), "Your latest search: \"Helsinki\"")));
    }


    @After
    public void close() {
        this.driver.close();
    }
}