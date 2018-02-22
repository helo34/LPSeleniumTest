package libreplanTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreationCalendar extends PageMenu {
	
	
/* STRUCTURE DE LA PAGE
 * 
 * Initialisation du driver et du driver d'attente
 * Boutons et m�thodes g�n�rales
 * M�thodes de v�rification
 * 
 * */

//############################################################################################################
//############## INITIALISATION DU DRIVER ET DU DRIVER D'ATTENTE ############################################
	
	public CreationCalendar(WebDriver driver) {
		super(driver);
	}

	WebDriverWait wait = new WebDriverWait(driver, 10);
	
	//Identification du bouton de cr�ation du calendrier
	WebElement boutonCreer = driver.findElement(By.xpath("//table[@id='"+prefix()+"q4-box']//tr[2]/td[2]"));
	
//############################################################################################################
//############## METHODES ####################################################################################
	
	//########### METHODE 1 : Cr�ation d'un calendrier avec "Enregistrer" ####################################
	public ListCalendar creerCalendrier(String nomCalendrier) {
		
		//Identification du champ "Nom du calendrier" et saisie d'informations
		WebElement champNomCalendrier = driver.findElement(By.id(prefix()+"45"));
		champNomCalendrier.clear();
		champNomCalendrier.sendKeys(nomCalendrier);
		
		//V�rification de la case coch�e
		if ( !driver.findElement(By.id(prefix()+"d5-real")).isSelected() )
		{
		     driver.findElement(By.id(prefix()+"d5-real")).click();
		}
		
		//Enregistrement du calendrier
		WebElement boutonSave = driver.findElement(By.id(prefix()+"z7-box"));
		boutonSave.click();
		return PageFactory.initElements(driver, ListCalendar.class);
	}
	
	//########### METHODE 2 : Cr�ation d'un calendrier avec "Enregistrer et continuer" ########################
	public void creerCalendrier2(String nomCalendrier) {
			
		//Identification du champ "Nom du calendrier" et saisie d'informations
		WebElement champNomCalendrier = driver.findElement(By.id(prefix()+"45"));
		champNomCalendrier.clear();
		champNomCalendrier.sendKeys(nomCalendrier);
			
		//V�rification de la case coch�e
		if ( !driver.findElement(By.id(prefix()+"d5-real")).isSelected() )
		{
		     driver.findElement(By.id(prefix()+"d5-real")).click();
		}
			
		//Enregistrement du calendrier
		WebElement boutonSaveContinue = driver.findElement(By.id(prefix()+"_8-box"));
		boutonSaveContinue.click();
	}
			
	//########### METHODE 3 : Clic sur le bouton "Annuler" ######################################################
	public ListCalendar cliquerAnnuler() {
		
		//Identification du bouton d'annulation
		WebElement boutonAnnuler = driver.findElement(By.id(prefix()+"08-box"));
		
		//Clic et retour sur la page de liste des calendriers
		boutonAnnuler.click();
		return PageFactory.initElements(driver, ListCalendar.class);
	}
	
	//########### METHODE 4 : Clic sur bouton "Save" #############################################################
	public void cliquerSauver() {
		
		//Identification du bouton et clic
		WebElement boutonSave = driver.findElement(By.xpath("//table[@id='"+(prefix()+"z7-box']")));
		boutonSave.click();
	}
	
	//########### METHODE 5 : Clic sur "Cr�ation d'exception" ######################################################
	public CreationCalendar boutonCreerException() {
		
		//Identification du bouton et clic
		WebElement boutonException = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[.='Create exception']")));
		boutonException.click();
		return PageFactory.initElements(driver, CreationCalendar.class);
	}

	//########### METHODE 6 : S�lection d'un type d'exception ######################################################
	public void selectionTypeException () {
		
		//Identification du bouton de s�lection
		WebElement boutonSelection = driver.findElement(By.id(prefix()+"s6-btn"));
		boutonSelection.click();
		
		//Identification de l'option voulue (ici, "cong�")
		WebElement conge = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tr[@id='"+prefix()+"1a']/td[2]")));
		conge.click();
	}
	
	//########### METHODE 7 : S�lection d'un type d'exception variabilis� ###########################################
	public void selectionTypeException2(String nomException) throws Exception {
		
		//Identification du bouton de s�lection
		WebElement boutonSelection = driver.findElement(By.id(prefix()+"s6-btn"));
		boutonSelection.click();
		Thread.sleep(2000);
		
		//Parcours de la liste d'options � s�lectionner et identification de la ligne correspondant � l'exception souhait�e
		List<WebElement> lignes = driver.findElements(By.xpath("//div[@id='"+prefix()+"s6-pp']/table[1]/tbody[1]/tr"));
		int numeroLigne = 1;
		for (WebElement l : lignes) {
			WebElement caseCherchee = l.findElement(By.xpath("./td[2]"));
			if (!caseCherchee.getText().equals(nomException)) {
				numeroLigne = numeroLigne + 1;
			} else { break; }
		}
		
		//Clic sur la case correspondant � l'option souhait��
		WebElement caseClic = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='"+prefix()+"s6-pp']/table[1]/tbody[1]/tr["+numeroLigne+"]/td[2]")));
		caseClic.click();
	}
	
	//########### METHODE 8 : S�lection d'une date de fin d'exception ###############################################
	public void selectionDateException () {
		
		//Identification du bouton ouvrant le calendrier
		WebElement boutonSelection = driver.findElement(By.id(prefix()+"i6-btn"));
		boutonSelection.click();
		
		//Cr�ation d'un tableau avec chaque ligne du calendrier
		WebElement calendrier = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='z-calendar']")));
		List<WebElement> semaine = driver.findElements(By.xpath("//table[@class='z-calendar-calday']/tbody[1]/tr"));
		WebElement jourSouhaite = null;
		
		//Parcours de chaque ligne du calendrier (= semaine)
		for (WebElement s : semaine) {
			List<WebElement> Jour = s.findElements(By.xpath("./td"));
			
			//Parcours de chaque jour de la semaine
			for (WebElement j : Jour) {
				
				//Recherche du jour actuel (qui appara�t surlign� sur le site)
				if (j.getAttribute("class").equals("z-calendar-wkday z-calendar-seld") || 
						j.getAttribute("class").equals("z-calendar-wkend z-calendar-seld")) {
				jourSouhaite = j.findElement(By.xpath("."));
				}
			}
		}
		
		//S�lection du jour actuel
		jourSouhaite.click();
	}
	
	//########### METHODE 9 : Mise � jour d'une exception ###############################################################
	public void majException(String valeur1, String valeur2, String valeur3, String valeur4) {
		
		//Identification des diff�rents champs � modifier
		WebElement effortNormal1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(prefix()+"8a-real")));
		WebElement effortNormal2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(prefix()+"9a-real")));
		WebElement effortSupplementaire1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(prefix()+"ba-real")));
		WebElement effortSupplementaire2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(prefix()+"ca-real")));
		
		//Modification des champs avec les valeurs souhait�es
		effortNormal1.sendKeys(valeur1);
		effortNormal2.sendKeys(valeur2);
		effortSupplementaire1.sendKeys(valeur3);
		effortSupplementaire2.sendKeys(valeur4);
	}
	
	//########### METHODE 10 : Clic sur le bouton "Mise � jour d'une exception" ###########################################
	public CreationCalendar boutonMajException() {
		
		//Identification du bouton et clic, puis mise � jour de la page
		WebElement boutonException = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[.='Update exception']")));
		boutonException.click();
		return PageFactory.initElements(driver, CreationCalendar.class);
	}
	

	//############################################################################################################
	//############## METHODES DE VERIFICATION ####################################################################
	
	//########### METHODE 11 : Message d'erreur si le calendrier est d�j� cr�� ##################################
	public boolean verifDoublon(String nomCalendrier) {
			
		//Initialisation du bool�en et du texte attendu
		boolean verif = false;
		String texteDoublon = nomCalendrier+" already exists";
		
		//Recherche de l'emplacement du message d'erreur
		WebElement Verif = driver.findElement(By.id(prefix()+"nd"));
		
		//V�rification de l'�galit� du texte du message d'erreur avec le texte attendu
		if (texteDoublon.equals(Verif.getText())) {
			verif = true;
		}
		return verif;
	}
		
	//########### METHODE 12 : Message de validation de la cr�ation (quand "Save and Continue") ##################
	public boolean verifCreation(String nomCalendrier) {
			
		//Initialisation du bool�en et du texte attendu
		boolean verif = false;
		String texteVerif = "Base calendar \""+nomCalendrier+"\" saved";
		String texteTitre = "Create Calendar: "+nomCalendrier;
			
		//Recherche de l'emplacement des messages de cr�ation
		WebElement Verif = driver.findElement(By.id(prefix()+"ra"));
		WebElement Verif2 = driver.findElement(By.id(prefix()+"s4-cnt"));
			
		//V�rification de la correspondance entre les diff�rents textes
		if (texteVerif.equals(Verif.getText()) && texteTitre.equals(Verif2.getText())) {
			verif = true;			}
		return verif;
	}
		
	//########### METHODE 13 : V�rification du nom indiqu� dans un �v�nement existant et cr�ation d'une copie ####
	public boolean verifCreerCopie(String nomCalendrier) {
		
		//Initialisation du bool�en et du texte attendu, et recherche des messages
		WebElement champNomCalendrier = driver.findElement(By.id(prefix()+"45"));
		String type = "Root calendar";
		WebElement champTypeCalendrier = driver.findElement(By.id(prefix()+"85"));
		boolean verif = false;
		
		//V�rification de la correspondance entre les diff�rents textes
		if (/*champNomCalendrier.getText().equals(nomCalendrier) && */type.equals(champTypeCalendrier.getText())) {
			verif = true;
			}
		return verif;
	}
	
	//########### METHODE 14 : V�rification de la cr�ation d'un d�riv� #############################################
	public boolean verfiDerive(String nomCalendrier) {
	
		//Initialisation du texte attendu et du bool�en, et recherche du texte � v�rifier
		String Attendu = "Derived of calendar "+nomCalendrier;
		WebElement Verif = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"85")));
		boolean verification = false;
		
		//V�rification de l'�quivalence des deux textes
		if (Attendu.equals(Verif.getText())) {
			verification = true;
		}
		return verification;
	}

	//########### METHODE 15 : V�rification qu'on est sur une page de modification ##################################
	public boolean verifierPageEdition(String nomCalendrier) {
		
		//Initialisation du texte attendu et du bool�en, et recherche du texte � v�rifier
		boolean verification = false;
		String Attendu = "Edit Calendar: "+nomCalendrier;
		WebElement Verif = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"s4-cnt")));
		
		//V�rification de l'�quivalence des deux textes
		if(Attendu.equals(Verif.getText())) {
			verification = true;
		}
		return verification;
	}
		
	
	//########### METHODE 16 : V�rification de l'apparition de la pop-up "S�lectionnez un type d'exception" #########
	public boolean popupTypeException() {
		
		//Identification de la pop-up et initialisation du bool�en
		WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[.='Please, select type of exception']")));
		boolean popupOui = false;
		
		//V�rification de l'affichage du message
		if (popup.isDisplayed()) {
			popupOui = true;
		}
		return popupOui;
	}
	
	//########### METHODE 17 : V�rification de l'apparition de la pop-up "S�lectionnez ue date de fin d'exception" ####
	public boolean popupTypeException2() {
		
		//Identification de la pop-up et initialisation du bool�en
		WebElement popup2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[.='Please, select an End Date for the Exception']")));
		boolean popup2Oui = false;
		
		//V�rification de la pr�sence de la pop-up
		if (popup2.isDisplayed()) {
			popup2Oui = true;
		}
		return popup2Oui;
	}
		
	//########### METHODE 18 : V�rification de la bonne cr�ation d'une exception ######################################
	public boolean verifCreationException() {
		
		//Initialisation du bool�en et identification des messages � afficher en cas de succ�s de la cr�ation
		boolean resultat = false;
		WebElement ligneException = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(prefix()+"fa")));
		WebElement jourException = driver.findElement(By.id(prefix()+"t5-real"));
		
		//V�rification de l'affichage des messages
		if (ligneException.isDisplayed() && jourException.isDisplayed()) {
			resultat = true;
		}		
		return resultat;
	}
	
	//########### METHODE 19 : V�rification de la bonne mise � jour d'une exception (par id) ##############################
	public boolean verifMajException(String valeur1, String valeur2, String valeur3, String valeur4) {
		
		//Initialisation du bool�en et des r�sultats attendus, et identification des messages � v�rifier
		boolean resultat = false;
		String attenduNormal = valeur1+":"+valeur2;
		String attenduSupplementaire = valeur3+":"+valeur4;
		WebElement verifNormal = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(prefix()+"ld")));
		WebElement verifNormal2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(prefix()+"z5")));
		WebElement verifSupplementaire = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(prefix()+"nd")));
		
		//V�rification
		if (attenduNormal.equals(verifNormal.getText()) && attenduNormal.equals(verifNormal2.getText()) && attenduSupplementaire.equals(verifSupplementaire.getText())) {
			resultat = true;
		}
		return resultat;
	}
	
	//########### METHODE 20 : V�rification de la bonne mise � jour d'une exception (par xpath) ############################
	public boolean verifMajException2(String valeur1, String valeur2, String valeur3, String valeur4) {
		
		//Initialisation du bool�en et des r�sultats attendus, et identification des messages � v�rifier
		boolean resultat = false;
		String attenduNormal = valeur1+":"+valeur2;
		String attenduSupplementaire = valeur3+":"+valeur4;
		
		WebElement verifNormal = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//fieldset[@class='z-fieldset']//div[@class='z-listbox-body']//tbody[2]/tr[1]/td[3]/div[1]/span[1]")));
		WebElement verifNormal2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(prefix()+"z5")));
		WebElement verifSupplementaire = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//fieldset[@class='z-fieldset']//div[@class='z-listbox-body']//tbody[2]/tr[1]/td[4]/div[1]/span[1]")));
		
		//V�rification
		if (attenduNormal.equals(verifNormal.getText()) && attenduNormal.equals(verifNormal2.getText()) && attenduSupplementaire.equals(verifSupplementaire.getText())) {
			resultat = true;
		}
		return resultat;
	}

	//########### METHODE 21 : V�rification du code de l'exception #########################################################
	public boolean verifCodeException(String numException) {
		
		//Initialisation du bool�en et des r�sultats attendus, et identification des messages � v�rifier
		boolean resultat = false;
		WebElement codeCalendrier = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(prefix()+"c5")));
		String debutCode = codeCalendrier.getText();
		String codeAttendu = debutCode+"-000"+numException;
		WebElement codeException = driver.findElement(By.id(prefix()+"bb"));
		String valeur = codeException.getAttribute("value");
		
		//V�rification
		if (codeAttendu.equals(valeur)) {
			resultat = true;
		}
		return resultat;
	}
	
	//########### METHODE 22 : V�rification de l'h�ritage de l'exception #########################################################
	public boolean verifExceptionDerivee(String origine) {
				
		//Choix du suffixe correspondant au type d'h�ritage  de l'exception �tudi�e
		String suffixe = "a";
		if (origine.equals("Inherited")) {
			suffixe = "og";
		} else if ((origine.equals("Direct"))) {
			suffixe = "4c";
		}
		
		//Initialisation du bool�en, des messages attendus et localisation des informations � v�rifier
		boolean resultat = false;
		WebElement champOrigine = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(prefix()+suffixe)));
		String valeurOrigine = champOrigine.getText();
		String valeurAttendue = origine;
		
		//V�rification
		if (valeurAttendue.equals(valeurOrigine)) {
			resultat = true;
		}
		return resultat;
	}
}

