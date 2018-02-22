package libreplanTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

public class TestJourExceptionnel {

WebDriver driver;
	
	@Before
	//Connexion à l'application
	public void setup() {
		driver = new FirefoxDriver();
		driver.get("http://localhost:8080/libreplan");
	}
	
	@Test
	//Test "Ajouter un jour exceptionnel"
	public void ajoutJourExceptionnel() throws Exception {
		PageConnexion pageLogin = PageFactory.initElements(driver, PageConnexion.class);
		
		//Connexion à l'application
		PageCalendrier pageAccueil = pageLogin.seConnecter("admin", "admin");
		Thread.sleep(2000);
		//driver.navigate().refresh();
		
		//Navigation jusqu'à la liste des calendriers
		ListJoursExcepCal listeJoursExcep = pageAccueil.listJoursExcepCal();
		Thread.sleep(1000);
		assertTrue(listeJoursExcep.verifierColonne());
		
		//Ouverture de la page de création de jours exceptionnels
		CreationJourExcep pageCreationJour1 = listeJoursExcep.cliquerCreer();
		Thread.sleep(2000);
		assertTrue(pageCreationJour1.verifFormatPage());
		assertTrue(pageCreationJour1.verifContenuPage("", "red (default)", "0", "0","0","0"));
		
		//Remplissage du nom et annulation
		pageCreationJour1.remplirNom("Nom");
		Thread.sleep(2000);
		ListJoursExcepCal listeJoursExcep1 = pageCreationJour1.cliquerAnnuler();
		assertFalse(listeJoursExcep1.rechercheJour("Nom"));
		
		//Création d'un jour exceptionnel avec une couleur autre que celle par défaut
		CreationJourExcep pageCreationJour2 = listeJoursExcep1.cliquerCreer();
		Thread.sleep(1000);
		pageCreationJour2.remplirNom("Nom");
		assertTrue(pageCreationJour2.choixCouleur("green"));
		
		//Premier champ Effort standard "non conforme"
		pageCreationJour2.saisieStandard1("-1");
		pageCreationJour2.remplirNom("Nom");
		assertTrue(pageCreationJour2.erreurStandard1());
		
		//Deuxième champ Effort standard "non conforme"
		pageCreationJour2.saisieStandard1("1");
		pageCreationJour2.saisieStandard2("-1");
		pageCreationJour2.remplirNom("Nom");
		assertTrue(pageCreationJour2.erreurStandard2());
		
		//Premier champ Effort supplémentaire "non conforme"
		pageCreationJour2.saisieStandard2("1");
		pageCreationJour2.saisieExtra1("-1");
		pageCreationJour2.cliquerSauver();
		assertTrue(pageCreationJour2.erreurStandard1());
		
		//Deuxième champ Effort supplémentaire "non conforme"
		pageCreationJour2.saisieExtra1("5");
		Thread.sleep(1000);
		CreationJourExcep pageCreationJour3 = PageFactory.initElements(driver, CreationJourExcep.class);
		pageCreationJour3.saisieExtra2("-1");
		pageCreationJour3.cliquerSauverContinuer();
		assertTrue(pageCreationJour3.erreurStandard2());
		
		//Deuxième champ Effort supplémentaire conforme et enregistrement
		pageCreationJour3.saisieExtra2("3");
		pageCreationJour3.cliquerSauver();
		ListJoursExcepCal listeJoursExcep3 = PageFactory.initElements(driver, ListJoursExcepCal.class);
		listeJoursExcep3.messageCreationJour("Nom");
		Thread.sleep(1000);
		
		//Vérification des données enregistrées
		assertTrue(listeJoursExcep3.rechercheDonneesJour("Nom", "green", "1", "1", "5", "3", "No"));
		Thread.sleep(2000);
		
		//Ouverture de la page Calendriers
		ListCalendar listeCalendriers1 = listeJoursExcep3.accesAdminCal();
		Thread.sleep(2000);
		listeCalendriers1.verifierColonne();
		CreationCalendar modifCalendrier1 = listeCalendriers1.cliquerModifier("Calendrier - Test 1");
		Thread.sleep(2000);
		
		//Sélection du nouveau type d'exception créé
		modifCalendrier1.selectionTypeException2("Nom");
		modifCalendrier1.selectionDateException();
		modifCalendrier1.boutonCreerException();
		CreationCalendar modifCalendrier2 = PageFactory.initElements(driver, CreationCalendar.class);
		assertTrue(modifCalendrier2.verifMajException2("1", "1", "5", "3"));
	}
}
