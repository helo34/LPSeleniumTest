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

public class ListWorkers extends PageMenu{

	public ListWorkers(WebDriver driver) {
		super(driver);
		
	}
WebDriverWait wait = new WebDriverWait(driver,10);
@FindBy(how=How.XPATH,using="//td[.='Create']")
public WebElement boutonCreate;


public CreateWorker clickCreate(){
	this.boutonCreate.click();
	return PageFactory.initElements(driver, CreateWorker.class);
}
public boolean verifTableau(){
	//Verification de la présence des éléments du tableau
	WebElement colone1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[.='Surname']")));
	WebElement colone2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[.='First name']")));
	WebElement colone3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[.='ID']")));
	WebElement colone4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[.='Code']")));
	WebElement colone5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[.='Queue-based']")));
	WebElement colone6 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[.='Operations']")));
	//initialisation du boolean
	boolean resultat = false;
	
	if( colone1.isDisplayed() && colone2.isDisplayed() && colone3.isDisplayed() && colone4.isDisplayed() && colone5.isDisplayed() && colone6.isDisplayed() ){
		resultat = true;
	}
	
	return resultat;
}

public boolean verifChamp(){
	//verification de la présence de tout les chaps de la page
	WebElement champFilterBy = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"q4-real")));
	WebElement champPersonalDetails = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"d5")));
	//initialisation du boolean
	boolean resultat1 = false;
	if (champFilterBy.isDisplayed() && champPersonalDetails.isDisplayed()){
		resultat1=true;
	}
	return resultat1;

}
public boolean verifBouton(){
	//verification de la présence de tout les boutons de la page
	WebElement boutonLoupe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"q4-btn")));
	WebElement boutonMoreOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"f5-cnt")));
	WebElement boutonFilter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[.='Filter']")));
	WebElement boutonCreateVerif = wait.until(ExpectedConditions.visibilityOf(boutonCreate));
	//initialisation du boolean
	boolean resultat2=false;
	if ( boutonLoupe.isDisplayed() && boutonMoreOption.isDisplayed() && boutonFilter.isDisplayed() && boutonCreateVerif.isDisplayed()){
		resultat2=true;
	}
	return resultat2;
}
public boolean verifMessage(String message){
	//verification de la présence du message worker save
	WebElement message1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"o9")));
	//initialisation du boolean
boolean resultat3=false;
if (message1.getText().equals(message)){
		resultat3=true;
}
return resultat3;
}
public boolean moreOptions(){
WebElement boutonMoreOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"f5-cnt")));
boutonMoreOption.click();
WebElement champFrom = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"i5-real")));
WebElement champTo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"k5-real")));
WebElement menuType2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"m5")));
//initialisation du boolean
boolean resultat4=false;
if(champFrom.isDisplayed()&&champTo.isDisplayed()&&menuType2.isDisplayed()){
	resultat4=true;
}
return resultat4;
}

public void navListWorker() throws InterruptedException{
	WebElement buttonNext = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='z-paging-next']")));
	buttonNext.click();
	Thread.sleep(2000);
	WebElement buttonPrev = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='z-paging-prev']")));
	buttonPrev.click();
	Thread.sleep(2000);
	WebElement buttonLast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='z-paging-last']")));
	buttonLast.click();
	Thread.sleep(2000);
	WebElement buttonFirst = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='z-paging-first']")));
	buttonFirst.click();
}
public boolean verifCreaUtilisateur(String idChercher, String firstNameChercher, String surnameChercher){
	//List<WebElement> surname = driver.findElements(By.xpath("//tbody[@id='"+prefix()+"lf']/tr/td[1]/div/span"));

	List<WebElement> lignes= driver.findElements(By.xpath("//tr[@class='clickable-rows z-row' or @class='clickable-rows z-row z-grid-odd']"));
	//List<WebElement> id = driver.findElements(By.xpath("//tbody[@id='"+prefix()+"lf']/tr/td[3]/div/span"));
	
	
	//recherche de l'element selectionner dans la liste titi et determination de la ligne ou il se trouve
			int i = 0;
			int trouver = -1;
			for(WebElement t : lignes){
				WebElement cases = t.findElement(By.xpath("./td[3]/div"));
				String test = cases.getText();
				if(test.equals(idChercher)){
				trouver = i;
				}	
				i++;
			}
			
			
			//recherche du fisrtname associer a l'id trouvée
			String firstNameTrouve = lignes.get(trouver).findElement(By.xpath("./td[2]/div")).getText();
			
			
			//recherche du Surname associer a l'id trouvée
			
			String surnameTrouve = lignes.get(trouver).findElement(By.xpath("./td[1]/div")).getText();
	
			//initialisation du boolean
boolean resultat5 = false;
if( surnameTrouve.equals(surnameChercher) && firstNameTrouve.equals(firstNameChercher)){
	resultat5 = true;


}
return resultat5;
}
}


