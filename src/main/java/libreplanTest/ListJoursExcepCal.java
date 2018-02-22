package libreplanTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ListJoursExcepCal extends PageMenu {

	public ListJoursExcepCal(WebDriver driver) {
		super(driver);
	}

/* STRUCTURE DE LA PAGE
 * 
 * Initialisation du driver et du driver d'attente
 * Boutons et m�thodes g�n�rales
 * M�thodes de v�rification
 * 
 * */

//############################################################################################################
//############## INITIALISATION DU DRIVER ET DU DRIVER D'ATTENTE ############################################

	WebDriverWait wait = new WebDriverWait(driver, 10);

//############################################################################################################
//############## METHODES ####################################################################################

	//Bouton "Cr�er"
	public CreationJourExcep cliquerCreer() {
		WebElement boutonCreer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='"+prefix()+"t4-box']/tbody/tr[2]/td[2]")));
		boutonCreer.click();
		return PageFactory.initElements(driver, CreationJourExcep.class);
	}
	
	//Parcours de la liste des jours exceptionnels cr��s
	public boolean rechercheJour (String nom) {
		WebElement colonneName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='"+prefix()+"n4-cave']")));
		
		//Identification des lignes du tableau et initialisation du bool�en
		List<WebElement> lignesTableau = driver.findElements(By.xpath("div[@id='k4-body']/table[1]/tbody[2]/tr"));
		boolean resultat = false;
		
		//Parcours du tableau et recherche du nom du jour
		for (WebElement ligne : lignesTableau) {
			WebElement caseNom = ligne.findElement(By.xpath("./td[1]/div[1]/span[1]"));
			if (caseNom.getText().equals(nom)) {
				resultat = true;
			}
		}
		return resultat;
	}

//############################################################################################################
//############## METHODES DE VERIFICATION ####################################################################
	
	//V�rifier la pr�sence des diff�rentes colonnes et du bouton de cr�ation
	public boolean verifierColonne() {
		
		//Identification des colonnes dont on veut v�rifier la pr�sence et initialisation du bool�en
		WebElement colonneName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='"+prefix()+"n4-cave']")));
		WebElement colonneColor = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='"+prefix()+"o4-cave']")));
		WebElement colonneOverallLocated = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='"+prefix()+"p4-cave']")));
		WebElement colonneStandard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='"+prefix()+"q4-cave']")));
		WebElement colonneOvertime = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='"+prefix()+"r4-cave']")));
		WebElement colonneOperations = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='"+prefix()+"s4-cave']")));
		WebElement boutonCreer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='"+prefix()+"t4-box']/tbody/tr[2]/td[2]")));
			
		boolean verif = false;
			
		//V�rification de la pr�sence des colonnes
		if (colonneOperations.isDisplayed() && colonneName.isDisplayed() && colonneOverallLocated.isDisplayed()
				 && colonneColor.isDisplayed() && boutonCreer.isDisplayed() && colonneStandard.isDisplayed() && colonneOvertime.isDisplayed()) {
			verif = true;
		}
		return verif;
	}
	
	//V�rification des donn�es des jours exceptionnels cr��s
	public boolean rechercheDonneesJour (String nom, String couleur, String valeur1, String valeur2, String valeur3, String valeur4, String suraffecte) {
		WebElement colonneName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='"+prefix()+"n4-cave']")));
		
		//Identification des lignes du tableau et initialisation du bool�en et du compteur de lignes
		//List<WebElement> lignesTableau = driver.findElements(By.xpath("//tr[@class='clickable-rows z-row' or @class='clickable-rows z-row z-grid-odd']"));
		List<WebElement> lignesTableau = driver.findElements(By.xpath("//div[@id='"+prefix()+"k4-body']/table[1]/tbody[2]/tr"));
		boolean resultat = false;
		int numeroLigne = 1;
		
		//Parcours du tableau et incr�mentation du num�ro de ligne jusqu'� trouver celle qu'on cherche
		for (WebElement ligne : lignesTableau) {
			WebElement caseNom = ligne.findElement(By.xpath("./td[1]/div[1]"));
			if (!caseNom.getText().equals(nom)) {
				numeroLigne = numeroLigne+1;
			} else {
				break;
			}
		}
		
		//Identification des donn�es qu'on souhaite v�rifier
		WebElement ligneTableauCherchee = driver.findElement(By.xpath("//div[@id='"+prefix()+"k4-body']/table[1]/tbody[2]/tr["+numeroLigne+"]"));
		WebElement nomCherche = ligneTableauCherchee.findElement(By.xpath("./td[1]/div[1]/span[1]"));
		WebElement couleurCherchee = ligneTableauCherchee.findElement(By.xpath("./td[2]/div[1]/span[1]"));
		WebElement standardCherche = ligneTableauCherchee.findElement(By.xpath("./td[4]/div[1]/span[1]"));
		WebElement overtimeCherche = ligneTableauCherchee.findElement(By.xpath("./td[5]/div[1]/span[1]"));
		WebElement suraffecteCherche = ligneTableauCherchee.findElement(By.xpath("./td[3]/div[1]/span[1]"));
		
		//V�rification
		if (nomCherche.getText().equals(nom)  && couleurCherchee.getText().equals(couleur) && standardCherche.getText().equals(valeur1+":"+valeur2)
				&& overtimeCherche.getText().equals(valeur3+":"+valeur4) && suraffecteCherche.getText().equals(suraffecte)) {
			resultat = true;
		}
		return resultat;
	}
		
	//V�rification du message de cr�ation
	public boolean messageCreationJour(String nom) {
		
		//Identification du message de cr�ation
		WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Calendar Exception Day \""+nom+"\" saved']")));
		boolean verif = false;
		
		//V�rification de sa pr�sence
		if (message.isDisplayed()) {
			verif = true;
		}
		return verif;
	}
}
