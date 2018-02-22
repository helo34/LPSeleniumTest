package libreplanTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

public class TestCreationCalendrier {

	WebDriver driver;
	
	@Before
	//Connexion à l'application
	public void setup() {
		driver = new FirefoxDriver();
		driver.get("http://localhost:8080/libreplan");
	}
	
	@Test
	//Test "Créer un calendrier"
	public void creerUnCalendrier() throws Exception {
		PageConnexion pageLogin = PageFactory.initElements(driver, PageConnexion.class);
		
		//Connexion à l'application
		PageCalendrier pageAccueil = pageLogin.seConnecter("admin", "admin");
		Thread.sleep(2000);
		driver.navigate().refresh();
		
		//Navigation jusqu'à la liste des calendriers
		ListCalendar listeCalendriers = pageAccueil.accesAdminCal();
		Thread.sleep(2000);
		assertTrue(listeCalendriers.verifierColonne());
		Thread.sleep(2000);
		
		//Ouverture de la page de création de calendrier
		CreationCalendar creationCalendrier = listeCalendriers.accesCreationCalendrier();
		Thread.sleep(1000);
		
		//Création de calendrier et vérification
		ListCalendar pageListe2 = creationCalendrier.creerCalendrier("Calendrier - Test 1");
		Thread.sleep(2000);
		CreationCalendar pageCreationSousCalendrier = pageListe2.trouverCalendrier("Calendrier - Test 1");
		assertTrue(pageCreationSousCalendrier.verfiDerive("Calendrier - Test 1"));
		
		//Création d'un calendrier dérivé du même nom et vérification
		pageCreationSousCalendrier.creerCalendrier2("Calendrier - Test 1");
		Thread.sleep(2000);
		assertTrue(pageCreationSousCalendrier.verifDoublon("Calendrier - Test 1"));
		
		//Création d'un calendrier dérivé avec un nom différent
		pageCreationSousCalendrier.creerCalendrier2("Calendrier - Test Calendrier Dérivé");
		Thread.sleep(2000);
		assertTrue(pageCreationSousCalendrier.verifCreation("Calendrier - Test Calendrier Dérivé"));

		//Bouton "Annuler", retour sur la page de liste et vérification
		ListCalendar pageListe3 = pageCreationSousCalendrier.cliquerAnnuler();
		Thread.sleep(2000);
		pageListe3.verifierColonne();
		assertTrue(pageListe3.sousCalendrier("Calendrier - Test Calendrier Dérivé"));
		Thread.sleep(2000);
		
		//Bouton [-] pour fermer le sous-menu du calendrier
		assertFalse(pageListe3.cacherSousCalendrier("Calendrier - Test 1"));
		
		//Créer une copie du calendrier
		CreationCalendar creerCopie = pageListe3.creerCopieCalendrier("Calendrier - Test 1");
		Thread.sleep(2000);
		assertTrue(creerCopie.verifCreerCopie("Calendrier - Test 1"));
		creerCopie.cliquerSauver();
		Thread.sleep(2000);
		creerCopie.verifDoublon("Calendrier - Test 1");
		Thread.sleep(2000);
		creerCopie.creerCalendrier("Calendrier - Test 2");
		Thread.sleep(2000);
		ListCalendar retourListe = PageFactory.initElements(driver, ListCalendar.class);
		Thread.sleep(2000);
		retourListe.verifierMessageCreation("Calendrier - Test 2");
		assertTrue(retourListe.verifierPresenceCalendrier("Calendrier - Test 2"));
	}
}
