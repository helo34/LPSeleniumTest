package libreplanTest;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

public class CreerDesProgressTypes {
	WebDriver driver;
	@Before
	//COnnexion à l'application
	public void setup() {
		driver = new FirefoxDriver();
		driver.get("http://localhost:8080/libreplan");
	}
	
	@Test
	
	public void creerDesProgressTypes() throws InterruptedException {
		//Test de connexion à l'application
		PageConnexion pageLogin = PageFactory.initElements(driver, PageConnexion.class);
		pageLogin.seConnecter("admin", "admin");
		Thread.sleep(2000);
		PageMenu pageCal = PageFactory.initElements(driver, PageMenu.class);
		//Test de creation d'un type de progress
		pageCal.accesProgress();
		Thread.sleep(2000);
		PageProgress pageProgress = PageFactory.initElements(driver, PageProgress.class);
		pageProgress.clickBoutonCreate();
		Thread.sleep(2000);
		CreateProgressType createProgressType = PageFactory.initElements(driver, CreateProgressType.class);
		createProgressType.creaTypeProgress1("Type avancement -Test1", "10.00");
		Thread.sleep(2000);
		assertTrue("Verification failed: Element1 and Element2 are not same.","Progress Type \"Type avancement -Test1\" saved".equals(driver.findElement(By.id(pageCal.prefix()+"s7")).getText()));
		Thread.sleep(2000);
		assertTrue(createProgressType.contientLaReference("Type avancement -Test1"));
		pageProgress.clickBoutonCreate();
		Thread.sleep(2000);
		createProgressType.creaTypeProgress2("Type avancement -Test2");
		Thread.sleep(2000);
		assertTrue("Verification failed: Element3 and Element4 are not same.","Progress Type \"Type avancement -Test2\" saved".equals(driver.findElement(By.id(pageCal.prefix()+"47")).getText()));
		assertTrue(createProgressType.contientLaReference("Type avancement -Test2"));
	}
}
	