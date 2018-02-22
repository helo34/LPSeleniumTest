package libreplanTest;





import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CreateProgressType extends PageMenu{

	public CreateProgressType(WebDriver driver) {
		super(driver);
		}

public void creaTypeProgress1(String name, String number){
	WebElement champProgressName = driver.findElement(By.id(prefix()+"55"));
	champProgressName.sendKeys(name);
		
	if ( !driver.findElement(By.id(prefix()+"85")).isSelected())
	{
		driver.findElement(By.id(prefix()+"85")).click();
	}
	WebElement champDefaultMaxValue = driver.findElement(By.id(prefix()+"b5"));
	champDefaultMaxValue.clear();
	champDefaultMaxValue.sendKeys(number);
	if ( driver.findElement(By.id(prefix()+"k5")).isSelected())
	{
		driver.findElement(By.id(prefix()+"k5")).click();
	}
	WebElement boutonSave = driver.findElement(By.xpath("//td[.='Save']"));
	boutonSave.click();
		
}

public void creaTypeProgress2(String name){
	WebElement champProgressName = driver.findElement(By.id(prefix()+"55"));
	champProgressName.sendKeys(name);
		
	if ( !driver.findElement(By.id(prefix()+"85")).isSelected())
	{
		driver.findElement(By.id(prefix()+"85")).click();
	}
	if ( !driver.findElement(By.id(prefix()+"k5")).isSelected())
	{
		driver.findElement(By.id(prefix()+"k5")).click();
	}
	WebElement boutonSaveAndContinue = driver.findElement(By.xpath("//td[.='Save & Continue']"));
	boutonSaveAndContinue.click();
	WebElement boutonCancel = driver.findElement(By.xpath("//td[.='Cancel']"));
	boutonCancel.click();
}	



@FindBy(how=How.PARTIAL_LINK_TEXT, using="Test1")
List<WebElement> tousElementsTypeAvancement;

	public boolean contientAuMoinsUnElement() {
	return tousElementsTypeAvancement.size() > 0;
}
public boolean contientLaReference( String reference) {
	List<WebElement> ligneTableau = driver.findElements(By.xpath("//tbody[@id='"+prefix()+"o5']/tr"));
	boolean resultat = false;
	for(WebElement ligne : ligneTableau) {
		WebElement premiereCase = ligne.findElement(By.xpath("./td/div/span"));
		if( premiereCase.getText().equals(reference)){
			resultat = true;
			
		}
	}
	return resultat;
}
	
}

	
	

