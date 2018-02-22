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
		
		//Aller vers la page des critères
		PageMenu allerPageCritere = PageFactory.initElements(driver, PageMenu.class);
		allerPageCritere.accesAdminCritere();
		
		//Aller vers la page de création d'un critère
		PageCritere allerPageCreationCritere = PageFactory.initElements(driver, PageCritere.class);
		Thread.sleep(2000);
		allerPageCreationCritere.creerUnCritere();
		
		//Saisir les informations du nouveau critère
		PageCritereCreation pageCritereCreation = PageFactory.initElements(driver, PageCritereCreation.class);
		Thread.sleep(1000);
		pageCritereCreation.ecrireChampNom("Critère - Test bouton [Annuler]");
		Thread.sleep(1000);
		pageCritereCreation.selectType();
		pageCritereCreation.ecrireChampDescription("Critère - Test bouton [Annuler]");
		
		//Annuler la création du critère
		pageCritereCreation.cliquerBoutonAnnuler();
		Thread.sleep(1000);
		
		//Vérifier la non présence du critère
		PageCritere retourPageCritere = PageFactory.initElements(driver, PageCritere.class);
		assertTrue(retourPageCritere.verifierAbsenceCritere("Critère - Test bouton [Annuler]"));
	}
	
	@Test
	//Test de creation d'un critere avec enregistrement
	public void testCreationCritereEnregistrer() throws Exception {
		
		//Aller vers la page des critères
		PageMenu allerPageCritere = PageFactory.initElements(driver, PageMenu.class);
		allerPageCritere.accesAdminCritere();
		
		//Aller vers la page de création d'un critère
		PageCritere allerPageCreationCritere = PageFactory.initElements(driver, PageCritere.class);
		Thread.sleep(2000);
		
		//Saisir les informations du nouveau critère
		allerPageCreationCritere.creerUnCritere();
		PageCritereCreation pageCritereCreation = PageFactory.initElements(driver, PageCritereCreation.class);
		Thread.sleep(1000);
		pageCritereCreation.ecrireChampNom("Critère - Test bouton [Enregistrer]");
		Thread.sleep(1000);
		pageCritereCreation.selectType();
		pageCritereCreation.ecrireChampDescription("Critère - Test bouton [Enregistrer]");
		
		//Enregistrer le critère
		pageCritereCreation.cliquerBoutonEnregistrer();
		Thread.sleep(1000);
		
		//Vérifier la présence du critère dans la liste des critères
		PageCritere retourPageCritere = PageFactory.initElements(driver, PageCritere.class);
		assertTrue(retourPageCritere.verifierPresenceCritere("Critère - Test bouton [Enregistrer]"));
		
		
	}
	
	@Test
	//Test de creation d'un critere avec bouton enregistrement et continuer
	public void testCreationCritereEnregistrerEtContinuer() throws Exception {
		
		//Aller vers la page des critères
		PageMenu allerPageCritere = PageFactory.initElements(driver, PageMenu.class);
		allerPageCritere.accesAdminCritere();
		
		//Aller vers la page de création d'un critère
		PageCritere allerPageCreationCritere = PageFactory.initElements(driver, PageCritere.class);
		Thread.sleep(2000);
		
		//Saisir les informations du nouveau critère
		allerPageCreationCritere.creerUnCritere();
		PageCritereCreation pageCritereCreation = PageFactory.initElements(driver, PageCritereCreation.class);
		Thread.sleep(1000);
		pageCritereCreation.ecrireChampNom("Critère - Test bouton [Sauver et continuer]");
		pageCritereCreation.selectType();
		pageCritereCreation.ecrireChampDescription("Critère - Test bouton [Sauver et continuer]");
		
		//Enregistrer le critère et continuer
		pageCritereCreation.cliquerBoutonEnregistrerEtContinuer();
		Thread.sleep(1000);
		
		//Vérifier la présence du texte de confirmation de sauvegarde du critère dans la même page
		assertTrue(pageCritereCreation.texteVerificationPresence("Critère - Test bouton [Sauver et continuer]"));
		
		//Retourner vers la page de liste des critères
		Thread.sleep(1000);
		pageCritereCreation.cliquerBoutonAnnuler();
		
		//Vérifier la présence du critère créé dans la liste des critères
		PageCritere retourPageCritere = PageFactory.initElements(driver, PageCritere.class);
		Thread.sleep(1000);
		assertTrue(retourPageCritere.verifierPresenceCritere("Critère - Test bouton [Sauver et continuer]"));
		
		//Modifier le critère en cliquant sur le bouton modifier situé sur sa ligne
		Thread.sleep(1000);
		retourPageCritere.cliquerBoutonModifierDuCritere("Critère - Test bouton [Sauver et continuer]");
		
		//Vérifier que la page modifier du critère sélectionné est bien apparente
		PageCritereCreation pageModificationCritere = PageFactory.initElements(driver, PageCritereCreation.class);
		Thread.sleep(1000);
		assertTrue(pageModificationCritere.verifierTitrePageModification("Critère - Test bouton [Sauver et continuer]"));
		
		//Modification du nom du critère
		Thread.sleep(1000);
		pageModificationCritere.effacerChampNom();
		pageModificationCritere.ecrireChampNom("Critère - Test bouton [Sauver et continuer] 2");
		
		//Annuler la modification
		Thread.sleep(1000);
		pageModificationCritere.cliquerBoutonAnnuler();
		
		//Vérifier la non prise en compte de cette modification dans la liste des critères
		PageCritere retourPageCritereAnnulation = PageFactory.initElements(driver, PageCritere.class);
		Thread.sleep(1000);
		assertTrue(retourPageCritereAnnulation.verifierAbsenceCritere("Critère - Test bouton [Sauver et continuer] 2"));
		
		//modifier le critère sélectionné en cliquant sur le nom du critère dans la liste
		Thread.sleep(1000);
		retourPageCritereAnnulation.cliquerBoutonModifierCritereParNom("Critère - Test bouton [Sauver et continuer]");
		
		//Vérifier que la page modifier du critère sélectionné est bien apparente
		PageCritereCreation pageModificationCritere1 = PageFactory.initElements(driver, PageCritereCreation.class);
		Thread.sleep(1000);
		assertTrue(pageModificationCritere1.verifierTitrePageModification("Critère - Test bouton [Sauver et continuer]"));
		
		//Modification du nom du critère
		Thread.sleep(1000);
		pageModificationCritere1.effacerChampNom();
		pageModificationCritere1.ecrireChampNom("Critère - Test bouton [Sauver et continuer] 2");
		
		//Cliquer sur le Bouton Enregistrer et Continuer
		Thread.sleep(1000);
		pageModificationCritere1.cliquerBoutonEnregistrerEtContinuer();
		
		//Vérifier la présence du texte de confirmation de sauvegarde du critère dans la même page
		Thread.sleep(1000);
		assertTrue(pageModificationCritere1.texteVerificationPresence("Critère - Test bouton [Sauver et continuer] 2"));
		// Vérifier la modification du titre de la page de modification
		Thread.sleep(1000);
		assertTrue(pageModificationCritere1.verifierTitrePageModification("Critère - Test bouton [Sauver et continuer] 2"));
		
		//Retourner dans la page de liste des critères
		pageModificationCritere1.cliquerBoutonAnnuler();
		
		//Vérifier que le critère a bien changé de nom dans la liste des critères
		PageCritere pageCritereVerif = PageFactory.initElements(driver, PageCritere.class);
		Thread.sleep(1000);
		assertTrue(pageCritereVerif.verifierPresenceCritere("Critère - Test bouton [Sauver et continuer] 2"));
		
		//Supprimer le critère
		Thread.sleep(1000);
		pageCritereVerif.cliquerBoutonSuppressionDuCritere("Critère - Test bouton [Sauver et continuer] 2");
		
		//Vérifier l'apparition de la popup de confirmation de suppression
		Thread.sleep(1000);
		assertTrue(pageCritereVerif.texteVerificationPresenceFenetreSuppressionCritere("Critère - Test bouton [Sauver et continuer] 2"));
		
		//Annuler la suppression
		Thread.sleep(1000);
		PageCritere pageCritereVerif1 = PageFactory.initElements(driver, PageCritere.class);
		pageCritereVerif1.cliquerBoutonAnnulerFenetreSuppressionCritere();
		
		//Vérifier que le critère est toujours présent dans la liste des critères
		Thread.sleep(1000);
		assertTrue(pageCritereVerif1.verifierPresenceCritere("Critère - Test bouton [Sauver et continuer] 2"));
		
		//Cliquer de nouveau sur le bouton suppression du critère à supprimer
		Thread.sleep(1000);
		pageCritereVerif1.cliquerBoutonSuppressionDuCritere("Critère - Test bouton [Sauver et continuer] 2");
		
		//ReVérifier l'apparition de la popup de confirmation de suppression
		Thread.sleep(1000);
		assertTrue(pageCritereVerif1.texteVerificationPresenceFenetreSuppressionCritere("Critère - Test bouton [Sauver et continuer] 2"));
		
		//Cliquer sur le bouton ok pour confirmer la suppression du critère
		Thread.sleep(1000);
		pageCritereVerif1.cliquerBoutonOkFenetreSuppressionCritere();
		
		//Vérifier la présence du message de confirmation de suppression sur la page de liste des critère
		Thread.sleep(1000);
		assertTrue(pageCritereVerif1.MessageDeletedVerificationPresence("Critère - Test bouton [Sauver et continuer] 2"));
		Thread.sleep(1000);
		assertTrue(pageCritereVerif1.verifierAbsenceCritere("Critère - Test bouton [Sauver et continuer] 2"));
	}
	
	@After
	public void quit(){
		driver.quit();
	}
}
