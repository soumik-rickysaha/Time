package Time.TimeTests;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;


/**
 * Unit test for simple App.
 */
public class TimeTest {
	WebDriver driver;
	WebDriverWait wait;
	String URL = "https://www.time4education.com/";
	Utilities utiObj = new Utilities();

	@BeforeSuite
	public void DriverSetup() {
		String DriverPath = System.getProperty("user.dir");
		System.out.println(DriverPath);
		System.setProperty("webdriver.chrome.driver", DriverPath.replace("\\", "\\\\") + "\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(URL);
	}

	@Test(priority = 1)
	public void Login() throws InterruptedException {
		// WebElement
		// LoginElement=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='time-log']/a")));
		// LoginElement.click();
		driver.navigate().to("https://www.time4education.com/login");
		// verify in login page is displayed
		WebElement Username = wait.until(ExpectedConditions.elementToBeClickable(By.id("username")));
		WebElement Password = wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
		if (Username.isDisplayed() && Password.isDisplayed()) {
			Username.sendKeys("KXCA7A423");
			Password.sendKeys("code991118guru");
			driver.findElement(By.id("loginbtn")).click();
			String strUsername = driver.findElement(By.className("usertext")).getText();
			assertEquals("Soumik Saha", strUsername);
		} else {
			System.out.println("Login Page not visible");
		}

	}

	@Test(priority = 2, dependsOnMethods = "Login")
	public void AIMCAT() {
		// Navigate to AIMCAT Section
		List<WebElement> strtest = driver.findElements(By.xpath(".//div[@class='left-head']/a"));
		for (int i = 0; i < strtest.size(); i++) {
			System.out.println(strtest.get(i).getTagName());
		}
		driver.findElement(By.xpath(".//a[text()='AIMCATs']")).click();
	}

	@Test(priority = 3, dependsOnMethods = "AIMCAT")
	public void storeresults() throws IOException {
		TakesScreenshot screenshot=screenshot=((TakesScreenshot)driver);
		File src,dest;
		// navigating to the
		List col = driver.findElements(By.xpath(".//table[@class='table table-responsive qui']/thead/tr/th"));
		System.out.println("Column Size : " + col.size());
		// .//table[@class='table table-responsive qui']//following::td
		List row = driver.findElements(By.xpath(".//table[@class='table table-responsive qui']/tbody/tr/td[1]"));
		System.out.println("row Size : " + row.size());
		String OriginalWindow=driver.getWindowHandle();
		for (int Rowcounter = 1; Rowcounter <= row.size(); Rowcounter++) {
			String TestName=driver.findElement(By.xpath(".//table[@class='table table-responsive qui']/tbody/tr[" + Rowcounter + "]/td[1]")).getText();
			String path=utiObj.createFolder(TestName, "Aimcat");
			driver.findElement(By.xpath(".//table[@class='table table-responsive qui']/tbody/tr[" + Rowcounter + "]/td[6]")).click();
			try {
				Thread.sleep(5000);
				String ResultURL=driver.findElement(By.xpath("//*[@id='adjs_div']/iframe")).getAttribute("src");
				driver.navigate().to(ResultURL);
				Thread.sleep(5000);
				//Take screenshot
				src=screenshot.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(src, new File(path+".png"));
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
}
