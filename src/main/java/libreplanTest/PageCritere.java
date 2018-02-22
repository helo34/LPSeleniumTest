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

public class PageCritere extends PageMenu{
		
	public PageCritere(WebDriver driver) {
		super(driver);
	}

	//Definition des élément du menu.

	//Bouton ressources deployant un menu déroulant.
	//@FindBy(how=How.XPATH,using="//body/div")
	//public WebElement bouton;

	WebDriverWait wait = new WebDriverWait(driver,10);
	
	public String prefix(){
		WebElement bouton = driver.findElement(By.xpath("//body/div"));
		String toto = bouton.getAttribute("id");
		String prefix = toto.substring(0, 4);
		return prefix;

	}
	
	//Bouton Créer
	//@FindBy (how=How.XPATH, using="//table[contains(@id,'_5-box')]/tbody/tr[2]/td[2]")
	
	//public WebElement boutonCreate;
		
	public PageCritereCreation creerUnCritere(){
		
		WebElement boutonCreer = driver.findElement(By.xpath("//table[@id='"+prefix()+"_5-box']/tbody/tr[2]/td[2]"));
		boutonCreer.click();
		
		//this.boutonCreate.click();
		
		return PageFactory.initElements(driver, PageCritereCreation.class);
	}
	
	//Vérifier absence du critère dans la liste des critères
	public boolean verifierAbsenceCritere(String nomDuProduit) {
		
		List<WebElement> lignes = driver.findElements(By.xpath("//tbody[@id='"+prefix()+"r4']/tr"));
			boolean resultat = true;
			for(WebElement ligne : lignes){
				List<WebElement> cases=ligne.findElements(By.xpath("./td"));
				
				if(cases.get(0).getText().equals(nomDuProduit)){
					resultat = false;
				}
			}
			return resultat;
	}
	
	//Vérifier présence du critère dans la liste des critères
	public boolean verifierPresenceCritere(String nomDuProduit) {
		
		List<WebElement> lignes = driver.findElements(By.xpath("//tbody[@id='"+prefix()+"r4']/tr"));
			boolean resultat = false;
			for(WebElement ligne : lignes){
				List<WebElement> cases=ligne.findElements(By.xpath("./td"));
				
				if(cases.get(0).getText().equals(nomDuProduit)){
					resultat = true;
				}
			}
			return resultat;
	}
	
	//Sélectionner la ligne du critère à modifier
	public int trouverLaLigneDuCritère(String nomDuProduit) throws ElementNonTrouveException  {
		
		List<WebElement> lignes = driver.findElements(By.xpath("//tbody[@id='"+prefix()+"r4']/tr"));
			int compteurDeLigne = 1;
			for(WebElement ligne : lignes){
				List<WebElement> cases=ligne.findElements(By.xpath("./td"));
				
				if(cases.get(0).getText().equals(nomDuProduit)){
					return compteurDeLigne;
				}
				compteurDeLigne = compteurDeLigne+1;
			}
			throw new ElementNonTrouveException();
	}
	
	//Accéder à la page de modification du critère en passant par le bouton edit
	public void cliquerBoutonModifierDuCritere(String nomDuProduit) throws ElementNonTrouveException {
		
		//Etape 1 : Identifier le bouton modifier sur lequel on veut cliquer
		WebElement boutonModifier = driver.findElement(By.xpath("//tbody[@id='"+prefix()+"r4']/tr["+trouverLaLigneDuCritère(nomDuProduit)+"]/td[5]/div/table/tbody/tr/td/table/tbody/tr/td[1]/span"));
		//Etape 2 : Cliquer sur ce bouton
		boutonModifier.click();
		}
	
	//Sélectionner la ligne du critère à modifier
	public void cliquerBoutonModifierCritereParNom(String nomDuProduit) {
			
		List<WebElement> lignes = driver.findElements(By.xpath("//tbody[@id='"+prefix()+"r4']/tr"));
			for(WebElement ligne : lignes){
				List<WebElement> cases=ligne.findElements(By.xpath("./td"));
					
				if(cases.get(0).getText().equals(nomDuProduit)){
					WebElement case1 =ligne.findElement(By.xpath("./td"));
					case1.click();
				}
			}
	}
	
		
	//Accéder au bouton de suppression du critère sélectionné
	public void cliquerBoutonSuppressionDuCritere(String nomDuProduit) throws ElementNonTrouveException {
			
		//Etape 1 : Identifier le bouton modifier sur lequel on veut cliquer
		WebElement boutonModifier = driver.findElement(By.xpath("//tbody[@id='"+prefix()+"r4']/tr["+trouverLaLigneDuCritère(nomDuProduit)+"]/td[5]/div/table/tbody/tr/td/table/tbody/tr/td[3]/span"));
		//Etape 2 : Cliquer sur ce bouton
		boutonModifier.click();
		}
	
	public void cliquerBoutonAnnulerFenetreSuppressionCritere(){
		
		WebElement boutonAnnuler = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='z-messagebox-btn z-button']/table/tbody/tr[2]/td[2][.='Cancel']")));
		boutonAnnuler.click();
		
		//table[@id='"+prefix()+"z6-box']/tbody/tr[2]/td[2]
	}
	
	public boolean texteVerificationPresenceFenetreSuppressionCritere(String nomCriterion) {
		String texteVerif = driver.findElement(By.xpath("//span[.='Delete Criterion Type \""+nomCriterion+"\". Are you sure?']")).getText();
		String texteAttendu = "Delete Criterion Type \""+nomCriterion+"\". Are you sure?";
		boolean resultat = false;
		if (texteVerif.equals(texteAttendu)) {
			resultat = true;
		}
		return resultat;
	}
	
	public void cliquerBoutonOkFenetreSuppressionCritere(){
		
		WebElement boutonOk = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='z-messagebox-btn z-button']/table/tbody/tr[2]/td[2][.='OK']")));
		boutonOk.click();
	}
	
	public boolean MessageDeletedVerificationPresence(String nomCriterion) {
		String texteVerif = driver.findElement(By.xpath("//span[.='Criterion Type \""+nomCriterion+"\" deleted']")).getText();
		String texteAttendu = "Criterion Type \""+nomCriterion+"\" deleted";
		boolean resultat = false;
		if (texteVerif.equals(texteAttendu)) {
			resultat = true;
		}
		return resultat;
	}
}
