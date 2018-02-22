package libreplanTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

public class testCreerUnProjet {

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
	//Cr�er Un Nouveau Projet
	public void CreerUnNouveauProjet() throws Exception{
		PageCalendrier pageCreation = PageFactory.initElements(driver, PageCalendrier.class);
		
		// V�rifier que l'on est bien sur la page Calendrier
		assertTrue(pageCreation.verifierOngletProjectPlanningPresent());
	
		//Cliquer sur le bouton NouveauProjet situ� en dessous du logo LibrePlan de la page
		pageCreation.cliquerBoutonCreateProject();
		Thread.sleep(1000);

		//V�rifier de la pr�sence des �l�ments de la page de creation d'un nouveau projet
		assertTrue(pageCreation.verifierAffichagePageCreateNewProject());
	
		//Saisir champ Nom
		pageCreation.saisirChampName("PROJET_TEST1");
		
		//D�cocher generation code et saisir dans le champ code
		Thread.sleep(1000);
		pageCreation.decocherCaseGenerationCode();
		Thread.sleep(1000);
		pageCreation.effacerChampCode();
		Thread.sleep(1000);
		pageCreation.saisirChampCode("PRJTEST001");
		
		
		//date de d�but du projet 5 jours suivant la date du jour
		Thread.sleep(1000);
		pageCreation.dateAjouter5jours();
		
		/*
		//date de fin du projet dans 15 jours
		Thread.sleep(1000);
		pageCreation.dateAjouter15jours();
		
		//Cliquer sur le bouton accepter
		Thread.sleep(1000);
		pageCreation.cliquerBoutonAccepterCreationProjet();
		
		//V�rifier la pr�sence de l'acces project details et l'onglet WBS apr�s cr�ation d'un projet
		PageProjetDetails pageNewProject = PageFactory.initElements(driver, PageProjetDetails.class);
		Thread.sleep(2000);
		assertTrue(pageNewProject.VerifierBoutonProjectDetailsEtOngletWBS());
		
		//V�rifier la pr�sence de tout les boutons du menu gauche
		Thread.sleep(1000);
		assertTrue(pageNewProject.VerifierBoutonsMenuGauche());
		
		//V�rifier la pr�sence de tout les onglets du menu horizontal
		Thread.sleep(1000);
		assertTrue(pageNewProject.VerifierOngletsBarreHorizontale());
		
		//V�rifier la pr�sence du bouton Sauvegarde du nouveau projet et du bouton Annuler Projet
		Thread.sleep(1000);
		assertTrue(pageNewProject.VerifierPresenceBoutonSaveEtCancel());
		
		//cliquer sur le bouton cancel editing
		Thread.sleep(1000);
		pageNewProject.cliquerBoutonCancel();
		
		//V�rifier les informations (texte,boutons ok & cancel) de la fen�tre de confirmation d'annulation d'�dition du projet
		Thread.sleep(1000);
		assertTrue(pageNewProject.VerifierPresenceTexteEtBoutonsOKEtCancelFenetreCancelProjet());
		
		//Cliquer sur le bouton cancel de cette fenetre d'annulation d'edition projet
		Thread.sleep(1000);
		pageNewProject.cliquerBoutonCancelFenetreAnnulationEdition();
		
		//V�rifier la pr�sence de l'acces project details et l'onglet WBS apr�s clique annulation d'�dition du projet
		Thread.sleep(1000);
		assertTrue(pageNewProject.VerifierBoutonProjectDetailsEtOngletWBS());
		
		//Recliquer sur le bouton cancel editing et cliquer sur OK
		Thread.sleep(1000);
		pageNewProject.cliquerBoutonCancel();
		Thread.sleep(1000);
		pageNewProject.cliquerBoutonOKFenetreAnnulationEdition();
		
		//V�rifier la pr�sence du bouton Projects Planning dans le menu vertical gauche de la page Calendrier
		PageCalendrier pageRetourCalendrier = PageFactory.initElements(driver, PageCalendrier.class);
		Thread.sleep(1000);
		assertTrue(pageRetourCalendrier.VerifierPresenceProjectDetailsMenuGauche());
		
		//Aller vers la page de liste des projets par le boutons projects de l'onglet Planning
		Thread.sleep(1000);
		PageMenu PageAllerListeProjets = PageFactory.initElements(driver, PageMenu.class);
		PageAllerListeProjets.accesPageProjetList();
	
		//V�rifier la pr�sence du bouton ProjectList dans le menu vertical gauche
		PageProjetList pageProjetList = PageFactory.initElements(driver, PageProjetList.class);
		Thread.sleep(1000);
		assertTrue(pageProjetList.verifierPresenceBoutonProjectsListMenuGauche());
		
		//V�rifier la pr�sence du projet cr��e dans la liste des projets ainsi que toutes les informations saisies le concernant
		Thread.sleep(1000);
		assertTrue(pageProjetList.verifierPresenceProjetCree("PROJET_TEST1", "PRJTEST001", "", "0 �","0", "PRE-SALES")); */
	}
	
}
