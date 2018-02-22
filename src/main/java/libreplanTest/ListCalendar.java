package libreplanTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ListCalendar extends PageMenu {

/* STRUCTURE DE LA PAGE
 * 
 * Initialisation du driver et du driver d'attente
 * Boutons et m�thodes g�n�rales
 * M�thodes de v�rification
 * 
 * */

//############################################################################################################
//############## INITIALISATION DU DRIVER ET DU DRIVER D'ATTENTE ############################################

	public ListCalendar(WebDriver driver) {
		super(driver);
	}
	
	WebDriverWait wait = new WebDriverWait(driver, 10);
		
//############################################################################################################
//############## METHODES ####################################################################################
	
	//Acc�s � la page de cr�ation de calendrier
	public CreationCalendar accesCreationCalendrier() throws Exception {
		
		//Identification du bouton
		WebElement boutonCreer = driver.findElement(By.xpath("//table[@id='"+prefix()+"q4-box']//tr[2]/td[2]"));
		
		//Clic et mise en place d'une page "Cr�ation de calendrier"
		boutonCreer.click();
		Thread.sleep(2000);
		return PageFactory.initElements(driver, CreationCalendar.class);
	}
	
	//Masquer ou d�voiler le(s) sous-calendrier(s)
	public boolean cacherSousCalendrier(String nomCalendrier) {
		//On cherche dans le tableau la ligne correspondant au calendrier voulu 
		List<WebElement> lignesTableau = driver.findElements(By.xpath("//tbody[@id='"+prefix()+"k4-rows']/tr"));
		Integer numeroLigne = 1;
		for(WebElement ligne : lignesTableau) {
			WebElement caseNomCalendrier = ligne.findElement(By.xpath("./td/div/span[2]"));
			//On clique sur le [-] qui permet de fermer le menu d�roulant
			if(!nomCalendrier.equals(caseNomCalendrier.getText())) {
				numeroLigne = numeroLigne + 1;
			} else {
				WebElement baliseDerouler = ligne.findElement(By.xpath("//tbody[@id='"+prefix()+"k4-rows']/tr["+numeroLigne+"]/td/div/span[1]"));
				numeroLigne = numeroLigne + 1;
				baliseDerouler.click();
				break;
			}
		}
		
		//On v�rifie que le menu s'est bien ferm�
		WebElement caseCherchee = driver.findElement(By.xpath("//tbody[@id='"+prefix()+"k4-rows']/tr["+numeroLigne+"]//span[3]"));
		boolean visible = true;
		if (!caseCherchee.isDisplayed()) {
			visible = false;
		}
		return visible;
	}
	
	//Cr�ation d'une copie de calendrier existant
	public CreationCalendar creerCopieCalendrier(String nomCalendrier) {
		WebElement colonneOperations = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='"+prefix()+"p4-cave']")));

		//Identification du tableau et initialisation du num�ro de ligne
		List<WebElement> lignesTableau = driver.findElements(By.xpath("//tbody[@id='"+prefix()+"k4-rows']/tr"));
		Integer numeroLigne = 1;
		
		//Parcours du tableau
		for(WebElement ligne : lignesTableau) {
			//Identification de la deuxi�me case
			WebElement caseNomCalendrier = ligne.findElement(By.xpath("./td/div/span[2]"));
			//Recherche du bon calendrier
			if(!nomCalendrier.equals(caseNomCalendrier.getText())) {
				numeroLigne = numeroLigne + 1;
			} else {
				WebElement boutonCopier = ligne.findElement(By.xpath("//tbody[@id='"+prefix()+"k4-rows']/tr["+numeroLigne+"]/td[4]/div/span[2]"));
				boutonCopier.click();
				break;
				}
			}
		return PageFactory.initElements(driver, CreationCalendar.class);
	}
		
	//Ouvrir la cr�ation d'un calendrier d�riv�
	public CreationCalendar trouverCalendrier(String nomCalendrier) {
		
		//Identification des lignes du tableau o� chercher le calendrier, et initialisation de la ligne
		List<WebElement> lignesTableau = driver.findElements(By.xpath("//tbody[@id='"+prefix()+"k4-rows']/tr"));
		Integer numeroLigne = 1;
		
		//Parcours du tableau et recherche de la ligne correspondant au calendrier cherch�, puis clic sur le bouton de cr�ation d'un d�riv�
		for(WebElement ligne : lignesTableau) {
			WebElement caseNomCalendrier = ligne.findElement(By.xpath("./td/div/span[2]"));
			if(!nomCalendrier.equals(caseNomCalendrier.getText())) {
				numeroLigne = numeroLigne + 1;
			} else {
				WebElement caseAjoutCalendrierDerive = ligne.findElement(By.xpath("//tbody[@id='"+prefix()+"k4-rows']/tr["+numeroLigne+"]/td[4]//td[@class='z-button-cm']"));
				caseAjoutCalendrierDerive.click();
				break;
			}
		}
		return PageFactory.initElements(driver, CreationCalendar.class);
	}
		
	//Clic sur le bouton "Modifier" d'un calendrier d�fini
	public CreationCalendar cliquerModifier(String nomCalendrier) throws Exception {
		WebElement colonneOperations = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='"+prefix()+"p4-cave']")));

		//Identification des lignes du tableau o� chercher le calendrier, et initialisation de la ligne
		List<WebElement> lignesTableau = driver.findElements(By.xpath("//tbody[@id='"+prefix()+"k4-rows']/tr"));
		Integer numeroLigne = 1;
		
		//Parcours du tableau et recherche de la ligne correspondant au calendrier cherch�
		for(WebElement ligne : lignesTableau) {
			WebElement caseNomCalendrier = ligne.findElement(By.xpath("./td/div/span[2]"));
			if(!nomCalendrier.equals(caseNomCalendrier.getText())) {
				numeroLigne = numeroLigne + 1;
				} else {
				break;
				}
			}
		Thread.sleep(5000);
		
		//Clic sur le bouton "Modifier" identifi� et chargement d'une nouvelle page 
		WebElement boutonModifier = driver.findElement(By.xpath("//tbody[@id='"+prefix()+"k4-rows']/tr["+numeroLigne+"]/td[4]/div/span[3]"));
		boutonModifier.click();
		return PageFactory.initElements(driver, CreationCalendar.class);
	}
	
//############################################################################################################
//############## METHODES DE VERIFICATION ####################################################################
	//V�rifier la pr�sence des diff�rentes colonnes et du bouton de cr�ation
	public boolean verifierColonne() {
	
		//Identification des colonnes dont on veut v�rifier la pr�sence, et du bouton de cr�ation
		WebElement colonneName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='"+prefix()+"m4-cave']")));
		WebElement colonneInheritsFrom = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='"+prefix()+"n4-cave']")));
		WebElement colonneInheritsUp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='"+prefix()+"o4-cave']")));
		WebElement boutonCreer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='"+prefix()+"q4-box']/tbody/tr[2]/td[2]")));
		WebElement colonneOperations = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='"+prefix()+"p4-cave']")));
		
		//Initialisation du bool�en et v�rification
		boolean verif = false;
		if (colonneOperations.isDisplayed() && colonneName.isDisplayed() && colonneInheritsFrom.isDisplayed()
				 && colonneInheritsUp.isDisplayed() && boutonCreer.isDisplayed()) {
			verif = true;
		}
		return verif;
	}
	
	//V�rification de l'existence d'un sous-calendrier
	public boolean sousCalendrier(String nomCalendrier) {
			
		//Identification des �l�ments dont on veut v�rifier la pr�sence
		WebElement caseSousCalendrier = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(prefix()+"zd")));
		WebElement caseCherchee = driver.findElement(By.id(prefix()+"_e"));
		String nomSousCalendrier = nomCalendrier;
		
		//Initialisation du bool�en et v�rification
		boolean visible = false;
		if (caseCherchee.getText().equals(nomSousCalendrier)) {
			visible = true;
		}
		return visible;
	}
	
	//V�rification de la bonne cr�ation d'un calendrier
	public boolean verifierMessageCreation(String nomCalendrier) {
		
		WebElement colonneOperations = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='"+prefix()+"p4-cave']")));
		
		//Initialisation du bool�en, du message attendu et de l'�l�ment � v�rifier
		boolean verif = false;
		String texteVerif = "Base calendar \""+nomCalendrier+"\" saved";
		WebElement Verif = driver.findElement(By.id(prefix()+"5f"));
		
		//V�rification
		if (texteVerif.equals(Verif.getText())) {
			verif = true;
		}
		return verif;
	}
	
	//V�rification de la pr�sence d'un calendrier
	public boolean verifierPresenceCalendrier(String nomCalendrier)  {
		
		//Identification des lignes du tableau o� chercher le calendrier, et initialisation du bool�en
		List<WebElement> lignesTableau = driver.findElements(By.xpath("//tbody[@id='"+prefix()+"k4-rows']/tr"));
		boolean presence = false;
		
		//Parcours du tableau et recherche du nom du calendrier dans la premi�re colonne de chaque ligne
		for(WebElement ligne : lignesTableau) {
			WebElement caseNomCalendrier = ligne.findElement(By.xpath("./td/div/span[2]"));
			if(nomCalendrier.equals(caseNomCalendrier.getText())) {
				presence= true;
			}
		}
		return presence;
	}
}