package Testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
public class LinkRetriveParallelStream {
	public static WebDriver driver;
	 public static void main(String[] args) {
		 WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.get("https://www.flipkart.com/");
			driver.manage().window().maximize();
			
			List<WebElement> allLinks = driver.findElements(By.xpath("//a"));

	        AtomicReference<List<String>> urls = new AtomicReference<>();
	        urls.set(allLinks.parallelStream()
	        		 .map(element -> element.getAttribute("href")) 
		                .filter(url -> url != null && !url.isEmpty()) 
		                .collect(Collectors.toList())); 
      
	        urls.get().forEach(System.out::println);
	        driver.quit();
	    }
}
