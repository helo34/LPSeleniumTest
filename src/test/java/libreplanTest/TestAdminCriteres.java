package libreplanTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

public class TestAdminCriteres {

	WebDriver wd;
	
	@Before
	//COnnexion à l'application
	public void setup() {
		wd = new FirefoxDriver();
		wd.get("http://localhost:8080/libreplan");
	}
	
	@Test
	//Test de connexion à l'application
	public void seLoguer() throws InterruptedException {
		PageConnexion pageLogin = PageFactory.initElements(wd, PageConnexion.class);
		pageLogin.seConnecter("admin", "admin");
		Thread.sleep(5000);
		//WebElement connectedUser = wd.findElement(By.xpath("//div[@class='user-area']/table1/tbody/tr[1]/td[2]"));
		//assertEquals("user: admin", connectedUser.getText());
		PageMenu pageCal = PageFactory.initElements(wd, PageMenu.class);
		pageCal.accesAdminCal();
		
	}

}
