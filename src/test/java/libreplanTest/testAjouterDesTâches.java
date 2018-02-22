package libreplanTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

//Prérequis : Avoir lancé le test : testCreerUnProjet

public class testAjouterDesTâches {

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
		
		//Aller vers la page listant les projets enregistrés
		PageCalendrier allerPageListeProjet = PageFactory.initElements(driver, PageCalendrier.class);
		allerPageListeProjet.allerPageProjetList();
		
		//Ouvrir le projet "PROJET_TEST1"
		Thread.sleep(1000);
		PageProjetList pageListeProjet = PageFactory.initElements(driver, PageProjetList.class);
		pageListeProjet.cliquerProjetCree("PROJET_TEST1");
		
		//Vérifier que l'onglet WBS est présent
		Thread.sleep(2000);
		PageProjetDetails pageProjetDetails = PageFactory.initElements(driver, PageProjetDetails.class);
		assertTrue(pageProjetDetails.VerifierOngletWBS());
		
		//Vérifier la présence du fil d'Ariane
		Thread.sleep(1000);
		assertTrue(pageProjetDetails.VerifierFilAriane("PROJET_TEST1"));
		
		//Ajouter une nouvelle tâche au projet
		Thread.sleep(500);
		pageProjetDetails.saisirChampNewTask("Tache1-P1");
		Thread.sleep(500);
		pageProjetDetails.saisirChampHours("5");
		Thread.sleep(100);
		pageProjetDetails.clickBoutonAdd();
		
		//Vérifier la bonne prise en compte des informations saisie dans la liste des tâches
		Thread.sleep(500);
		assertTrue(pageProjetDetails.verifierPresenceNewTask("", "Tache1-P1", "5", "0 €", "", ""));
		
		//Créer plusieurs taches à la suite
		
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
		
		//Renseigner date de début pour tache 1 et 2 et Renseigner date d'échéance pour tache 3 et 4
		Thread.sleep(500);
		pageProjetDetails.SaisirDateTache();
		
		//Enregistrer les tâches
		pageProjetDetails.cliquerBoutonSaveTache();
		
		//Vérifier la présence du bouton OK, du bouton croix et du texte dans la fenetre de confirmation de sauvegarde
		Thread.sleep(500);
		assertTrue(pageProjetDetails.VerifierPresenceTexteEtBoutonsOKEtCroixFenetreSauvegardeTask());
		
		//Cliquer sur le bouton ok de la fenetre
		pageProjetDetails.cliquerBoutonOKFenetreSaveTask();
		
		//Aller dans la page de plannification de projet (bouton Project Scheduling)
		Thread.sleep(500);
		pageProjetDetails.cliquerBoutonProjectSchedulingMenuVerticalGauche();
		
	}
	
	
	
}
