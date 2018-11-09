import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PDFReaderTest {

	@Test
	public void readPDFTest() throws IOException
	{
		
		System.setProperty("webdriver.chrome.driver", "D:\\MaheshAutomation\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.betterteam.com/downloads/employee-information-form-download-20170810.pdf");
		String currenturl=driver.getCurrentUrl();
		URL url=new URL(currenturl);
		
		InputStream is=url.openStream();
		
		BufferedInputStream fileParse=new BufferedInputStream(is);
		PDDocument document=null;
		
		document=PDDocument.load(fileParse);
		String pdfcontent=new PDFTextStripper().getText(document);
		System.out.println(pdfcontent);
		
		Assert.assertTrue(pdfcontent.contains("Company Name "));
		Assert.assertTrue(pdfcontent.contains("Employee Information "));
		Assert.assertTrue(pdfcontent.contains("Address:"));
	}
}
