package javaByKiran_OfflineWebsite_TC;




import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DashBoard_TC {
    WebDriver driver;
    String loginUrl = "https://javabykiran.com/liveproject/index.html";
    String dashboardUrl = "https://javabykiran.com/dashboard"; // adjust if needed
    String loginTitle = "JavaByKiran | Log in";
    String dashboardTitle = "JavaByKiran | Dashboard";

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        System.out.println("Browser Launched");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        System.out.println("Browser Closed");
    }

    @BeforeMethod
    public void openLoginPage() {
        driver.get(loginUrl);
        System.out.println("Login Page Opened");
    }

    // Existing Login Tests
    @Test
    public void tc01CheckTitle() {
        String actTitle = driver.getTitle();
        Assert.assertEquals(actTitle, loginTitle, "Login page title mismatch");
    }

    @Test
    public void tc02CheckUrl() {
        String actUrl = driver.getCurrentUrl();
        Assert.assertTrue(actUrl.contains("javabykiran"), "URL not correct");
    }

    @Test
    public void tc03CheckEmailFieldPresence() {
        Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed(), "Email field not present");
    }

    @Test
    public void tc04CheckPasswordFieldPresence() {
        Assert.assertTrue(driver.findElement(By.id("password")).isDisplayed(), "Password field not present");
    }

    @Test
    public void tc05CheckLoginButtonPresence() {
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/div/button")).isDisplayed(), "Login button missing");
    }

    @Test
    public void tc06LoginWithValidCredentials() throws InterruptedException {
        driver.findElement(By.id("email")).sendKeys("kiran@gmail.com");
        driver.findElement(By.id("password")).sendKeys("123456");
        driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/div/button")).click();
        Thread.sleep(1000);
        String actTitle = driver.getTitle();
        Assert.assertNotEquals(actTitle, loginTitle, "Login failed with valid credentials");
    }

    @Test
    public void tc07LoginWithInvalidPassword() throws InterruptedException {
        driver.findElement(By.id("email")).sendKeys("kiran@gmail.com");
        driver.findElement(By.id("password")).sendKeys("wrongpass");
        driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/div/button")).click();
        Thread.sleep(1000);
        String actTitle = driver.getTitle();
        Assert.assertEquals(actTitle, loginTitle, "User logged in with invalid password!");
    }

    @Test
    public void tc08LoginWithInvalidEmail() throws InterruptedException {
        driver.findElement(By.id("email")).sendKeys("wrong@gmail.com");
        driver.findElement(By.id("password")).sendKeys("123456");
        driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/div/button")).click();
        Thread.sleep(1000);
        String actTitle = driver.getTitle();
        Assert.assertEquals(actTitle, loginTitle, "User logged in with invalid email!");
    }

    @Test
    public void tc09LoginWithBlankCredentials() throws InterruptedException {
        driver.findElement(By.id("email")).sendKeys("");
        driver.findElement(By.id("password")).sendKeys("");
        driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/div/button")).click();
        Thread.sleep(1000);
        String actTitle = driver.getTitle();
        Assert.assertEquals(actTitle, loginTitle, "Blank login should not work!");
    }

    @Test
    public void tc10CheckRegisterLink() {
        boolean isLinkPresent = driver.findElement(By.xpath("/html/body/div/div[2]/a")).isDisplayed();
        Assert.assertTrue(isLinkPresent, "Register link not found on login page");
    }

    // ---------------- Dashboard Tests Start ----------------
    // After successful login, navigate to dashboard for these test cases
    // This method logs in with valid credentials before dashboard tests
    public void login() throws InterruptedException {
        driver.get(loginUrl);
        driver.findElement(By.id("email")).sendKeys("kiran@gmail.com");
        driver.findElement(By.id("password")).sendKeys("123456");
        driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/div/button")).click();
        Thread.sleep(1500);
    }

    @Test
    public void dashboard_tc01CheckTitle() throws InterruptedException {
        login();
        Assert.assertEquals(driver.getTitle(), dashboardTitle, "Dashboard title mismatch");
    }

    @Test
    public void dashboard_tc02UserProfilePresence() throws InterruptedException {
        login();
        Assert.assertTrue(driver.findElement(By.id("userProfile")).isDisplayed(), "User profile missing");
    }

    @Test
    public void dashboard_tc03NavigationMenuPresence() throws InterruptedException {
        login();
        Assert.assertTrue(driver.findElement(By.id("navMenu")).isDisplayed(), "Navigation menu missing");
    }

    @Test
    public void dashboard_tc04NotificationsPresence() throws InterruptedException {
        login();
        Assert.assertTrue(driver.findElement(By.id("notifications")).isDisplayed(), "Notifications missing");
    }

    @Test
    public void dashboard_tc05LogoutButtonPresence() throws InterruptedException {
        login();
        Assert.assertTrue(driver.findElement(By.id("logoutBtn")).isDisplayed(), "Logout button missing");
    }

    @Test
    public void dashboard_tc06LogoutFunctionality() throws InterruptedException {
        login();
        driver.findElement(By.id("logoutBtn")).click();
        Thread.sleep(1000);
        Assert.assertEquals(driver.getTitle(), loginTitle, "Logout did not redirect to login page");
    }

    @Test
    public void dashboard_tc07WidgetsPresence() throws InterruptedException {
        login();
        Assert.assertTrue(driver.findElement(By.id("widget1")).isDisplayed(), "Widget 1 missing");
        Assert.assertTrue(driver.findElement(By.id("widget2")).isDisplayed(), "Widget 2 missing");
    }

    @Test
    public void dashboard_tc08SearchFunctionality() throws InterruptedException {
        login();
        driver.findElement(By.id("searchBox")).sendKeys("Selenium");
        driver.findElement(By.id("searchBtn")).click();
        Thread.sleep(500);
        Assert.assertTrue(driver.findElement(By.id("searchResults")).isDisplayed(), "Search results not displayed");
    }

    // Continue similarly for tc09 to tc35, adding meaningful test steps, asserts and locator updates

    // Example placeholders:
    @Test
    public void dashboard_tc09ResponsiveDesign() throws InterruptedException {
        login();
        // Test responsive behavior by resizing window and verifying elements
    }

    @Test
    public void dashboard_tc10ProfileUpdate() throws InterruptedException {
        login();
        // Code to test profile update functionality
    }

    // ... add remaining tests similarly ...

}
