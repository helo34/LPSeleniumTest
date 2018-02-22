package libreplanTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PageProgress extends PageMenu{

	public PageProgress(WebDriver driver) {
		super(driver);
		
	}
@FindBy(how=How.XPATH,using="//td[.='Create']")
public WebElement boutonCreate;

public CreateProgressType clickBoutonCreate(){
	this.boutonCreate.click();
	return PageFactory.initElements(driver, CreateProgressType.class);
}
}
