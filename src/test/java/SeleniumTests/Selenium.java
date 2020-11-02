package SeleniumTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Selenium {

    private WebDriver driver;

    @BeforeEach
    void setUp(){
        System.setProperty("webdriver.chrome.driver", "C://Users/charl/selenium/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://facebook.com");
    }

    @Test
    void testHeader() {
        WebElement header = driver.findElement(By.tagName("h2"));
        String expected = "Connect with friends and the world around you on Facebook.";
        String actual = header.getText();
        assertEquals(expected, actual);
    }

    @Test
    void testFooter() {
        List<WebElement> list = driver.findElements(By.tagName("li"));
        for(WebElement listItem: list) {
            System.out.println(listItem.getText());
        }
    }

    @Test
    void testBadEmail() {
        driver.findElement(By.id("email")).sendKeys("someone@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("password", Keys.ENTER);
        assertEquals("The email you’ve entered doesn’t match any account. Sign up for an account.", driver.findElement(By.cssSelector("._9ay7")).getText());

    }

    @Test
    void testBack(){
        driver.navigate().to("https://twitter.com");
        driver.navigate().back();
        assertEquals("Facebook - Log In or Sign Up", driver.getTitle());
    }

    @Test
    void testAvailableLanguages() {
        List<WebElement> languages = driver.findElements(By.tagName("a"));
        List<String> availableLanguages = new ArrayList<String>();
        for(WebElement lang: languages){
            availableLanguages.add(lang.getText());
        }

        assertThat(availableLanguages, hasItems(""));
    }

    @Test
    void testForward() {
        driver.navigate().to("https://youtube.com");
        driver.navigate().to("https://reddit.com");
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().forward();
        assertEquals("reddit: the front page of the internet", driver.getTitle());
    }

    @Test
    void testRefresh() {
        driver.navigate().refresh();
        assertEquals("Facebook - Log In or Sign Up", driver.getTitle());
    }

    @AfterEach
    void tearDown() {
        driver.close();
    }

}
