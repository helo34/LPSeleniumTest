package libreplanTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

//Pr�requis : Avoir lanc� le test : testCreerUnProjet

public class testAjouterDesT�ches {

	WebDriver driver;
	
	
	@Before
	public void setupConnexion() {
		driver = new FirefoxDriver();
		driver.get("http://localhost:8080/libreplan/common/layout/login.zul");
		
		//Page connexion : authentification pour entrer dans l'application web
		PageConnexion pageLogin = PageFactory.initElements(driver, PageConnexion.class);
		pageLogin.seConnecter("admin", "admin");
	}
	
	@Test
	public void testAjouterDesTaches() throws InterruptedException{
		
		//Aller vers la page listant les projets enregistr�s
		PageCalendrier allerPageListeProjet = PageFactory.initElements(driver, PageCalendrier.class);
		allerPageListeProjet.allerPageProjetList();
		
		//Ouvrir le projet "PROJET_TEST1"
		Thread.sleep(1000);
		PageProjetList pageListeProjet = PageFactory.initElements(driver, PageProjetList.class);
		pageListeProjet.cliquerProjetCree("PROJET_TEST1");
		
		//V�rifier que l'onglet WBS est pr�sent
		Thread.sleep(2000);
		PageProjetDetails pageProjetDetails = PageFactory.initElements(driver, PageProjetDetails.class);
		assertTrue(pageProjetDetails.VerifierOngletWBS());
		
		//V�rifier la pr�sence du fil d'Ariane
		Thread.sleep(1000);
		assertTrue(pageProjetDetails.VerifierFilAriane("PROJET_TEST1"));
		
		//Ajouter une nouvelle t�che au projet
		Thread.sleep(500);
		pageProjetDetails.saisirChampNewTask("Tache1-P1");
		Thread.sleep(500);
		pageProjetDetails.saisirChampHours("5");
		Thread.sleep(100);
		pageProjetDetails.clickBoutonAdd();
		
		//V�rifier la bonne prise en compte des informations saisie dans la liste des t�ches
		Thread.sleep(500);
		assertTrue(pageProjetDetails.verifierPresenceNewTask("", "Tache1-P1", "5", "0 �", "", ""));
		
		//Cr�er plusieurs taches � la suite
		
		//tache 2
		Thread.sleep(100);
		pageProjetDetails.saisirChampNewTask("Tache2-P1");
		Thread.sleep(100);
		pageProjetDetails.saisirChampHours("10");
		Thread.sleep(100);
		pageProjetDetails.clickBoutonAdd();
		
		//tache 3
		Thread.sleep(100);
		pageProjetDetails.saisirChampNewTask("Tache3-P1");
		Thread.sleep(100);
		pageProjetDetails.saisirChampHours("20");
		Thread.sleep(100);
		pageProjetDetails.clickBoutonAdd();
		
		//tache 4
		Thread.sleep(100);
		pageProjetDetails.saisirChampNewTask("Tache4-P1");
		Thread.sleep(100);
		pageProjetDetails.saisirChampHours("8");
		Thread.sleep(100);
		pageProjetDetails.clickBoutonAdd();
		
		//Descendre la tache 1 d'un niveau
		Thread.sleep(500);
		pageProjetDetails.selectTask("Tache1-P1");
		pageProjetDetails.clickArrowDown();
		
		//Monter la tache 3 d'un niveau
		Thread.sleep(500);
		pageProjetDetails.selectTask("Tache3-P1");
		pageProjetDetails.clickArrowUp();
		
		//Renseigner pour chaque tache un code
		Thread.sleep(1000);
		pageProjetDetails.saisirCodeTaches();
		
		//Renseigner date de d�but pour tache 1 et 2 et Renseigner date d'�ch�ance pour tache 3 et 4
		Thread.sleep(500);
		pageProjetDetails.SaisirDateTache();
		
		//Enregistrer les t�ches
		pageProjetDetails.cliquerBoutonSaveTache();
		
		//V�rifier la pr�sence du bouton OK, du bouton croix et du texte dans la fenetre de confirmation de sauvegarde
		Thread.sleep(500);
		assertTrue(pageProjetDetails.VerifierPresenceTexteEtBoutonsOKEtCroixFenetreSauvegardeTask());
		
		//Cliquer sur le bouton ok de la fenetre
		pageProjetDetails.cliquerBoutonOKFenetreSaveTask();
		
		//Aller dans la page de plannification de projet (bouton Project Scheduling)
		Thread.sleep(500);
		pageProjetDetails.cliquerBoutonProjectSchedulingMenuVerticalGauche();
		
	}
	
	
	
}
