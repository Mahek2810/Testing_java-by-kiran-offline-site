package javaByKiran_OfflineWebsite_TC;



	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.testng.Assert;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.Test;

	public class Login_TC {
	    WebDriver driver;
	    String baseUrl = "https://javabykiran.com/liveproject/index.html";
	    String loginTitle = "JavaByKiran | Log in";

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
	        driver.get(baseUrl);
	        System.out.println("Login Page Opened");
	    }

	    @Test
	    public void tc01CheckTitle() {
	        Assert.assertEquals(driver.getTitle(), loginTitle, "Title mismatch");
	    }

	    @Test
	    public void tc02CheckUrl() {
	        Assert.assertTrue(driver.getCurrentUrl().contains("javabykiran"), "URL mismatch");
	    }

	    @Test
	    public void tc03EmailFieldPresence() {
	        Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed(), "Email field absent");
	    }

	    @Test
	    public void tc04PasswordFieldPresence() {
	        Assert.assertTrue(driver.findElement(By.id("password")).isDisplayed(), "Password field absent");
	    }

	    @Test
	    public void tc05LoginButtonPresence() {
	        Assert.assertTrue(driver.findElement(By.xpath("//*[@id='form']/div[3]/div/button")).isDisplayed(), "Login button absent");
	    }

	    @Test
	    public void tc06LoginWithValidCredentials() throws InterruptedException {
	        driver.findElement(By.id("email")).sendKeys("kiran@gmail.com");
	        driver.findElement(By.id("password")).sendKeys("123456");
	        driver.findElement(By.xpath("//*[@id='form']/div[3]/div/button")).click();
	        Thread.sleep(2000);
	        Assert.assertNotEquals(driver.getTitle(), loginTitle, "Failed to login with valid credentials");
	    }

	    @Test
	    public void tc07LoginWithInvalidPassword() throws InterruptedException {
	        driver.findElement(By.id("email")).sendKeys("kiran@gmail.com");
	        driver.findElement(By.id("password")).sendKeys("wrongPass");
	        driver.findElement(By.xpath("//*[@id='form']/div[3]/div/button")).click();
	        Thread.sleep(2000);
	        Assert.assertEquals(driver.getTitle(), loginTitle, "Login should fail with invalid password");
	    }

	    @Test
	    public void tc08LoginWithInvalidEmail() throws InterruptedException {
	        driver.findElement(By.id("email")).sendKeys("wrongemail@gmail.com");
	        driver.findElement(By.id("password")).sendKeys("123456");
	        driver.findElement(By.xpath("//*[@id='form']/div[3]/div/button")).click();
	        Thread.sleep(2000);
	        Assert.assertEquals(driver.getTitle(), loginTitle, "Login should fail with invalid email");
	    }

	    @Test
	    public void tc09LoginWithBlankCredentials() throws InterruptedException {
	        driver.findElement(By.id("email")).sendKeys("");
	        driver.findElement(By.id("password")).sendKeys("");
	        driver.findElement(By.xpath("//*[@id='form']/div[3]/div/button")).click();
	        Thread.sleep(2000);
	        Assert.assertEquals(driver.getTitle(), loginTitle, "Login should not succeed with blank credentials");
	    }

	    @Test
	    public void tc10ForgotPasswordLinkPresence() {
	        Assert.assertTrue(driver.findElement(By.linkText("Forgot Password?")).isDisplayed(), "Forgot Password link absent");
	    }

	    @Test
	    public void tc11RegisterLinkPresence() {
	        Assert.assertTrue(driver.findElement(By.linkText("Register a new membership")).isDisplayed(), "Register link absent");
	    }

	    @Test
	    public void tc12EmailPlaceholder() {
	        String placeholder = driver.findElement(By.id("email")).getAttribute("placeholder");
	        Assert.assertEquals(placeholder, "Email", "Email placeholder mismatch");
	    }

	    @Test
	    public void tc13PasswordPlaceholder() {
	        String placeholder = driver.findElement(By.id("password")).getAttribute("placeholder");
	        Assert.assertEquals(placeholder, "Password", "Password placeholder mismatch");
	    }

	    @Test
	    public void tc14LoginButtonText() {
	        String btnText = driver.findElement(By.xpath("//*[@id='form']/div[3]/div/button")).getText();
	        Assert.assertEquals(btnText, "Sign In", "Login button text mismatch");
	    }

	    @Test
	    public void tc15CheckLogoPresence() {
	        Assert.assertTrue(driver.findElement(By.tagName("img")).isDisplayed(), "Logo image missing");
	    }

	    @Test
	    public void tc16CheckPageHeading() {
	        String heading = driver.findElement(By.tagName("h3")).getText();
	        Assert.assertEquals(heading, "Welcome Back!", "Page heading mismatch");
	    }

	    @Test
	    public void tc17LoginButtonEnabled() {
	        Assert.assertTrue(driver.findElement(By.xpath("//*[@id='form']/div[3]/div/button")).isEnabled(), "Login button disabled");
	    }

	    @Test
	    public void tc18EmailFieldMaxLength() {
	        String longEmail= "averylongemailaddresswhichshouldbevalidated@example.com";
	        driver.findElement(By.id("email")).sendKeys(longEmail);
	        String entered = driver.findElement(By.id("email")).getAttribute("value");
	        Assert.assertEquals(entered, longEmail, "Email input max length issue");
	    }

	    @Test
	    public void tc19PasswordFieldMaxLength() {
	        String longPass= "averylongpasswordthatisallowed1234";
	        driver.findElement(By.id("password")).sendKeys(longPass);
	        String entered = driver.findElement(By.id("password")).getAttribute("value");
	        Assert.assertEquals(entered, longPass, "Password input max length issue");
	    }

	    @Test
	    public void tc20CheckRememberMeCheckboxPresence() {
	        Assert.assertTrue(driver.findElement(By.id("rememberMe")).isDisplayed(), "Remember Me checkbox missing");
	    }

	    @Test
	    public void tc21RememberMeCheckboxToggle() {
	        driver.findElement(By.id("rememberMe")).click();
	        Assert.assertTrue(driver.findElement(By.id("rememberMe")).isSelected(), "Remember Me checkbox toggle failed");
	    }

	    @Test
	    public void tc22CheckLoginWithSpecialCharEmail() throws InterruptedException {
	        driver.findElement(By.id("email")).sendKeys("test!@#gmail.com");
	        driver.findElement(By.id("password")).sendKeys("123456");
	        driver.findElement(By.xpath("//*[@id='form']/div[3]/div/button")).click();
	        Thread.sleep(2000);
	        Assert.assertEquals(driver.getTitle(), loginTitle, "Login should fail with special character in email");
	    }

	    @Test
	    public void tc23CheckEmptyPasswordFieldMessage() throws InterruptedException {
	        driver.findElement(By.id("email")).sendKeys("kiran@gmail.com");
	        driver.findElement(By.id("password")).sendKeys("");
	        driver.findElement(By.xpath("//*[@id='form']/div[3]/div/button")).click();
	        Thread.sleep(2000);
	        // Assuming error message element id="passwordError"
	        Assert.assertTrue(driver.findElement(By.id("passwordError")).isDisplayed(), "Password error message not shown");
	    }

	    @Test
	    public void tc24CheckEmptyEmailFieldMessage() throws InterruptedException {
	        driver.findElement(By.id("email")).sendKeys("");
	        driver.findElement(By.id("password")).sendKeys("123456");
	        driver.findElement(By.xpath("//*[@id='form']/div[3]/div/button")).click();
	        Thread.sleep(2000);
	        // Assuming error message element id="emailError"
	        Assert.assertTrue(driver.findElement(By.id("emailError")).isDisplayed(), "Email error message not shown");
	    }

	    @Test
	    public void tc25CheckErrorMessageInvalidLogin() throws InterruptedException {
	        driver.findElement(By.id("email")).sendKeys("wrongemail@gmail.com");
	        driver.findElement(By.id("password")).sendKeys("wrongpass");
	        driver.findElement(By.xpath("//*[@id='form']/div[3]/div/button")).click();
	        Thread.sleep(2000);
	        // Assuming error message element id="loginError"
	        Assert.assertTrue(driver.findElement(By.id("loginError")).isDisplayed(), "Login error message not shown");
	    }

	    @Test
	    public void tc26CheckPasswordMasking() {
	        String type = driver.findElement(By.id("password")).getAttribute("type");
	        Assert.assertEquals(type, "password", "Password should be masked");
	    }

	    @Test
	    public void tc27CheckTabKeyNavigation() {
	        driver.findElement(By.id("email")).sendKeys("k");
	        driver.findElement(By.id("email")).sendKeys(org.openqa.selenium.Keys.TAB);
	        String activeElement = driver.switchTo().activeElement().getAttribute("id");
	        Assert.assertEquals(activeElement, "password", "Tab key navigation failed");
	    }

	    @Test
	    public void tc28CheckLoginButtonColor() {
	        String color = driver.findElement(By.xpath("//*[@id='form']/div[3]/div/button")).getCssValue("background-color");
	        Assert.assertNotNull(color, "Login button color missing");
	    }

	    @Test
	    public void tc29CheckPageResponsive() {
	        driver.manage().window().setSize(new org.openqa.selenium.Dimension(375, 667)); // iPhone size
	        Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed(), "Page is not responsive");
	        driver.manage().window().maximize();
	    }

	    @Test
	    public void tc30CheckFooterText() {
	        String footerText = driver.findElement(By.tagName("footer")).getText();
	        Assert.assertTrue(footerText.contains("©"), "Footer text incorrect");
	    }
	}


