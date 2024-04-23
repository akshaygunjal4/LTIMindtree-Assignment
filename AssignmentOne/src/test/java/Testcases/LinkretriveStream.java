package Testcases;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
public class LinkretriveStream {
	public static WebDriver driver;
	 public static void main(String[] args) {
		 WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.get("https://www.flipkart.com/");
			driver.manage().window().maximize();
			
			List<WebElement> allLinks = driver.findElements(By.xpath("//a"));
	        
	       
	        List<String> allUrls = allLinks.stream()
	                .map(element -> element.getAttribute("href")) 
	                .filter(url -> url != null && !url.isEmpty()) 
	                .collect(Collectors.toList()); 
	       
	        allUrls.forEach(System.out::println);

	        driver.quit();

}}
