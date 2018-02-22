package libreplanTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

public class testAdminConnexion {

	WebDriver driver;
	
	@Before
	//Connexion à l'application
	public void setup() {
		driver = new FirefoxDriver();
		driver.get("http://localhost:8080/libreplan");
	}
	
	@Test
	//Test de connexion à l'application
	public void seLoguer() throws Exception {
		PageConnexion pageLogin = PageFactory.initElements(driver, PageConnexion.class);
		PageCalendrier pageAccueil = pageLogin.seConnecter("admin", "admin");
		Thread.sleep(2000);
		WebElement connectedUser = driver.findElement(By.xpath("//div[@class='user-area']/table[1]/tbody/tr[1]/td[2]"));
		assertEquals("user: admin", connectedUser.getText());
	}

}
