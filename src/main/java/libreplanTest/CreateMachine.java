package libreplanTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateMachine extends PageMenu{

	public CreateMachine(WebDriver driver) {
		super(driver);
		
	}
	WebDriverWait wait = new WebDriverWait(driver,10);

	@FindBy(how=How.XPATH,using="//td[.='Save']")
	public WebElement boutonSAVE;
	@FindBy(how=How.XPATH,using="//td[.='Save & Continue']")
	public WebElement boutonSAVEContinue;
	@FindBy(how=How.XPATH,using="//td[.='Cancel']")
	public WebElement boutonCancel;

	public boolean verifBlockCreateMachine(){
		//verification de la présence de tout les elements du block1
		WebElement champCode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"g6")));
		WebElement chkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"h6-real")));
		WebElement champName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"k6")));
		WebElement champDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"n6")));
		WebElement menuType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"s6")));
		//Determination d'un liste d'elements  selectionner par default du menu deroulant
		Select select = new Select(menuType);
		List<WebElement> toto = select.getAllSelectedOptions();
		boolean option = false;
		//Determination de la selection de l'element par default "normal resource"
		for(WebElement e:toto){
		if(e.getText().equals("Normal resource")){
			option = true;
		}
		}
		//verification  que les champs sont vides
		boolean champVide = false;
		if( champName.getText().equals("") && champDescription.getText().equals("")){
			champVide= true;
		}
		//verification que la checkbox est bien cochées
		boolean check = false;
		if (chkbox.isSelected()){
			check = true;
		}
		
		boolean resultat1 = false;
		//Verification que tout les boolean sont vrai
		if (champCode.isDisplayed() && champDescription.isDisplayed() && champName.isDisplayed() && menuType.isDisplayed() && option==true && champVide == true && check == true){
			resultat1=true;
		}
		return resultat1;

	}

/*public boolean boutonDispo(){
	WebElement boutonSave = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[.='Save']")));
	WebElement boutonSaveContinue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[.='Save & Continue']")));
	WebElement boutonCancel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[.='Cancel']")));
	boolean resultat2 = false;
	if (boutonSave.isDisplayed() && boutonSaveContinue.isDisplayed() && boutonCancel.isDisplayed()){
		resultat2 = true;
	}
	
return resultat2;	
}

*/
	//#####################################################################################################################################
		//partie methode introduction d'élement dans les champs.
	public void blockCreateMachine(String code, String name){
		WebElement chkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"h6-real")));
		if (chkbox.isSelected()){
			chkbox.click();
		}
		
		WebElement champCode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"g6")));
		champCode.clear();
		champCode.sendKeys(code);
		WebElement champName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"k6")));
		champName.sendKeys(name);
		WebElement champDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prefix()+"n6")));
		champDescription.sendKeys(name);
		boutonSAVEContinue.click();
	
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
	public void retournerListeMachine(){
		WebElement bouttonCancel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='"+prefix()+"ld-box']/tbody/tr/td[.='Cancel']")));
		bouttonCancel.click();
	}
}
	
	

