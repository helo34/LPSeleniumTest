package libreplanTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class PageConnexion extends PageSource {

	//Héritage du driver
	public PageConnexion(WebDriver driver) {
		super(driver);
	}

	//Identification des boutons User, Password et Connexion
	@FindBy(how=How.ID, using = "textfield")
	public WebElement champUser;
	
	@FindBy(how=How.ID, using = "textfield2")
	public WebElement champPassword;
	
	@FindBy(how=How.ID, using = "button")
	public WebElement boutonLogin;
	
	//Méthode de connexion
	public PageCalendrier seConnecter(String login, String mdp) {
		this.champUser.clear();
		this.champUser.sendKeys(login);
		this.champPassword.clear();
		this.champPassword.sendKeys(mdp);
		this.boutonLogin.click();
		return PageFactory.initElements(driver, PageCalendrier.class);
	}
}
