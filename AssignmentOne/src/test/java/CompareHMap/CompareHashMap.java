package CompareHMap;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CompareHashMap {
	public static WebDriver driver;
	public static String Price;
	public static String Stock;
	public static int i,j;
    public static void main(String[] args) throws IOException {
       	
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://money.rediff.com/losers/bse/daily/groupall");
		driver.manage().window().maximize();
     
        List<WebElement> rows = driver.findElements(By.xpath("//* [@class='dataTable']//tr"));
        int rowcount = rows.size();// row count of table
		
		HashMap<String, Double> hashMapExcel = new HashMap<>();
		
		HashMap<String, Double> hashMapWebTable = new HashMap<>();
	
		Workbook workbook = new XSSFWorkbook();
	    Sheet sheet = workbook.createSheet("Sheet1");
	        
		          for (i = 1 ;i <5;i++) {

			        WebElement Stockname = driver.findElement(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr["+i+"]/td[1]/a")) ;
			         Stock = Stockname.getText();
			         Row row = sheet.createRow(i);
			         Cell cell = row.createCell(0, CellType.STRING);
			         cell.setCellValue(Stock);
			         
			         String stockName = driver.findElement(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr["+i+"]/td[1]/a")).getText(); 
				    	Double stockPrice = Double.parseDouble(driver.findElement( By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr["+i+"]/td[4]")).getText()); 
				    	hashMapWebTable.put(stockName, stockPrice);
	          
	              for (j=i; j <= i;j++) {
	            	 WebElement StockPrice = driver.findElement(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr["+j+"]/td[4]")) ;
	                  Price = StockPrice.getText();
	 	             Row rowP = sheet.getRow(j);
	 				 Cell cellP = rowP.createCell(1, CellType.STRING);
	 	             cellP.setCellValue(Price);
                      } 
		            }
		             System.out.println("=======================================================");
		  
                 try (FileOutputStream fileOut = new FileOutputStream("output.xlsx")) {
        	         workbook.write(fileOut);
        	         
        	        
        	      for (Row rows1 : sheet) {
        	                String key = rows1.getCell(0).getStringCellValue(); // Assuming the key is in the first column
        	                Double value = Double.parseDouble(rows1.getCell(1).getStringCellValue());
        	                hashMapExcel.put(key, value);
        	            }
                  }
                catch (IOException e) {
                     e.printStackTrace();
                }      
                 finally 
                {
        	    for (String key : hashMapExcel.keySet()) 
        	    {
                 System.out.println("HashMapExcel "+key + ": " + hashMapExcel.get(key));
        	    }
        	    
        	    for (String key2 : hashMapWebTable.keySet()) 
        	    {
                 System.out.println("HashMapWebTable "+key2 + ": " + hashMapWebTable.get(key2));
	    	         }
        	    boolean isEqual = compareHashMaps(hashMapExcel, hashMapWebTable);
        	    if (isEqual) {
                    System.out.println("The HashMaps are equal.");
                     } 
        	    else 
                  {
                    System.out.println("The HashMaps are not equal.");
                     }
                     }
                 try { 
                	 workbook.close();
                	 }  
                 catch (IOException e) {
                      e.printStackTrace();
}
}
	private static boolean compareHashMaps(HashMap<String, Double> hashMapExcel,
			HashMap<String, Double> hashMapWebTable) {
		if (hashMapExcel.size() != hashMapWebTable.size()) {
            return false;
        }
        for (Map.Entry<String, Double> entry : hashMapExcel.entrySet()) {
            String key = entry.getKey();
            Double value1 = entry.getValue();
            if (!hashMapWebTable.containsKey(key)) {
                return false;
            }
            Double value2 = hashMapWebTable.get(key);
            if (!value1.equals(value2)) {
            	return false;
	}
}
		return true;
}
}


    
