package libreplanTest;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreerUnWorker {
	WebDriver driver;
	@Before
	//Connexion à l'application
	public void setup() {
		driver = new FirefoxDriver();
		driver.get("http://localhost:8080/libreplan");
	}
@Test
	
	public void creerUnWorker() throws InterruptedException{
	//Test de connexion à l'application
			PageConnexion pageLogin = PageFactory.initElements(driver, PageConnexion.class);
			pageLogin.seConnecter("admin", "admin");
			Thread.sleep(2000);
			PageMenu pageCal = PageFactory.initElements(driver, PageMenu.class);
			//Test de creation d'un participant
			//acces à la page de la liste des participants
			pageCal.listWorkers();
			Thread.sleep(2000);
			ListWorkers pageListeWorker = PageFactory.initElements(driver, ListWorkers.class);
			assertTrue(pageListeWorker.verifTableau());
			assertTrue(pageListeWorker.verifChamp());
			assertTrue(pageListeWorker.verifBouton());
			/*
			pageListeWorker.clickCreate();
			CreateWorker creerUnWorker = PageFactory.initElements(driver, CreateWorker.class);
			assertTrue(creerUnWorker.verifBlock1());
			assertTrue(creerUnWorker.verifblock2());
			//Creation du participant
			//block1 details perso
			
			creerUnWorker.block1("Jean", "DU", "jdu");
			//creation d'un utilisateur et de ses identifiants de connection
			creerUnWorker.block2("jdu", "$jdumdp1", "jdu@test.fr");
			assertTrue(pageListeWorker.verifMessage("Worker saved"));
			Thread.sleep(2000);
			pageListeWorker.clickCreate();
			creerUnWorker.block1("Jean", "AA", "aze");
			creerUnWorker.bouttonSAVE.click();
			Thread.sleep(2000);
			pageListeWorker.clickCreate();
			creerUnWorker.block1("Jean", "ZZ", "zea");
			creerUnWorker.bouttonSAVE.click();
			Thread.sleep(2000);
			pageListeWorker.clickCreate();
			creerUnWorker.block1("Jean", "DT", "zer");
			creerUnWorker.bouttonSAVE.click();
			Thread.sleep(2000);
			pageListeWorker.clickCreate();
			creerUnWorker.block1("Jean", "ZG", "zzt");
			creerUnWorker.bouttonSAVE.click();
			Thread.sleep(2000);
			pageListeWorker.clickCreate();
			creerUnWorker.block1("Jean", "ZT", "zpo");
			creerUnWorker.bouttonSAVE.click();
			Thread.sleep(2000);
			pageListeWorker.clickCreate();
			creerUnWorker.block1("Jean", "BC", "rez");
			creerUnWorker.bouttonSAVE.click();
			Thread.sleep(2000);
			pageListeWorker.clickCreate();
			creerUnWorker.block1("Jean", "CB", "eza");
			creerUnWorker.bouttonSAVE.click();
			Thread.sleep(2000);
			pageListeWorker.clickCreate();
			creerUnWorker.block1("Jean", "AC", "aez");
			creerUnWorker.bouttonSAVE.click();
			Thread.sleep(2000);
			pageListeWorker.clickCreate();
			creerUnWorker.block1("Jean", "CA", "zre");
			creerUnWorker.bouttonSAVE.click();
			Thread.sleep(2000);
			pageListeWorker.clickCreate();
			creerUnWorker.block1("Jean", "DA", "etr");
			creerUnWorker.bouttonSAVE.click();
			Thread.sleep(2000);
			pageListeWorker.clickCreate();
			creerUnWorker.block1("Jean", "DB", "ter");
			creerUnWorker.bouttonSAVE.click();
			Thread.sleep(2000);
			pageListeWorker.clickCreate();
			creerUnWorker.block1("Jean", "DC", "rze");
			creerUnWorker.bouttonSAVE.click();
			Thread.sleep(2000);
			pageListeWorker.clickCreate();
			creerUnWorker.block1("Jean", "DD", "eaz");
			creerUnWorker.bouttonSAVE.click();
			Thread.sleep(2000);
			pageListeWorker.clickCreate();
			creerUnWorker.block1("Jean", "AD", "art");	
			creerUnWorker.bouttonSAVE.click();
			Thread.sleep(2000);
			pageListeWorker.clickCreate();
			creerUnWorker.block1("Jean", "DT", "tea");
			creerUnWorker.bouttonSAVE.click();
			Thread.sleep(2000);
			*/
			Thread.sleep(2000);
			assertTrue(pageListeWorker.verifCreaUtilisateur("jdu", "Jean", "DU"));
			assertTrue(pageListeWorker.moreOptions());
			pageListeWorker.navListWorker();
			Thread.sleep(2000);
			pageCal.deconnexion();
			Thread.sleep(2000);
			pageLogin.seConnecter("jdu", "$jdumdp1");
			Thread.sleep(2000);
			PageDashbord pageDashboard = PageFactory.initElements(driver, PageDashbord.class);
			assertTrue(pageDashboard.verifConnexion());

}

}
