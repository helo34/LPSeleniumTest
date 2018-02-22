package libreplanTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreationJourExcep extends PageMenu {

/* STRUCTURE DE LA PAGE
 * 
 * Initialisation du driver et du driver d'attente
 * Boutons et méthodes générales
 * Méthodes de vérification
 * 
 * */

//############################################################################################################
//############## INITIALISATION DU DRIVER ET DU DRIVER D'ATTENTE ############################################
	
	public CreationJourExcep(WebDriver driver) {
		super(driver);
	}

	WebDriverWait wait = new WebDriverWait(driver, 10);

//############################################################################################################
//############## METHODES ####################################################################################
		
	//Remplissage du champ "Nom" pour la création d'un jour exceptionnel
		public void remplirNom(String nom) {
			
			//Identification du champ, suppression de son contenu et remplacement par le texte souhaité
			WebElement champNom = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='"+prefix()+"c5']")));
			champNom.clear();
			champNom.sendKeys(nom);
		}
				
	//Cliquer sur Annuler
		public ListJoursExcepCal cliquerAnnuler() {
			
			//Identification du bouton, clic et retour sur la page de liste des exceptions
			WebElement boutonCancel = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='"+prefix()+"z5-box']/tbody/tr[2]/td[2]")));
			boutonCancel.click();
			return PageFactory.initElements(driver, ListJoursExcepCal.class);
		}
			
	//Choix d'une couleur
		public boolean choixCouleur(String couleur) {
			
			//Identification du menu déroulant relatif à la couleur
			WebElement champCouleur = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='"+prefix()+"g5']")));
			Select menuCouleur = new Select(champCouleur);
			
			//Choix de la couleur souhaitée
			menuCouleur.selectByVisibleText(couleur);
			
			//Vérification de la couleur choisie
			String styleFortAttendu = "";
			String styleAttenueAttendu = "";
			boolean resultat = false;
			if (couleur.equals("red (default)")) {
				styleFortAttendu = "background-color: rgb(255, 51, 51);";
				styleAttenueAttendu = "background-color: rgb(255, 153, 153);";
			} else if (couleur.equals("green")) {
				styleFortAttendu = "background-color: rgb(46, 230, 46);";
				styleAttenueAttendu = "background-color: rgb(138, 230, 138);";
			} else if (couleur.equals("blue")) {
				styleFortAttendu = "background-color: rgb(51, 51, 255);";
				styleAttenueAttendu = "background-color: rgb(153, 153, 255);";
			} else if (couleur.equals("cyan")) {
				styleFortAttendu = "background-color: rgb(51, 255, 255);";
				styleAttenueAttendu = "background-color: rgb(153, 255, 255);";
			} else if (couleur.equals("magenta")) {
				styleFortAttendu = "background-color: rgb(255, 51, 255);";
				styleAttenueAttendu = "background-color: rgb(255, 153, 255);";
			} else if (couleur.equals("yellow")) {
				styleFortAttendu = "background-color: rgb(230, 230, 46);";
				styleAttenueAttendu = "background-color: rgb(230, 230, 161);";
			} else if (couleur.equals("black")) {
				styleFortAttendu = "background-color: rgb(51, 51, 51);";
				styleAttenueAttendu = "background-color: rgb(153, 153, 153);";
			} else if (couleur.equals("orange")) {
					styleFortAttendu = "background-color: rgb(255, 183, 51);";
					styleAttenueAttendu = "background-color: rgb(255, 219, 153);";
			} else if (couleur.equals("purple")) {
				styleFortAttendu = "background-color: rgb(128, 26, 128);";
				styleAttenueAttendu = "background-color: rgb(179, 142, 179);";
			} 
				
			WebElement couleurForte = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='"+prefix()+"h5']")));
			WebElement couleurAttenuee = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='"+prefix()+"j5']")));
			
			if (couleurForte.getAttribute("style").equals(styleFortAttendu) && couleurAttenuee.getAttribute("style").equals(styleAttenueAttendu)) {
			resultat = true;
			}
			return resultat;
		}
				
	//Saisie d'une valeur dans le premier champ de Standard Effort
		public void saisieStandard1 (String valeur) throws Exception {
			
			//Identification du champ à remplir, suppression du texte éventuel et saisie des valeurs souhaitées
			WebElement champEffort1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='"+prefix()+"o5-real']")));
			champEffort1.clear();
			Thread.sleep(1000);
			champEffort1.sendKeys(valeur);
		}
			
	//Saisie d'une valeur dans le deuxième champ de Standard Effort
		public void saisieStandard2 (String valeur) throws InterruptedException {
			
			//Identification du champ à remplir, suppression du texte éventuel et saisie des valeurs souhaitées
			WebElement champEffort2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='"+prefix()+"p5-real']")));
			champEffort2.clear();
			champEffort2.sendKeys(valeur);
		}
				
	//Saisie d'une valeur dans le premier champ de Extra Effort
		public void saisieExtra1 (String valeur) throws InterruptedException {
			
			//Identification du champ à remplir, suppression du texte éventuel et saisie des valeurs souhaitées
			WebElement champEffort3 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='"+prefix()+"u5-real']")));
			champEffort3.clear();
			champEffort3.sendKeys(valeur);
		}
			
	//Saisie d'une valeur dans le deuxième champ de Extra Effort
		public void saisieExtra2 (String valeur) throws InterruptedException {
			
			//Identification du champ à remplir, suppression du texte éventuel et saisie des valeurs souhaitées
			WebElement champEffort4 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='"+prefix()+"v5-real']")));
			champEffort4.clear();
			champEffort4.sendKeys(valeur);
		}

	//Cliquer sur Sauver
		public void cliquerSauver() {
			
			//Identification du bouton et clic
			WebElement boutonSave = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='"+prefix()+"x5-box']/tbody/tr[2]/td[2]")));
			boutonSave.click();
		}
				
	//Cliquer sur Sauver et continuer
		public void cliquerSauverContinuer() {
			
			//Identification du bouton et clic
			WebElement boutonSaveContinue = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='"+prefix()+"y5-box']/tbody/tr[2]/td[2]")));
			boutonSaveContinue.click();
		}
		
//############################################################################################################
//############## METHODES DE VERIFICATION ####################################################################

	//Vérification de la page (format)
		public boolean verifFormatPage() {
			boolean resultat = false;
			
			//Identification des colonnes dont on veut vérifier la présence
			WebElement colonneName = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='"+prefix()+"b5']")));
			WebElement colonneColor = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='"+prefix()+"e5']")));
			WebElement colonneCode = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='"+prefix()+"65']")));
			WebElement colonneStandard = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='"+prefix()+"m5']")));
			WebElement colonneEffort = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='"+prefix()+"r5']")));
			WebElement boutonSave = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='"+prefix()+"x5-box']/tbody/tr[2]/td[2]")));
			WebElement boutonSaveContinue = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='"+prefix()+"y5-box']/tbody/tr[2]/td[2]")));
			WebElement boutonCancel = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='"+prefix()+"z5-box']/tbody/tr[2]/td[2]")));
			
			//Vérification de la présence desdites colonnes
			if (colonneCode.isDisplayed() && colonneName.isDisplayed() && colonneEffort.isDisplayed()
					 && colonneColor.isDisplayed() && boutonCancel.isDisplayed() && colonneStandard.isDisplayed() && boutonSave.isDisplayed() && boutonSaveContinue.isDisplayed()) {
				resultat = true;
			}
			
			return resultat;
		}
	
	//Vérification de la page (contenu des champs)
		public boolean verifContenuPage(String nom, String couleur, String valeur1, String valeur2, String valeur3, String valeur4) {
			
			//Initialisation du booléen, identification des champs à vérifer 
			boolean resultat = false;
			WebElement champCode = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='"+prefix()+"85']")));
			WebElement champNom = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='"+prefix()+"c5']")));
			String valeurNom = champNom.getAttribute("value");
			WebElement champCouleur = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='"+prefix()+"g5']/option[@selected='selected']")));
			String valeurCouleur = champCouleur.getText();
			WebElement champEffort1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='"+prefix()+"o5-real']")));
			String valeurEffort1 = champEffort1.getAttribute("value");
			WebElement champEffort2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='"+prefix()+"p5-real']")));
			String valeurEffort2 = champEffort2.getAttribute("value");
			WebElement champEffort3 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='"+prefix()+"u5-real']")));
			String valeurEffort3 = champEffort3.getAttribute("value");
			WebElement champEffort4 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='"+prefix()+"v5-real']")));
			String valeurEffort4 = champEffort4.getAttribute("value");
			
			//La case "Generate code" est-elle cochée ? Si oui, texte grisé
			String verifCochee1 = "non";
			if (champCode.getAttribute("class").equals("z-textbox z-textbox-disd z-textbox-text-disd")) {
				verifCochee1 = "oui";
			}
			
			//La case "Infinitely Over Assignable" est-elle cochée ? Si non, texte disponible
			String verifCochee2 = "oui";
			if (champEffort4.getAttribute("class").equals("z-spinner-inp")) {
				verifCochee2 = "non";
			}
			
			//Vérification de l'équivalence entre données réelles et données attendues
			if (valeurNom.equals(nom) && valeurCouleur.equals(couleur) && valeurEffort1.equals(valeur1) && valeurEffort2.equals(valeur2)
					 && valeurEffort3.equals(valeur3) && valeurEffort4.equals(valeur4) && verifCochee1.equals("oui") && verifCochee2.equals("non")) {
				resultat = true;
			}
			
			return resultat;
		}
		
	
	//Message d'erreur lors de la mauvaise saisie du premier champ
		public boolean erreurStandard1() {
			
			//Initialisation du booléen, identification des champs à vérifer 
			boolean verif = false;
			WebElement messageErreur = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[.='Out of range (>= 0).']")));
			
			//Vérification
			if (messageErreur.isDisplayed()) {
				verif = true;
			}
			return verif;
		}	
	
	//Message d'erreur lors de la mauvaise saisie du premier champ
		public boolean erreurStandard2() {
			
			//Initialisation du booléen, identification des champs à vérifer 
			boolean verif = false;
			WebElement messageErreur = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[.='Out of range (0 ~ 59).']")));
			
			
			//Vérification
			if (messageErreur.isDisplayed()) {
				verif = true;
			}
			return verif;
		}

}