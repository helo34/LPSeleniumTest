package libreplanTest;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

public class CreerUneMachine {

	WebDriver driver;
	@Before
	//Connexion à l'application
	public void setup() {
		driver = new FirefoxDriver();
		driver.get("http://localhost:8080/libreplan");
	}
@Test
	public void creerUneMachine() throws InterruptedException{
	//Test de connexion à l'application
			PageConnexion pageLogin = PageFactory.initElements(driver, PageConnexion.class);
			pageLogin.seConnecter("admin", "admin");
			Thread.sleep(2000);
			PageMenu pageCal = PageFactory.initElements(driver, PageMenu.class);
			pageCal.listMachine();
			ListMachine pageListMachine = PageFactory.initElements(driver, ListMachine.class);
			assertTrue(pageListMachine.verifTableau());
			assertTrue(pageListMachine.verifChamp());
			assertTrue(pageListMachine.verifBouton());
			pageListMachine.selectBoutonCreate();
			CreateMachine creerMachine = PageFactory.initElements(driver, CreateMachine.class);
			assertTrue(creerMachine.verifBlockCreateMachine());
			//assertTrue(creerMachine.boutonDispo());
			creerMachine.blockCreateMachine("MACHINETEST1", "MACHINETEST1");
			assertTrue(creerMachine.verifMessage("Machine \"MACHINETEST1\" saved"));
			creerMachine.retournerListeMachine();
			Thread.sleep(5000);
			assertTrue(pageListMachine.verifCreaMACHINETEST("MACHINETEST1", "MACHINETEST1", "MACHINETEST1"));
			
			
			
}			
}
