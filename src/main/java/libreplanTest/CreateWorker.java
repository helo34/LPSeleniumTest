package libreplanTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateWorker extends PageMenu{

	public CreateWorker(WebDriver driver) {
		super(driver);
		
	}
	WebDriverWait wait = new WebDriverWait(driver,10);
	@FindBy(how=How.XPATH,using="//td[.='Save']")
	public WebElement bouttonSAVE;
	public boolean verifBlock1(){
		//verification de la présence de tout les élements du block1
		WebElement champCode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"k6")));
		WebElement chkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"l6-real")));
		WebElement champFirstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"o6")));
		WebElement champLastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"u6")));
		WebElement champID = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"x6")));
		WebElement menuType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"17")));
		//Determination d'un liste d'elements  selectionner par default du menu deroulant
		Select select = new Select(menuType);
		List<WebElement> toto = select.getAllSelectedOptions();
		//initialisation du boolean
		boolean option = false;
		//Determination de la selection de l'element par default "normal resource"
		for(WebElement e:toto){
		if(e.getText().equals("Normal resource")){
			option = true;
		}
		}
		//verification  que les champs sont vides
		//initialisation du boolean
		boolean champVide = false;
		if( champFirstName.getText().equals("") && champLastName.getText().equals("")  && champID.getText().equals("") ){
			champVide= true;
		}
		//verification que la checkbox est bien cochées
		//initialisation du boolean
		boolean check = false;
		if (chkbox.isSelected()){
			check = true;
		}
		//initialisation du boolean
		boolean resultat1 = false;
		//Verification que tout les boolean sont vrai
		if (champCode.isDisplayed() && champFirstName.isDisplayed() && champLastName.isDisplayed() && champID.isDisplayed() && menuType.isDisplayed() && option==true && champVide == true && check == true){
			resultat1=true;
		}
		return resultat1;

	}
	//vérification du block2 de la page de create worker
	public boolean verifCheckBoxDefault(){
		//verification du radio-bouton cocher par default
	
		//création de deux liste permetant de recuperer les enfant de l'element span contenant tout les couple de radio bouton
		List<WebElement> boutonRadio = driver.findElements(By.xpath("//span[@id='"+prefix()+"77']/span/input"));
		List<WebElement> labelBoutonRadio = driver.findElements(By.xpath("//span[@id='"+prefix()+"77']/span/label"));
		//recherche de l'element selectionner dans la liste titi et determination de la ligne ou il se trouve
		int i = 0;
		int trouve = -1;
		for(WebElement t : boutonRadio){
			if(t.isSelected()){
				trouve = i;
			}
			i++;
		}
		//element selectionner trouver récupereration
		String texteElementSelectionne = labelBoutonRadio.get(trouve).getText();
		//initialisation du boolean
		boolean resultat2 = false;
		if( texteElementSelectionne.equals("Not bound")){
			resultat2 = true;	
		}
		return resultat2;
	}	
//#####################################################################################################################################
	//partie methode introduction d'élement dans les champs.
	
	public void block1(String fName, String lName, String ID){
		WebElement champFirstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"o6")));
		WebElement champLastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"u6")));
		WebElement champID = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"x6")));
			
		
		champFirstName.sendKeys(fName);
		champLastName.sendKeys(lName);
		champID.sendKeys(ID);
	}
	public void block2(String username,String password,String eMail){
		WebElement chkbox2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"kf-real")));
		chkbox2.click();
		WebElement champUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"i8")));
		WebElement champPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"l8")));
		WebElement champRePassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"o8")));
		WebElement champEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"r8")));
		champUsername.sendKeys(username);
		champPassword.sendKeys(password);
		champRePassword.sendKeys(password);
		champEmail.sendKeys(eMail);
		WebElement boutonSave = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[.='Save']")));
		boutonSave.click();
	}
	
	//#####################################################################################################################################
		//partie methode CT no passant.	
	public boolean verifMessageErreur() throws InterruptedException{
		WebElement boutonSave = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[.='Save']")));
		boutonSave.click();
		WebElement bulleMessErreur = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='z-errbox z-popup']/div/div/div/div/div/div/div[.='This field may not be empty or contain only spaces.']")));
		//initialisation du boolean
		boolean resultat3 = false;
		if (bulleMessErreur.isDisplayed()){
			resultat3 = true;
		}
		WebElement boutonHelp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"g3")));
		Actions action1 = new Actions(driver);
		action1.clickAndHold(bulleMessErreur).moveToElement(boutonHelp).release(boutonHelp).build().perform();
		Thread.sleep(2000);
		WebElement  pouet = bulleMessErreur.findElement(By.xpath("./../.."));
		String attributPouet = pouet.getAttribute("class");
		//initialisation du boolean
		boolean resultat4 = false;
		if( attributPouet.equals("z-errbox-left z-arrow-ld")){
			resultat4 = true;
		}
		WebElement boutonWorker = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"c3")));
		Actions action2 = new Actions(driver);
		action2.clickAndHold(bulleMessErreur).moveToElement(boutonWorker).release(boutonWorker).build().perform();
		Thread.sleep(2000);
		WebElement  pouet1 = bulleMessErreur.findElement(By.xpath("./../.."));
		String attributPouet1 = pouet1.getAttribute("class");
		//initialisation du boolean
		boolean resultat5 = false;
		if( attributPouet1.equals("z-errbox-left z-arrow-d")){
			resultat5 = true;
		}
		WebElement boutonPersonalData = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"36-hm")));
		Actions action3 = new Actions(driver);
		action3.clickAndHold(bulleMessErreur).moveToElement(boutonPersonalData).release(boutonPersonalData).build().perform();
		Thread.sleep(2000);
		WebElement  pouet2 = bulleMessErreur.findElement(By.xpath("./../.."));
		String attributPouet2 = pouet2.getAttribute("class");
		//initialisation du boolean
		boolean resultat6 = false;
		if( attributPouet2.equals("z-errbox-left z-arrow-rd")){
			resultat6 = true;
		}
		WebElement textFirstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"n6")));
		Actions action4 = new Actions(driver);
		action4.clickAndHold(bulleMessErreur).moveToElement(textFirstName).release(textFirstName).build().perform();
		Thread.sleep(2000);
		//WebElement  pouet3 = bulleMessErreur.findElement(By.xpath("./../.."));
		//String attributPouet3 = pouet3.getAttribute("class");
		//initialisation du boolean
		//boolean resultat7 = false;
		//if( attributPouet3.equals("z-errbox-left z-arrow-r")){
		//	resultat7 = true;
		//}
		WebElement textBoundUser = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"67-cnt")));
		Actions action5 = new Actions(driver);
		action5.clickAndHold(bulleMessErreur).moveToElement(textBoundUser).release(textBoundUser).build().perform();
		Thread.sleep(2000);
		//WebElement  pouet4 = bulleMessErreur.findElement(By.xpath("./../.."));
		//String attributPouet4 = pouet4.getAttribute("class");
		//boolean resultat8 = false;
		//if( attributPouet4.equals("z-errbox-left z-arrow-ru")){
		//	resultat8 = true;
		//}		
		WebElement boutonCancel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"bf-box")));
		Actions action6 = new Actions(driver);
		action6.clickAndHold(bulleMessErreur).moveToElement(boutonCancel).release(boutonCancel).build().perform();
		Thread.sleep(2000);
		WebElement  pouet5 = bulleMessErreur.findElement(By.xpath("./../.."));
		String attributPouet5 = pouet5.getAttribute("class");
		//initialisation du boolean
		boolean resultat9 = false;
		if( attributPouet5.equals("z-errbox-left z-arrow-ru")){
			resultat9 = true;
		}	
/*
		Thread.sleep(2000);
		WebElement boutonCroixMessErreur = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='z-errbox-right z-errbox-close']")));
		boutonCroixMessErreur.click();
	*/	
		//initialisation du boolean
		boolean resultatFinal = false;
		if (resultat3 == true && resultat4 == true && resultat5 == true && resultat6 == true &&  resultat9 == true){
			resultatFinal= true;
		}
		return resultatFinal;
		
	}
	//verification de l'appartion du mess d'erreur lors de la creation dun utilisateur en selectionnant le bouton radion lier a un utilisateur existant
	public boolean MessErreur1(String fName, String lName, String ID){
		//renseignemnt de l'utilisateur
		WebElement champFirstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"o6")));
		WebElement champLastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"u6")));
		WebElement champID = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"x6")));
			
		
		champFirstName.sendKeys(fName);
		champLastName.sendKeys(lName);
		champID.sendKeys(ID);
		//lier l'utilisateur un a profil existant
		WebElement chkboxBoundUser = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"lf-real")));
		chkboxBoundUser.click();
		WebElement boutonSave = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[.='Save']")));
		boutonSave.click();
		//Apparition du message d'erreur
		WebElement messErreur = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[.='please select a user to bound']")));
		//initialisation du boolean
		boolean resultat10 = false;
		if ( messErreur.getText().equals("please select a user to bound")){
			resultat10 = true;
		}
		return resultat10;
	}

	public boolean MessErreur2(){
		//lier l'utilisateur un a nouveau profil 
		WebElement chkboxBoundUser1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"mf-real")));
		chkboxBoundUser1.click();
		WebElement boutonSave = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[.='Save']")));
		boutonSave.click();
		//Apparition du message d'erreur
		WebElement messErreur2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[.='cannot be empty']")));
		//initialisation du boolean
		boolean resultat11 = false;
		if ( messErreur2.getText().equals("cannot be empty")){
			resultat11 = true;
		}
		return resultat11;		
		
	}
	public boolean MessErreur3(String username, String password, String rePassword, String eMail){
		WebElement champUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"k8")));
		WebElement champPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"n8")));
		WebElement champRePassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"q8")));
		WebElement champEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"t8")));
		champUsername.sendKeys(username);
		champPassword.sendKeys(password);
		champRePassword.sendKeys(rePassword);
		champEmail.sendKeys(eMail);
		WebElement boutonSave = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[.='Save']")));
		boutonSave.click();
		WebElement messErreur3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[.='passwords do not match']")));
		//initialisation du boolean
		boolean resultat12 = false;
		if ( messErreur3.getText().equals("passwords do not match")){
			resultat12 = true;
		}
		return resultat12;		
		
	}
	public boolean MessErreur45 (String ID, String username, String rePassword){
		WebElement champID = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"x6")));
		champID.clear();
		champID.sendKeys(ID);
		WebElement champUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"k8")));
		champUsername.clear();
		champUsername.sendKeys(username);
		WebElement champRePassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"q8")));
		champRePassword.clear();
		champRePassword.sendKeys(rePassword);
		WebElement boutonSave = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[.='Save']")));
		boutonSave.click();
		WebElement messErreur4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"t7")));
		WebElement messErreur5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"x7")));
		//initialisation du boolean
		boolean resultat13 = false;
		if ( messErreur4.getText().equals("ID already used. It has to be be unique")){
			resultat13 = true;
		}
		//initialisation du boolean
		boolean resultat14 = false;
		if ( messErreur5.getText().equals("username is already being used by another user")){
			resultat14 = true;
		}
		
		//initialisation du boolean
		boolean resultatFinal = false;
		if (resultat13 == true && resultat14 == true){
			resultatFinal= true;
		}
		return resultatFinal;
	}
	public void CreerUtilisateur2(String ID, String username){
		WebElement champID = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"x6")));
		champID.clear();
		champID.sendKeys(ID);
		WebElement champUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"k8")));
		champUsername.clear();
		champUsername.sendKeys(username);	
		WebElement boutonSave = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[.='Save']")));
		boutonSave.click();
		
		
	}


}
