package IderaAutomation;

import org.testng.annotations.Test;
import org.testng.Assert;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

public class Idera_copy {
	public static WiniumDriver driver = null;
	public static SoftAssert softAssertion= new SoftAssert();
	public static Process p=null;
	
	
	@BeforeTest
	public static void beforeTest() {
		System.out.println("\n================Test Started for Alter Table================\n");

		Runtime r=Runtime.getRuntime(); 
		/*try
		{
			p.destroy();
			System.out.println("Winnium driver is Closed");
		}catch(Exception e) {
			System.out.println("Winnium driver not open");
		}
	    //Process p=null; 
	
*/	
		
		try 
	    { 
	      String s="D:\\Java\\com.ideraAutomation.test\\winiumLauncher\\Winium.exe"; 
	      p=r.exec(s); 
	    } 
	    catch(Exception e){ 
	      System.out.println("error==="+e.getMessage()); 
	      e.printStackTrace();
	    }
		
	
	}
	
	@Step("{0}")
	public static void logStep(String stepName) {
		
	}
	
	@Title("Check compare list in alter table--- ")
	  @Test (priority =1)
	    public static void compareList() throws MalformedURLException, InterruptedException, Exception {
		//System.out.println("Hello");
		 
		DesktopOptions option = new DesktopOptions();
		logStep("SQLyog application launched");
		//please change the path, if release changed 
		option.setApplicationPath("D:\\Java\\com.ideraAutomation.test\\IderaLauncher\\Release\\SQLyog.exe");
		option.setDebugConnectToRunningApp(false);
		option.setLaunchDelay(2);
	
		driver = new WiniumDriver(new URL("http://localhost:9999"), option);
		Thread.sleep(5000);
		logStep("Verify the title of the applications is :--SQLyog Ultimate");
		driver.findElement(By.xpath("//*[contains(@Name, 'SQLyog Ultimate 64')]")).click();
		
		//WebElement window = driver.findElement(By.className("MainWindow"));
		//System.out.println(window.getAttribute("Name"));
		Thread.sleep(5000);
		

		WebElement tree = driver .findElement(By.id("1009"));
		tree.sendKeys(Keys.ENTER);
		WebElement searchBox = driver.findElement(By.id("1103"));
		//searchBox.clear();
		searchBox.sendKeys("idera_test");
		searchBox.submit();
		searchBox.clear();
		logStep("Verify that selecting table 1 for check and select alter");
		searchBox.sendKeys("table_01");
		searchBox.submit();
		Actions act = new Actions(driver);
		WebElement menubar = driver.findElement(By.id("MenuBar"));
		
		//System.out.println("Menu bar is :"+menubar.getAttribute("Name"));
		List<WebElement> menuitems = menubar.findElements(By.xpath("//*[contains(@ControlType,'MenuItem')]"));
		logStep("Selecting alter option to check compare list");
		for(WebElement item:menuitems) {
			if(item.getAttribute("Name").equals("Table")) {
				item.click();
				List<WebElement> subMenuItems = menubar.findElements(By.xpath("//*[contains(@ControlType,'MenuItem')]"));
				//System.out.println("Menubar size is : "+subMenuItems.size());
				for(WebElement subItems:subMenuItems) {
					//System.out.println("SubItem is : "+subItems.getAttribute("Name"));
					
					if(subItems.getAttribute("Name").contains("Alter Table")) {
						subItems.click();
						//WebElement table = driver.findElement(By.id("1040"));
						WebElement mainPan = driver.findElement(By.id("2022"));
						int x=380;
						int y =13;
						act.moveByOffset(x,y).click().build().perform();
						act.moveToElement(mainPan, x, y).click().perform();
						break;
					}
				}
				
				WebElement roformateCombo = driver.findElement(By.id("1161"));
				roformateCombo.click();
				WebElement ListBox = roformateCombo.findElement(By.id("ListBox"));
				List<WebElement> rowFormateList = ListBox.findElements(By.xpath("//*[contains(@ControlType,'ListItem')]"));
				logStep("Check compare list to veriy '\"compact\",\"redundant' added or not");
				String[] checklist= {"compact","redundant"};
				for(int j=0;j<=checklist.length-1;j++) {
					//System.out.println("row list size :"+rowFormateList.size());
					for(int i =1;i<=rowFormateList.size();i++) {

						if(rowFormateList.get(i).getAttribute("Name").equals(checklist[j].toString())) {
							logStep(checklist[j].toString()+" -- Text is added to row format Combo :");
							System.out.println(checklist[j].toString()+" -- Text is added to row format Combo :");
							break;
						}else {
							if(i==rowFormateList.size()-1) {
								logStep(checklist[j].toString()+" -- Text is not added to row format Combo ");
								Assert.fail(checklist[j].toString()+" -- Text is not added to row format Combo :");
								//Assert.fail(checklist[j].toString()+" -- Text is not added to row format Combo :");
								break;
							}
						}
					}
				}
				softAssertion.assertAll();
				System.out.println("\n=================Alter Table Test Completed ===================\n");
				
			}

		}
		
	}
	@Title("Check Show all & Hide is working and check the query selected is working or not  ")
	  @Test (priority=2)
	  public static void openTable() throws MalformedURLException, InterruptedException, Exception {
		  System.out.println("================Test Started for Open Table================\n");
		  
		  	//driver.findElement(By.id("1010")).clear();
		  	logStep("Try to select Table to verify the features");
			Actions act = new Actions(driver);
			WebElement tree = driver .findElement(By.id("1009"));
			tree.clear();
			tree.sendKeys(Keys.ENTER);
			WebElement searchBox = driver.findElement(By.id("1103"));
			searchBox.sendKeys("idera_test");
			searchBox.submit();
			searchBox.clear();
			logStep("Selected table_01");
			searchBox.sendKeys("table_01");
			searchBox.submit();
			
			driver.findElement(By.id("Item 5")).click();
			logStep("Slect open table option");
			driver.findElement(By.id("Item 40151")).click();
			
			logStep("Clear the Query frame and try to type select query : -----Select * from Idera_test.table_01;");
			driver.findElement(By.id("1010")).sendKeys("Select * from Idera_test.table_01;");
			
			driver.findElement(By.id("Item 40077")).click();
			driver.findElement(By.id("Item 40108")).click();
			driver.findElement(By.id("Item 40108")).click();
			driver.findElement(By.id("1")).click();
			
			String data = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor); 
			logStep("Result Of Query:  \"Select * from Idera_test.table_01;\" \n"+data);
			System.out.println("Result Of Query:  \"Select * from Idera_test.table_01;\" \n"+data);
			
			
			
			
			WebElement mPan = driver.findElement(By.id("1011"));
			int x=345;
			int y =12;
			act.moveByOffset(x,y).click().build().perform();
			act.moveToElement(mPan, x, y).click().perform();
			
			driver.findElement(By.id("Item 41701")).click();
			
			
			
			
			 driver.findElement(By.id("100")).click();
			 driver.findElement(By.name("ID")).click();
			 driver.findElement(By.id("101")).click();
			 driver.findElement(By.name((">"))).click();
			 driver.findElement(By.id("102")).sendKeys("0");
			 
			 driver.findElement(By.id("103")).click();
			 driver.findElement(By.id("103")).sendKeys("Name");
			 driver.findElement(By.id("104")).click();
			 driver.findElement(By.id("104")).sendKeys("=");
			 driver.findElement(By.id("105")).sendKeys("AAA");
			 
			 driver.findElement(By.id("106")).click();
			 driver.findElement(By.id("106")).sendKeys("Dept");
			 driver.findElement(By.id("107")).click();
			 driver.findElement(By.id("107")).sendKeys("LIKE");
			 driver.findElement(By.id("108")).sendKeys("IT");
			 
			 driver.findElement(By.id("109")).click();
			 driver.findElement(By.id("109")).sendKeys("");
			 
			Thread.sleep(3000);
			WebElement text = driver.findElement(By.id("2646"));
			//System.out.println("Text is :"+text.getAttribute("Name"));
			logStep("Verify Show SQL Preview");
			if(text.getAttribute("Name").equals("Show SQL Preview")&& text.isEnabled()) {
				logStep("\n\n Show SQL Link is Visible and Able to click");
				System.out.println("\n\n Show SQL Link is Visible and Able to click");
				
			}
			
			else {
				logStep("\n\n Show SQL link is not visible");
				System.out.println("\n\n Show SQL link is not visible");
			}
			
			
			text.click();
			
			WebElement hidetext = driver.findElement(By.id("2647"));
			logStep("Hide SQL Preview");
			if(hidetext.getAttribute("Name").equals("Hide SQL Preview")&& hidetext.isEnabled()) {
				logStep("\n\n Hide SQL Link is Visible and Able to click");
				System.out.println("\n\n Hide SQL Link is Visible and Able to click");
				
			}
			
			else {
				logStep("\n\n Hide SQL link is not visible");
				System.out.println("\n\n Hide SQL link is not visible");
			}
			Thread.sleep(2000);
			WebElement newquery = driver.findElement(By.id("1424"));
			
			Thread.sleep(1000);
			Actions rightaction = new Actions(driver);
			rightaction.moveToElement(newquery, 17, 6).contextClick().sendKeys("S");
			rightaction.moveToElement(newquery, 17, 6).contextClick().sendKeys("C");
			rightaction.build().perform();
			
			String Query = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor); 
			logStep("\n\nNew Updated Quary : "+Query.toString());
			System.out.println("\n\nNew Updated Quary : "+Query.toString());
			driver.findElement(By.id("1")).click();
			
			driver.findElement(By.id("Item 40108")).click();
			driver.findElement(By.id("Item 40108")).click();
			driver.findElement(By.id("1")).click();
			
			String newResult = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor); 
			logStep("\nResult Of New Updated Query:\n"+newResult);
			System.out.println("\nResult Of New Updated Query:\n"+newResult);
			System.out.println("\n================Open Table Test completed================\n");
			
	  }
	
	@AfterTest
	public static void afterTest() {
		driver.close();
		p.destroy();
		
		
	}

}
