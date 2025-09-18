package javaByKiran_OfflineWebsite_TC;


	import java.util.concurrent.TimeUnit;
	import org.openqa.selenium.Alert;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.testng.Assert;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.Test;

	public class Register_TC {
	    WebDriver driver;

	    @BeforeClass
	    public void setUp() {
	        driver = new ChromeDriver();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        driver.manage().window().maximize();
	        System.out.println("Browser Launched");
	    }

	    @AfterClass
	    public void tearDown() {
	        driver.quit();
	        System.out.println("Browser Closed");
	    }

	    @BeforeMethod
	    public void openWebsite() {
	        driver.get("https://javabykiran.com/liveproject/index.html");
	        System.out.println("Website Opened");
	    }

	    @Test
	    public void tc01CheckTitle() {
	        Assert.assertEquals(driver.getTitle(), "JavaByKiran | Log in");
	    }

	    @Test
	    public void tc02CheckUrl() {
	        Assert.assertTrue(driver.getCurrentUrl().contains("javabykiran"));
	    }

	    @Test
	    public void tc03NavigateToRegisterPage() {
	        driver.findElement(By.xpath("/html/body/div/div[2]/a")).click();
	        Assert.assertTrue(driver.getTitle().contains("Register"));
	    }

	    @Test
	    public void tc04RegisterWithValidData() {
	        driver.findElement(By.xpath("/html/body/div/div[2]/a")).click();
	        driver.findElement(By.id("name")).sendKeys("Mahek");
	        driver.findElement(By.id("mobile")).sendKeys("1234561234");
	        driver.findElement(By.id("email")).sendKeys("ab@gmail.com");
	        driver.findElement(By.id("password")).sendKeys("123");
	        driver.findElement(By.xpath("//*[@id='form']/div[5]/div/button")).click();
	        Alert alert = driver.switchTo().alert();
	        Assert.assertTrue(alert.getText().contains("User registered successfully"));
	        alert.accept();
	    }

	    @Test
	    public void tc05CheckRegisterButtonPresence() {
	        driver.findElement(By.xpath("/html/body/div/div[2]/a")).click();
	        Assert.assertTrue(driver.findElement(By.xpath("//*[@id='form']/div[5]/div/button")).isDisplayed());
	    }

	    @Test
	    public void tc06CheckRegisterButtonEnabled() {
	        driver.findElement(By.xpath("/html/body/div/div[2]/a")).click();
	        Assert.assertTrue(driver.findElement(By.xpath("//*[@id='form']/div[5]/div/button")).isEnabled());
	    }

	    @Test
	    public void tc07BlankNameField() {
	        driver.findElement(By.xpath("/html/body/div/div[2]/a")).click();
	        driver.findElement(By.id("name")).sendKeys("");
	        driver.findElement(By.id("mobile")).sendKeys("1234567890");
	        driver.findElement(By.id("email")).sendKeys("test@gmail.com");
	        driver.findElement(By.id("password")).sendKeys("123");
	        driver.findElement(By.xpath("//*[@id='form']/div[5]/div/button")).click();
	        Alert alert = driver.switchTo().alert();
	        Assert.assertTrue(alert.getText().contains("Please enter Name"));
	        alert.accept();
	    }

	    @Test
	    public void tc08InvalidEmailFormat() {
	        driver.findElement(By.xpath("/html/body/div/div[2]/a")).click();
	        driver.findElement(By.id("name")).sendKeys("Ravi");
	        driver.findElement(By.id("mobile")).sendKeys("9876543210");
	        driver.findElement(By.id("email")).sendKeys("wrongemail");
	        driver.findElement(By.id("password")).sendKeys("123");
	        driver.findElement(By.xpath("//*[@id='form']/div[5]/div/button")).click();
	        Alert alert = driver.switchTo().alert();
	        Assert.assertTrue(alert.getText().contains("Please enter valid Email"));
	        alert.accept();
	    }

	    @Test
	    public void tc09MobileNumberLengthCheck() {
	        driver.findElement(By.xpath("/html/body/div/div[2]/a")).click();
	        driver.findElement(By.id("name")).sendKeys("Sneha");
	        driver.findElement(By.id("mobile")).sendKeys("123");
	        driver.findElement(By.id("email")).sendKeys("sneha@gmail.com");
	        driver.findElement(By.id("password")).sendKeys("123");
	        driver.findElement(By.xpath("//*[@id='form']/div[5]/div/button")).click();
	        Alert alert = driver.switchTo().alert();
	        Assert.assertTrue(alert.getText().contains("Please enter valid Mobile"));
	        alert.accept();
	    }

	    @Test
	    public void tc10BackToLoginPage() {
	        driver.findElement(By.xpath("/html/body/div/div[2]/a")).click();
	        driver.findElement(By.linkText("I already have a membership")).click();
	        Assert.assertEquals(driver.getTitle(), "JavaByKiran | Log in");
	    }

	    @Test
	    public void tc11BlankMobile() {
	        driver.findElement(By.xpath("/html/body/div/div[2]/a")).click();
	        driver.findElement(By.id("name")).sendKeys("Ramesh");
	        driver.findElement(By.id("mobile")).sendKeys("");
	        driver.findElement(By.id("email")).sendKeys("ramesh@gmail.com");
	        driver.findElement(By.id("password")).sendKeys("123");
	        driver.findElement(By.xpath("//*[@id='form']/div[5]/div/button")).click();
	        Alert alert = driver.switchTo().alert();
	        Assert.assertTrue(alert.getText().contains("Please enter Mobile"));
	        alert.accept();
	    }

	    @Test
	    public void tc12BlankEmail() {
	        driver.findElement(By.xpath("/html/body/div/div[2]/a")).click();
	        driver.findElement(By.id("name")).sendKeys("Raj");
	        driver.findElement(By.id("mobile")).sendKeys("9876543210");
	        driver.findElement(By.id("email")).sendKeys("");
	        driver.findElement(By.id("password")).sendKeys("123");
	        driver.findElement(By.xpath("//*[@id='form']/div[5]/div/button")).click();
	        Alert alert=driver.switchTo().alert();
	        Assert.assertTrue(alert.getText().contains("Please enter Email"));
	        alert.accept();
	    }

	    @Test
	    public void tc13BlankPassword() {
	        driver.findElement(By.xpath("/html/body/div/div[2]/a")).click();
	        driver.findElement(By.id("name")).sendKeys("Raj");
	        driver.findElement(By.id("mobile")).sendKeys("9876543210");
	        driver.findElement(By.id("email")).sendKeys("raj@gmail.com");
	        driver.findElement(By.id("password")).sendKeys("");
	        driver.findElement(By.xpath("//*[@id='form']/div[5]/div/button")).click();
	        Alert alert=driver.switchTo().alert();
	        Assert.assertTrue(alert.getText().contains("Please enter Password"));
	        alert.accept();
	    }

	    @Test
	    public void tc14SpecialCharactersName() {
	        driver.findElement(By.xpath("/html/body/div/div[2]/a")).click();
	        driver.findElement(By.id("name")).sendKeys("Ravi@#$");
	        driver.findElement(By.id("mobile")).sendKeys("9876543210");
	        driver.findElement(By.id("email")).sendKeys("ravi@gmail.com");
	        driver.findElement(By.id("password")).sendKeys("123");
	        driver.findElement(By.xpath("//*[@id='form']/div[5]/div/button")).click();
	        Alert alert=driver.switchTo().alert();
	        Assert.assertTrue(alert.getText().contains("Please enter valid Name"));
	        alert.accept();
	    }

	    @Test
	    public void tc15TermsAndConditionsNotChecked() {
	        driver.findElement(By.xpath("/html/body/div/div[2]/a")).click();
	        driver.findElement(By.id("name")).sendKeys("Kiran");
	        driver.findElement(By.id("mobile")).sendKeys("9876543210");
	        driver.findElement(By.id("email")).sendKeys("kiran@gmail.com");
	        driver.findElement(By.id("password")).sendKeys("123");
	        // Assume checkbox id or xpath for terms checkbox if available, left unchecked here
	        driver.findElement(By.xpath("//*[@id='form']/div[5]/div/button")).click();
	        Alert alert=driver.switchTo().alert();
	        Assert.assertTrue(alert.getText().contains("Please accept Terms and Conditions"));
	        alert.accept();
	    }

	    // Add remaining test cases similarly for cases like duplicate email, password length, special chars in password,
	    // password confirmation match, email verification link etc.

	}


