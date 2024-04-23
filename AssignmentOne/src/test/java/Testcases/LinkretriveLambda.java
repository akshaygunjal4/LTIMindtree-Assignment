package Testcases;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
 
import io.github.bonigarcia.wdm.WebDriverManager;

public class LinkretriveLambda {
	
	public static WebDriver driver;
	static String URLs;
	public static void main(String[] args) throws InterruptedException, IOException{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		List<WebElement> Alllink = driver.findElements(By.xpath("//a"));
	Alllink.forEach(URLs ->
            System.out.println(URLs.getAttribute("href")));	
        
}}