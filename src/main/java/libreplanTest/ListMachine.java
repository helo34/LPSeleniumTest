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

public class ListMachine extends PageMenu{

	public ListMachine(WebDriver driver) {
		super(driver);
	}
	WebDriverWait wait = new WebDriverWait(driver,10);
	@FindBy(how=How.XPATH,using="//td[.='Create']")
	public WebElement boutonCreate;
	public boolean verifTableau(){
		//Verification de la présence des éléments du tableau
		WebElement colone1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[.='Name']")));
		WebElement colone2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[.='Description']")));
		WebElement colone3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[.='Code']")));
		WebElement colone4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[.='Queue-based']")));
		WebElement colone5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[.='Operations']")));
		boolean resultat = false;
		
		if( colone1.isDisplayed() && colone2.isDisplayed() && colone3.isDisplayed() && colone4.isDisplayed() && colone5.isDisplayed()){
			resultat = true;
		}
		
		return resultat;
	}
	public boolean verifChamp(){
		//verification de la présence de tout les champs de la page
		WebElement champFilterBy = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"q4-real")));
		WebElement champPersonalDetails = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"d5")));
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

		boolean resultat2=false;
		if ( boutonLoupe.isDisplayed() && boutonMoreOption.isDisplayed() && boutonFilter.isDisplayed() && boutonCreateVerif.isDisplayed()){
			resultat2=true;
		}
		return resultat2;
	}
	public CreateMachine selectBoutonCreate(){
		this.boutonCreate.click();
		return PageFactory.initElements(driver, CreateMachine.class);
	}
	public boolean verifMessage(String message){
		//verification de la présence du message Machine "MACHINETEST1" saved
		WebElement message1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"l7")));
	boolean resultat3=false;
	if (message1.getText().equals(message)){
			resultat3=true;
	}
	return resultat3;
}
	public boolean verifCreaMACHINETEST(String name, String description, String code){
		
		List<WebElement> lignes= driver.findElements(By.xpath("//tbody[@id='"+prefix()+"ud']/tr"));
		//[@class='clickable-rows z-row' or @class='clickable-rows z-row z-grid-odd']
		
		//recherche de l'element selectionner dans la liste titi et determination de la ligne ou il se trouve
				int i = 0;
				int trouver = -1;
				for(WebElement t : lignes){
					WebElement cases = t.findElement(By.xpath("./td[1]/div"));
					String test = cases.getText();
					if(test.equals(name)){
					trouver = i;
					}	
					i++;
				}
				
				
				//recherche du fisrtname associer a l'id trouvée
				String descriptionTrouve = lignes.get(trouver).findElement(By.xpath("./td[2]/div")).getText();
				
				
				//recherche du Surname associer a l'id trouvée
				
				String codeTrouve = lignes.get(trouver).findElement(By.xpath("./td[3]/div")).getText();
		

	boolean resultat5 = false;
	if( codeTrouve.equals(code) && descriptionTrouve.equals(description)){
		resultat5 = true;


	}
	return resultat5;
	}
}
