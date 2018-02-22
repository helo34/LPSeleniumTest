package libreplanTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PageMenu extends PageSource{

	public PageMenu(WebDriver driver) {
		super(driver);
		
	}
	
//Definition des éléments du menu.
	
	//Bouton ressources deployant un menu déroulant.
	@FindBy(how=How.XPATH,using="//div[@class='mainmenu z-menubar-hor']/table/tbody/tr/td[2]/table/tbody/tr/td[2]/div/button")
	public WebElement boutonResources;
	
	@FindBy(how=How.XPATH,using="//div[@class='mainmenu z-menubar-hor']/table/tbody/tr/td[1]")
	public WebElement boutonPlanning;
	
	//boutons dans le menus deroulant Resources
	@FindBy(how=How.XPATH,using="//a[@href='/libreplan/resources/criterions/criterions.zul']")
	public WebElement boutonCriteria;
	
	//boutons dans le menus deroulant Planning
	@FindBy(how=How.XPATH,using="//a[@href='/libreplan/planner/index.zul;orders_list']")
	public WebElement boutonProjects;
	
	
//Recherche du préfixe des id de la page
	public String prefix(){
		WebElement bouton = driver.findElement(By.xpath("//body/div"));
		String id = bouton.getAttribute("id");
		String prefix = id.substring(0, 4);
		return prefix;
	}

//Accès aux différentes pages depuis le menu horizontal
public ListCalendar accesAdminCal(){
	Actions actionAdminCal = new Actions(driver);
	WebElement boutonResources = driver.findElement(By.id(prefix()+"r-b"));
	WebElement boutonCalendars = driver.findElement(By.id(prefix()+"w-a"));
	actionAdminCal.moveToElement(boutonResources).moveToElement(boutonCalendars).click().build().perform();
	return PageFactory.initElements(driver, ListCalendar.class);
}

public ListJoursExcepCal listJoursExcepCal(){	
	Actions actionAdminCal = new Actions(driver);
	WebElement boutonResources = driver.findElement(By.id(prefix()+"r-b"));
	WebElement boutonJoursExcepCal = driver.findElement(By.id(prefix()+"x-a"));
	actionAdminCal.moveToElement(boutonResources).moveToElement(boutonJoursExcepCal).click().build().perform();
	return PageFactory.initElements(driver, ListJoursExcepCal.class);
}

public void accesAdminCritere(){
		Actions actionAdminCritere = new Actions(driver);
		actionAdminCritere.moveToElement(boutonResources).moveToElement(boutonCriteria).click().build().perform();
	}
	
	public void accesPageProjetList(){
		Actions actionPlanningProjet = new Actions(driver);
		actionPlanningProjet.moveToElement(boutonPlanning).moveToElement(boutonProjects).click().build().perform();
}
	
public ListCriteria listCriteria (){
	Actions actionAdminCal = new Actions(driver);
	WebElement boutonResources = driver.findElement(By.id(prefix()+"r-b"));
	WebElement boutonCriteria = driver.findElement(By.id(prefix()+"w-a"));
	actionAdminCal.moveToElement(boutonResources).moveToElement(boutonCriteria).click().build().perform();
	return PageFactory.initElements(driver, ListCriteria.class);
	
}
public ListWorkers listWorkers(){
	Actions actionAdminCal = new Actions(driver);
	WebElement boutonResources = driver.findElement(By.id(prefix()+"r-b"));
	WebElement boutonWorkers = driver.findElement(By.id(prefix()+"w-t"));
	actionAdminCal.moveToElement(boutonResources).moveToElement(boutonWorkers).click().build().perform();
	return PageFactory.initElements(driver, ListWorkers.class);
	
}
public ListMachine listMachine(){
	Actions actionAdminCal = new Actions(driver);
	WebElement boutonResources = driver.findElement(By.id(prefix()+"r-b"));
	WebElement boutonMachine = driver.findElement(By.id(prefix()+"w-u"));
	actionAdminCal.moveToElement(boutonResources).moveToElement(boutonMachine).click().build().perform();
	return PageFactory.initElements(driver, ListMachine.class);
	
}
public PageProgress accesProgress(){
	Actions actionAdminCal = new Actions(driver);
	WebElement boutonResources = driver.findElement(By.id(prefix()+"r-b"));
	WebElement boutonProgress = driver.findElement(By.id(prefix()+"w-z"));
	actionAdminCal.moveToElement(boutonResources).moveToElement(boutonProgress).click().build().perform();
	return PageFactory.initElements(driver, PageProgress.class);
	
}
public ListQualityForm listQualityForm(){
	Actions actionAdminCal = new Actions(driver);
	WebElement boutonResources = driver.findElement(By.id(prefix()+"r-b"));
	WebElement boutonQualityForm = driver.findElement(By.id(prefix()+"20-a"));
	actionAdminCal.moveToElement(boutonResources).moveToElement(boutonQualityForm).click().build().perform();
	return PageFactory.initElements(driver, ListQualityForm.class);
	
}
public PageNewProjet selectBoutonNewProjet(){
	WebElement boutonNewProjet = driver.findElement(By.xpath("//img[@src='/libreplan/common/img/ico_add.png']"));
	boutonNewProjet.click();
	return PageFactory.initElements(driver, PageNewProjet.class);
}

public PageConnexion deconnexion(){
	WebElement boutonLogOut = driver.findElement(By.xpath("//a[.='[Log out]']"));
	boutonLogOut.click();
	return PageFactory.initElements(driver, PageConnexion.class);
}

}
