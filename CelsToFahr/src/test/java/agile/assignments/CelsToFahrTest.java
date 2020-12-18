package agile.assignments;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;



public class CelsToFahrTest {

    private WebDriver driver;
    private String baseUrl = "https://www.rapidtables.com/convert/temperature/celsius-to-fahrenheit.html";
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private boolean headless = true;
    private String browserDriver = "Firefox";
    String driverPath = "C:\\eclipse\\selenium\\drivers\\";

    @Before
    public void setUp() throws Exception {

        if (browserDriver == "Chrome")
        {
            if(headless) {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("headless");
                chromeOptions.addArguments("disable-gpu");
                chromeOptions.addArguments("disable-extensions");
                driver = new ChromeDriver(chromeOptions);
            }
            else {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("disable-gpu");
                chromeOptions.addArguments("disable-extensions");
                driver = new ChromeDriver(chromeOptions);
            }
        }
        else if (browserDriver == "Firefox")
        {
            if(headless) {
                FirefoxOptions fireFoxoptions = new FirefoxOptions();
                fireFoxoptions.addArguments("--headless");
                driver = new FirefoxDriver(fireFoxoptions);
            }
            else
                driver = new FirefoxDriver();
        }
        else if (browserDriver == "Edge")
        {

            System.setProperty("webdriver.edge.driver", driverPath+"msedgedriver.exe");

            if(headless) {
                //EdgeDriverService edgeDriverService = new EdgeDriverService(); //Microsoft.Edge.SeleniumTools.EdgeDriverService.CreateChromiumService();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setCapability("headless", "headless");
                driver = new EdgeDriver(edgeOptions);
            }
            else {
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setPageLoadStrategy("normal");//.PageLoadStrategy = PageLoadStrategy.Normal;
                driver = new EdgeDriver();
            }

            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        }
    }


    /**
     *
     * Test zero Celsius returns 32 Fahrenheit.
     *
     * @throws Exception
     */
    @Test
    public void testZeroCelsius() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.id("x")).clear();
        driver.findElement(By.id("x")).sendKeys("0");
        driver.findElement(By.xpath("//button[@type='button']")).click();
        try {
            assertEquals("32", driver.findElement(By.name("y")).getAttribute("value"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    /**
     *
     * Test negative integer.
     *
     * @throws Exception
     */
    @Test
    public void testNegativeOneCelsius() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.id("x")).clear();
        driver.findElement(By.id("x")).sendKeys("-1");
        driver.findElement(By.xpath("//button[@type='button']")).click();
        try {
            assertEquals("30.2", driver.findElement(By.name("y")).getAttribute("value"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    /**
     *
     * Test positive integer.
     *
     * @throws Exception
     */
    @Test
    public void testPositiveOneCelsius() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.id("x")).clear();
        driver.findElement(By.id("x")).sendKeys("1");
        driver.findElement(By.xpath("//button[@type='button']")).click();
        try {
            assertEquals("33.8", driver.findElement(By.name("y")).getAttribute("value"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    /**
     *
     * Test no Celsius value entered.
     * (IMO the actual return value of NaN wouldn't be acceptable,
     * because it could be confusing to the end user.)
     *
     * @throws Exception
     */
    @Test
    public void testNoValueCelsius() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.id("x")).clear();
        driver.findElement(By.id("x")).sendKeys("");
        driver.findElement(By.xpath("//button[@type='button']")).click();
        try {
            assertEquals("NaN", driver.findElement(By.name("y")).getAttribute("value"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    /**
     *
     * Test that the Reset button causes Celsius
     * and Fahrenheit to be cleared to blank entries.
     *
     * @throws Exception
     */
    @Test
    public void testResetButtonCelsius() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.id("x")).clear();
        driver.findElement(By.id("x")).sendKeys("100");
        driver.findElement(By.xpath("//button[@type='button']")).click();
        try {
            assertEquals("100", driver.findElement(By.id("x")).getAttribute("value"));
            assertEquals("212", driver.findElement(By.name("y")).getAttribute("value"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        driver.findElement(By.xpath("//*[@id=\"doc\"]/form/table/tbody/tr[2]/td[2]/button[2]")).click();
        try {
            assertEquals("", driver.findElement(By.id("x")).getAttribute("value"));
            assertEquals("", driver.findElement(By.name("y")).getAttribute("value"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    /**
     *
     * Test that the Reset button causes Celsius
     * and Fahrenheit to be cleared to blank entries.
     *
     * @throws Exception
     */
    @Test
    public void testZeroCelsiusWithADecimal() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.id("x")).clear();
        driver.findElement(By.id("x")).sendKeys("0.");
        driver.findElement(By.xpath("//button[@type='button']")).click();
        try {
            assertEquals("32", driver.findElement(By.name("y")).getAttribute("value"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    /**
     *
     * Validate for a positive input with 4 decimal places
     * as defined by our BA.
     *
     * @throws Exception
     */
    @Test
    public void testPositive100CelsiusWith4DecimalPlaces() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.id("x")).clear();
        driver.findElement(By.id("x")).sendKeys("100.0001");
        driver.findElement(By.xpath("//button[@type='button']")).click();
        try {
            assertEquals("212.00018", driver.findElement(By.name("y")).getAttribute("value"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    /**
     *
     * Validate for a negative input with 4 decimal places
     * as defined by our BA.
     *
     * @throws Exception
     */
    @Test
    public void testNegative100CelsiusWith4DecimalPlaces() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.id("x")).clear();
        driver.findElement(By.id("x")).sendKeys("-100.0001");
        driver.findElement(By.xpath("//button[@type='button']")).click();
        try {
            assertEquals("-148.00018", driver.findElement(By.name("y")).getAttribute("value"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    /**
     *
     * Validate that equations are not evaluated.
     *
     * @throws Exception
     */
    @Test
    public void testCalculationInputCelsius() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.id("x")).clear();
        driver.findElement(By.id("x")).sendKeys("(-1*8)");
        driver.findElement(By.xpath("//button[@type='button']")).click();
        try {
            assertEquals("NaN", driver.findElement(By.name("y")).getAttribute("value"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    /**
     *
     * Validate upper limit of one billion
     * as defined by our BA.
     *
     * @throws Exception
     */
    @Test
    public void testPositiveOneBillionCelsius() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.id("x")).clear();
        driver.findElement(By.id("x")).sendKeys("1000000000");
        driver.findElement(By.xpath("//button[@type='button']")).click();
        try {
            assertEquals("1800000032", driver.findElement(By.name("y")).getAttribute("value"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    /**
     *
     * Validate lower limit of one billion
     * as defined by our BA.
     *
     * @throws Exception
     */
    @Test
    public void testNegativeOneBillionCelsius() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.id("x")).clear();
        driver.findElement(By.id("x")).sendKeys("-1000000000");
        driver.findElement(By.xpath("//button[@type='button']")).click();
        try {
            assertEquals("-1799999968", driver.findElement(By.name("y")).getAttribute("value"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    /**
     *
     * Tests a value with 2 decimal points.
     * It should ignore everthing after the second
     * decimal point.
     *
     * @throws Exception
     */
    @Test
    public void testTwoDecimalPointsCelsius() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.id("x")).clear();
        driver.findElement(By.id("x")).sendKeys("1.1.1");
        driver.findElement(By.xpath("//button[@type='button']")).click();
        try {
            assertEquals("33.98", driver.findElement(By.name("y")).getAttribute("value"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    /**
     *
     * Tests an alphabetical Celsius value.
     *
     * @throws Exception
     */
    @Test
    public void testAlphabeticalCelsius() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.id("x")).clear();
        driver.findElement(By.id("x")).sendKeys("abc");
        driver.findElement(By.xpath("//button[@type='button']")).click();
        try {
            assertEquals("NaN", driver.findElement(By.name("y")).getAttribute("value"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    /**
     *
     * Tests an alphanumeric Celsius value
     * with numbers first.
     * It should ignore the alphabetical values
     * at the end of the string.
     *
     * @throws Exception
     */
    @Test
    public void testAlphanumericNumbersFirstCelsius() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.id("x")).clear();
        driver.findElement(By.id("x")).sendKeys("123abc");
        driver.findElement(By.xpath("//button[@type='button']")).click();
        try {
            assertEquals("253.4", driver.findElement(By.name("y")).getAttribute("value"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    /**
     *
     * Tests an alphanumeric Celsius value
     * with numbers first.
     * It should return an error value.
     *
     * @throws Exception
     */
    @Test
    public void testAlphanumericAlphabeticalFirstCelsius() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.id("x")).clear();
        driver.findElement(By.id("x")).sendKeys("abc123");
        driver.findElement(By.xpath("//button[@type='button']")).click();
        try {
            assertEquals("NaN", driver.findElement(By.name("y")).getAttribute("value"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    /**
     *
     * Tests an special chars Celsius value.
     *
     * @throws Exception
     */
    @Test
    public void testSpecialCharactersCelsius() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.id("x")).clear();
        driver.findElement(By.id("x")).sendKeys("#!@%$^&*");
        driver.findElement(By.xpath("//button[@type='button']")).click();
        try {
            assertEquals("NaN", driver.findElement(By.name("y")).getAttribute("value"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    /**
     *
     * Tests an JavaScript code version 1 Celsius value.
     *
     * @throws Exception
     */
    @Test
    public void testJavaScriptCodeInputCelsiusV1() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.id("x")).clear();
        driver.findElement(By.id("x")).sendKeys("open(\"http://google.com/'onClick='alert(1)\", \"_self\")");
        driver.findElement(By.xpath("//button[@type='button']")).click();
        try {
            assertEquals("NaN", driver.findElement(By.name("y")).getAttribute("value"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    /**
     *
     * Tests an JavaScript code version 2 Celsius value.
     *
     * @throws Exception
     */
    @Test
    public void testJavaScriptCodeInputCelsiusV2() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.id("x")).clear();
        driver.findElement(By.id("x")).sendKeys("<script>alert('hello')</script>");
        driver.findElement(By.xpath("//button[@type='button']")).click();
        try {
            assertEquals("NaN", driver.findElement(By.name("y")).getAttribute("value"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    /**
     *
     * Tests an JavaScript code version 3 Celsius value.
     *
     * @throws Exception
     */
    @Test
    public void testJavaScriptCodeInputCelsiusV3() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.id("x")).clear();
        driver.findElement(By.id("x")).sendKeys("alert()");
        driver.findElement(By.xpath("//button[@type='button']")).click();
        try {
            assertEquals("NaN", driver.findElement(By.name("y")).getAttribute("value"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    /**
     *
     * Tests an JavaScript code version 4 Celsius value.
     *
     * @throws Exception
     */
    @Test
    public void testJavaScriptCodeInputCelsiusV4() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.id("x")).clear();
        driver.findElement(By.id("x")).sendKeys("&lt;script&gt;alert('testing')&lt;/script&gt;");
        driver.findElement(By.xpath("//button[@type='button']")).click();
        try {
            assertEquals("NaN", driver.findElement(By.name("y")).getAttribute("value"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }


    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
