package briqAssignment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class WebTabletoCSVGenerate {
	
    public static void main(String[] args) {
        
    	
    	        System.setProperty("webdriver.chrome.driver", "C:\\Users\\RISHI CHAVAN\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");

    	      
    	        WebDriver driver = new ChromeDriver();

    	        driver.get("http://the-internet.herokuapp.com/challenging_dom");

    	        
    	        WebElement table = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div/div[2]/table"));

    	     
    	        List<WebElement> rows = table.findElements(By.tagName("tr"));

    	     
    	        List<List<String>> tableData = new ArrayList<>();

    	       
    	        for (WebElement row : rows) {
    	            List<WebElement> cells = row.findElements(By.tagName("td"));
    	            List<WebElement> cells1 = row.findElements(By.tagName("th"));

    	            List<String> rowData = new ArrayList<>();
    	            List<String> rowData1 = new ArrayList<>();

    	            for (WebElement cell : cells1) {
    	            	
    	    	           
    	            	if(!cell.getText().contains("Action"))
    	                rowData1.add(cell.getText()+"      ");
    	            }

    	            for (WebElement cell : cells) {
    	            	
    	           
    	            	if(!cell.getText().contains("edit delete"))
    	                rowData.add(cell.getText());
    	            }

    	            tableData.add(rowData);
    	           
    	            tableData.add(rowData1);

    	        }

    	        
    	        for (List<String> rowData : tableData) {
    	            String rowString = rowData.stream().collect(Collectors.joining("  "));
    	            System.out.println(rowString);
    	        }

    	        driver.quit();
    	    }
    	}
