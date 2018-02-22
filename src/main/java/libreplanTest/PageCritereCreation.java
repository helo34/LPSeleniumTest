package libreplanTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageCritereCreation extends PageCritere {

	public PageCritereCreation(WebDriver driver) {
		super(driver);
	}
	
	public String prefix(){
		WebElement bouton = driver.findElement(By.xpath("//body/div"));
		String toto = bouton.getAttribute("id");
		String prefix = toto.substring(0, 4);
		return prefix;
	}
	
	public void ecrireChampNom(String nom){
		WebElement champNom = driver.findElement(By.xpath("//input[@id='"+prefix()+"e5']"));
		champNom.sendKeys(nom);
	}
	
	public void effacerChampNom(){
		WebElement champNom = driver.findElement(By.xpath("//input[@id='"+prefix()+"e5']"));
		champNom.clear();
	}
	
	public void selectType(){
		WebElement menuType = driver.findElement(By.xpath("//i[@id='"+prefix()+"h5-btn']"));
		menuType.click();
		WebElement choixMenu = driver.findElement(By.xpath("//td[@class='z-comboitem-text'][.='WORKER']"));
		choixMenu.click();
		
	}
	
	public void ecrireChampDescription(String description){
		WebElement champDescription = driver.findElement(By.xpath("//textarea[@id='"+prefix()+"t5']"));
		champDescription.sendKeys(description);
	}
	
	public void cliquerBoutonAnnuler(){
		WebElement boutonAnnuler = driver.findElement(By.xpath("//table[@id='"+prefix()+"j6-box']/tbody/tr[2]/td[2][.='Cancel']"));
		boutonAnnuler.click();
		
	}
	
	public void cliquerBoutonEnregistrer(){
		WebElement boutonSave = driver.findElement(By.xpath("//table[@id='"+prefix()+"h6-box']/tbody/tr[2]/td[2][.='Save']"));
		boutonSave.click();
		
	}
	
	public void cliquerBoutonEnregistrerEtContinuer(){
		WebElement boutonSaveEtContinuer = driver.findElement(By.xpath("//table[@id='"+prefix()+"i6-box']/tbody/tr[2]/td[2][.='Save & Continue']"));
		boutonSaveEtContinuer.click();
		
	}
	
	public boolean texteVerificationPresence(String nomCriterion) {
		String texteVerif = driver.findElement(By.xpath("//span[.='Criterion Type \""+nomCriterion+"\" saved']")).getText();
		String texteAttendu = "Criterion Type \""+nomCriterion+"\" saved";
		boolean resultat = false;
		if (texteVerif.equals(texteAttendu)) {
			resultat = true;
		}
		return resultat;
	}
	
	
	public boolean verifierTitrePageModification(String nomCriterion){
		String texteVerif = driver.findElement(By.xpath("//td[@id='"+prefix()+"15-cnt']")).getText();
		String texteAttendu = "Edit Criterion Type: "+nomCriterion;
		boolean resultat = false;
		if (texteVerif.equals(texteAttendu)) {
			resultat = true;
		}
		return resultat;
	}
	
	
	
	
	
}



