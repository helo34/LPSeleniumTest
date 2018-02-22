package libreplanTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

public class TestAjoutExceptionCalendrier {

	WebDriver driver;
	
	@Before
	//Connexion � l'application
	public void setup() {
		driver = new FirefoxDriver();
		driver.get("http://localhost:8080/libreplan");
	}
	
	@Test
	//Test "Ajouter une exception � un calendrier"
	public void ajoutExceptionCalendrier() throws Exception {
		PageConnexion pageLogin = PageFactory.initElements(driver, PageConnexion.class);
		
		//Connexion � l'application
		PageCalendrier pageAccueil = pageLogin.seConnecter("admin", "admin");
		Thread.sleep(2000);
		driver.navigate().refresh();
		
		//Navigation jusqu'� la liste des calendriers
		ListCalendar listeCalendriers = pageAccueil.accesAdminCal();
		Thread.sleep(2000);
		assertTrue(listeCalendriers.verifierColonne());
		Thread.sleep(2000);
		
		//Ouverture de la page d'�dition
		CreationCalendar pageEditionCalendrier = listeCalendriers.cliquerModifier("Calendrier - Test 1");
		assertTrue(pageEditionCalendrier.verifierPageEdition("Calendrier - Test 1"));
		
		//Cr�ation d'une exception - Message d'erreur 1
		pageEditionCalendrier.boutonCreerException();
		assertTrue(pageEditionCalendrier.popupTypeException());
		
		//Cr�ation d'une exception - Message d'erreur 2
		pageEditionCalendrier.selectionTypeException();
		pageEditionCalendrier.boutonCreerException();
		assertTrue(pageEditionCalendrier.popupTypeException2());
		
		//Cr�ation d'une exception - Succ�s
		pageEditionCalendrier.selectionDateException();
		CreationCalendar pageEditionCalendrier2 = pageEditionCalendrier.boutonCreerException();
		assertTrue(pageEditionCalendrier2.verifCreationException());
		assertTrue(pageEditionCalendrier2.verifMajException("0","0","0","0"));
		
		//Mise � jour d'une exception
		pageEditionCalendrier2.majException("1","1","1","1");
		Thread.sleep(2000);
		CreationCalendar pageEditionCalendrier3 = pageEditionCalendrier2.boutonMajException();
		assertTrue(pageEditionCalendrier3.verifMajException2("1","1","1","0"));
		
		//Enregistrement d'une exception
		pageEditionCalendrier3.cliquerSauver();
		ListCalendar pageVerifCreationException =  PageFactory.initElements(driver, ListCalendar.class);
		pageVerifCreationException.verifierMessageCreation("Calendrier - Test 1");
		
		//Ouverture de la page d'�dition
		CreationCalendar pageEditionCalendrier4 = pageVerifCreationException.cliquerModifier("Calendrier - Test 1");
		Thread.sleep(2000);
		assertTrue(pageEditionCalendrier4.verifierPageEdition("Calendrier - Test 1"));
		//assertTrue(pageEditionCalendrier4.verifCodeException("9"));
		
		//Annulation
		ListCalendar listeCalendriers2 = pageEditionCalendrier4.cliquerAnnuler();
		Thread.sleep(2000);
		
		//Cr�ation d'un calendrier d�riv�
		CreationCalendar calendrierDerive = listeCalendriers2.trouverCalendrier("Calendrier - Test 1");
		Thread.sleep(2000);
		calendrierDerive.verfiDerive("Calendrier - Test 1");
		assertTrue(calendrierDerive.verifExceptionDerivee("Inherited"));
		assertTrue(calendrierDerive.verifMajException2("1","1","1","0"));
		
		//Annulation
		ListCalendar listeCalendriers3 = calendrierDerive.cliquerAnnuler();
		Thread.sleep(2000);
		
		//Cr�ation d'une copie
		CreationCalendar calendrierCopie = listeCalendriers3.creerCopieCalendrier("Calendrier - Test 1");
		assertTrue(calendrierCopie.verifCreerCopie("Calendrier - Test 1"));
		assertTrue(calendrierCopie.verifExceptionDerivee("Direct"));
		assertTrue(calendrierCopie.verifMajException2("1","1","1","0"));
		
	}
}