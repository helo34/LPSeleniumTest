package libreplanTest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

public class testAdministrationDesCriteres {
	
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
	//Test de creation d'un critere sans enregistrement
	public void testCreerUnProjet() throws Exception {
		
		//Aller vers la page des crit�res
		PageMenu allerPageCritere = PageFactory.initElements(driver, PageMenu.class);
		allerPageCritere.accesAdminCritere();
		
		//Aller vers la page de cr�ation d'un crit�re
		PageCritere allerPageCreationCritere = PageFactory.initElements(driver, PageCritere.class);
		Thread.sleep(2000);
		allerPageCreationCritere.creerUnCritere();
		
		//Saisir les informations du nouveau crit�re
		PageCritereCreation pageCritereCreation = PageFactory.initElements(driver, PageCritereCreation.class);
		Thread.sleep(1000);
		pageCritereCreation.ecrireChampNom("Crit�re - Test bouton [Annuler]");
		Thread.sleep(1000);
		pageCritereCreation.selectType();
		pageCritereCreation.ecrireChampDescription("Crit�re - Test bouton [Annuler]");
		
		//Annuler la cr�ation du crit�re
		pageCritereCreation.cliquerBoutonAnnuler();
		Thread.sleep(1000);
		
		//V�rifier la non pr�sence du crit�re
		PageCritere retourPageCritere = PageFactory.initElements(driver, PageCritere.class);
		assertTrue(retourPageCritere.verifierAbsenceCritere("Crit�re - Test bouton [Annuler]"));
	}
	
	@Test
	//Test de creation d'un critere avec enregistrement
	public void testCreationCritereEnregistrer() throws Exception {
		
		//Aller vers la page des crit�res
		PageMenu allerPageCritere = PageFactory.initElements(driver, PageMenu.class);
		allerPageCritere.accesAdminCritere();
		
		//Aller vers la page de cr�ation d'un crit�re
		PageCritere allerPageCreationCritere = PageFactory.initElements(driver, PageCritere.class);
		Thread.sleep(2000);
		
		//Saisir les informations du nouveau crit�re
		allerPageCreationCritere.creerUnCritere();
		PageCritereCreation pageCritereCreation = PageFactory.initElements(driver, PageCritereCreation.class);
		Thread.sleep(1000);
		pageCritereCreation.ecrireChampNom("Crit�re - Test bouton [Enregistrer]");
		Thread.sleep(1000);
		pageCritereCreation.selectType();
		pageCritereCreation.ecrireChampDescription("Crit�re - Test bouton [Enregistrer]");
		
		//Enregistrer le crit�re
		pageCritereCreation.cliquerBoutonEnregistrer();
		Thread.sleep(1000);
		
		//V�rifier la pr�sence du crit�re dans la liste des crit�res
		PageCritere retourPageCritere = PageFactory.initElements(driver, PageCritere.class);
		assertTrue(retourPageCritere.verifierPresenceCritere("Crit�re - Test bouton [Enregistrer]"));
		
		
	}
	
	@Test
	//Test de creation d'un critere avec bouton enregistrement et continuer
	public void testCreationCritereEnregistrerEtContinuer() throws Exception {
		
		//Aller vers la page des crit�res
		PageMenu allerPageCritere = PageFactory.initElements(driver, PageMenu.class);
		allerPageCritere.accesAdminCritere();
		
		//Aller vers la page de cr�ation d'un crit�re
		PageCritere allerPageCreationCritere = PageFactory.initElements(driver, PageCritere.class);
		Thread.sleep(2000);
		
		//Saisir les informations du nouveau crit�re
		allerPageCreationCritere.creerUnCritere();
		PageCritereCreation pageCritereCreation = PageFactory.initElements(driver, PageCritereCreation.class);
		Thread.sleep(1000);
		pageCritereCreation.ecrireChampNom("Crit�re - Test bouton [Sauver et continuer]");
		pageCritereCreation.selectType();
		pageCritereCreation.ecrireChampDescription("Crit�re - Test bouton [Sauver et continuer]");
		
		//Enregistrer le crit�re et continuer
		pageCritereCreation.cliquerBoutonEnregistrerEtContinuer();
		Thread.sleep(1000);
		
		//V�rifier la pr�sence du texte de confirmation de sauvegarde du crit�re dans la m�me page
		assertTrue(pageCritereCreation.texteVerificationPresence("Crit�re - Test bouton [Sauver et continuer]"));
		
		//Retourner vers la page de liste des crit�res
		Thread.sleep(1000);
		pageCritereCreation.cliquerBoutonAnnuler();
		
		//V�rifier la pr�sence du crit�re cr�� dans la liste des crit�res
		PageCritere retourPageCritere = PageFactory.initElements(driver, PageCritere.class);
		Thread.sleep(1000);
		assertTrue(retourPageCritere.verifierPresenceCritere("Crit�re - Test bouton [Sauver et continuer]"));
		
		//Modifier le crit�re en cliquant sur le bouton modifier situ� sur sa ligne
		Thread.sleep(1000);
		retourPageCritere.cliquerBoutonModifierDuCritere("Crit�re - Test bouton [Sauver et continuer]");
		
		//V�rifier que la page modifier du crit�re s�lectionn� est bien apparente
		PageCritereCreation pageModificationCritere = PageFactory.initElements(driver, PageCritereCreation.class);
		Thread.sleep(1000);
		assertTrue(pageModificationCritere.verifierTitrePageModification("Crit�re - Test bouton [Sauver et continuer]"));
		
		//Modification du nom du crit�re
		Thread.sleep(1000);
		pageModificationCritere.effacerChampNom();
		pageModificationCritere.ecrireChampNom("Crit�re - Test bouton [Sauver et continuer] 2");
		
		//Annuler la modification
		Thread.sleep(1000);
		pageModificationCritere.cliquerBoutonAnnuler();
		
		//V�rifier la non prise en compte de cette modification dans la liste des crit�res
		PageCritere retourPageCritereAnnulation = PageFactory.initElements(driver, PageCritere.class);
		Thread.sleep(1000);
		assertTrue(retourPageCritereAnnulation.verifierAbsenceCritere("Crit�re - Test bouton [Sauver et continuer] 2"));
		
		//modifier le crit�re s�lectionn� en cliquant sur le nom du crit�re dans la liste
		Thread.sleep(1000);
		retourPageCritereAnnulation.cliquerBoutonModifierCritereParNom("Crit�re - Test bouton [Sauver et continuer]");
		
		//V�rifier que la page modifier du crit�re s�lectionn� est bien apparente
		PageCritereCreation pageModificationCritere1 = PageFactory.initElements(driver, PageCritereCreation.class);
		Thread.sleep(1000);
		assertTrue(pageModificationCritere1.verifierTitrePageModification("Crit�re - Test bouton [Sauver et continuer]"));
		
		//Modification du nom du crit�re
		Thread.sleep(1000);
		pageModificationCritere1.effacerChampNom();
		pageModificationCritere1.ecrireChampNom("Crit�re - Test bouton [Sauver et continuer] 2");
		
		//Cliquer sur le Bouton Enregistrer et Continuer
		Thread.sleep(1000);
		pageModificationCritere1.cliquerBoutonEnregistrerEtContinuer();
		
		//V�rifier la pr�sence du texte de confirmation de sauvegarde du crit�re dans la m�me page
		Thread.sleep(1000);
		assertTrue(pageModificationCritere1.texteVerificationPresence("Crit�re - Test bouton [Sauver et continuer] 2"));
		// V�rifier la modification du titre de la page de modification
		Thread.sleep(1000);
		assertTrue(pageModificationCritere1.verifierTitrePageModification("Crit�re - Test bouton [Sauver et continuer] 2"));
		
		//Retourner dans la page de liste des crit�res
		pageModificationCritere1.cliquerBoutonAnnuler();
		
		//V�rifier que le crit�re a bien chang� de nom dans la liste des crit�res
		PageCritere pageCritereVerif = PageFactory.initElements(driver, PageCritere.class);
		Thread.sleep(1000);
		assertTrue(pageCritereVerif.verifierPresenceCritere("Crit�re - Test bouton [Sauver et continuer] 2"));
		
		//Supprimer le crit�re
		Thread.sleep(1000);
		pageCritereVerif.cliquerBoutonSuppressionDuCritere("Crit�re - Test bouton [Sauver et continuer] 2");
		
		//V�rifier l'apparition de la popup de confirmation de suppression
		Thread.sleep(1000);
		assertTrue(pageCritereVerif.texteVerificationPresenceFenetreSuppressionCritere("Crit�re - Test bouton [Sauver et continuer] 2"));
		
		//Annuler la suppression
		Thread.sleep(1000);
		PageCritere pageCritereVerif1 = PageFactory.initElements(driver, PageCritere.class);
		pageCritereVerif1.cliquerBoutonAnnulerFenetreSuppressionCritere();
		
		//V�rifier que le crit�re est toujours pr�sent dans la liste des crit�res
		Thread.sleep(1000);
		assertTrue(pageCritereVerif1.verifierPresenceCritere("Crit�re - Test bouton [Sauver et continuer] 2"));
		
		//Cliquer de nouveau sur le bouton suppression du crit�re � supprimer
		Thread.sleep(1000);
		pageCritereVerif1.cliquerBoutonSuppressionDuCritere("Crit�re - Test bouton [Sauver et continuer] 2");
		
		//ReV�rifier l'apparition de la popup de confirmation de suppression
		Thread.sleep(1000);
		assertTrue(pageCritereVerif1.texteVerificationPresenceFenetreSuppressionCritere("Crit�re - Test bouton [Sauver et continuer] 2"));
		
		//Cliquer sur le bouton ok pour confirmer la suppression du crit�re
		Thread.sleep(1000);
		pageCritereVerif1.cliquerBoutonOkFenetreSuppressionCritere();
		
		//V�rifier la pr�sence du message de confirmation de suppression sur la page de liste des crit�re
		Thread.sleep(1000);
		assertTrue(pageCritereVerif1.MessageDeletedVerificationPresence("Crit�re - Test bouton [Sauver et continuer] 2"));
		Thread.sleep(1000);
		assertTrue(pageCritereVerif1.verifierAbsenceCritere("Crit�re - Test bouton [Sauver et continuer] 2"));
	}
	
	@After
	public void quit(){
		driver.quit();
	}
}
