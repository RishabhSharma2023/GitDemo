package Pages;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;

import Base.Base;

public class FListing extends Base{
	
	By list = By.id("h_flist");
	By mobile = By.id("fmb0");
	By submit = By.xpath("//*[@id=\"add_div0\"]/div[3]/button");
	By error = By.id("fcoe");
	
	public void register() throws IOException, InterruptedException {
		Thread.sleep(3000);
		logger = report.createTest("Trying to list in Free Listing");
		
		try {
			openURL("websiteURLKey");
		wait(100, list);
		Thread.sleep(3000);
		driver.findElement(list).click();
		
		Thread.sleep(3000);
		reportPass("Free Listing page is Opened");
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/TestData.xlsx");
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(fs);
		XSSFSheet sheet = workbook.getSheet("Data");
		wait(100, mobile);
		driver.findElement(mobile).sendKeys(sheet.getRow(1).getCell(0).getStringCellValue());
		reportPass("Invalid data is entered in the Mobile number field");
		wait(100, submit);
		driver.findElement(submit).click();
		reportPass("Submit the data");
		wait(100, error);
		String str=driver.findElement(error).getText();
		System.out.println("\n");
		System.out.println("Error Message :"+str);
		System.out.println("\n");
		Screenshoot("FList");
		reportPass("Error is obtained");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

}
