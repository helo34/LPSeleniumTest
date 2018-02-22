package libreplanTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PageCalendrier extends PageMenu {
	
	public PageCalendrier(WebDriver driver) {
		super(driver);
	}
	
	@FindBy (how=How.XPATH, using="//span[@title='Create New Project']")
	public WebElement boutonCreateProject;
	
	@FindBy (how=How.XPATH, using="//span[@class='z-label'][.='Projects Planning']")
	public WebElement ongletProjectsPlanning;
	
	@FindBy (how=How.XPATH, using="//span[@class='z-label'][.='Name']")
	public WebElement CreateNewProjectName;
	
	@FindBy (how=How.XPATH, using="//span[@class='z-label'][.='Template']")
	public WebElement CreateNewProjectTemplate;
	
	@FindBy (how=How.XPATH, using="//span[@class='z-label'][.='Code']")
	public WebElement CreateNewProjectCode;
	
	@FindBy (how=How.XPATH, using="//span[@class='z-label'][.='Starting date']")
	public WebElement CreateNewProjectStartingDate;	
	
	@FindBy (how=How.XPATH, using="//span[@class='z-label'][.='Deadline']")
	public WebElement CreateNewProjectDeadline;
	
	@FindBy (how=How.XPATH, using="//span[@class='z-label'][.='Customer']")
	public WebElement CreateNewProjectCustomer;
	
	@FindBy (how=How.XPATH, using="//span[@class='z-label'][.='Calendar']")
	public WebElement CreateNewProjectCalendar;
	
	@FindBy (how=How.XPATH, using="//span[@class='save-button global-action z-button']/table/tbody/tr[2]/td[2][.='Accept']")
	public WebElement CreateNewProjectButtonAccept;
	
	@FindBy (how=How.XPATH, using="//span[@class='cancel-button global-action z-button']/table/tbody/tr[2]/td[2][.='Cancel']")
	public WebElement CreateNewProjectButtonCancel;
	
	@FindBy (how=How.XPATH, using="//div[@class='z-row-cnt z-overflow-hidden']/input")
	public WebElement champName;
	
	@FindBy (how=How.XPATH, using="//i[@class='z-bandbox']/input[@class='z-bandbox-inp']")
	public WebElement champTemplate;
	
	@FindBy (how=How.XPATH, using="//div[@class='z-row-cnt z-overflow-hidden']/table/tbody/tr/td/table/tbody/tr/td[1]/input")
	public WebElement champCode;
	
	@FindBy (how=How.XPATH, using="//label[.='Generate code']/preceding-sibling::input")
	public WebElement checkBoxGenerateCode;
	
	@FindBy (how=How.XPATH, using="//td[@class='z-button-cm'][.='Projects Planning']")
	public WebElement menuGaucheProjectsPlanning;
	
	@FindBy (how=How.XPATH, using="//td[@class='z-button-cm'][.='Projects List']")
	public WebElement menuGaucheProjectsList;
	
	//Méthode de récupération de l'ID variable
	public String prefix(){
		WebElement bouton = driver.findElement(By.xpath("//body/div"));
		String toto = bouton.getAttribute("id");
		String prefix = toto.substring(0, 4);
		return prefix;
	}
	
	//Méthode de clique sur le bouton création d'un nouveau projet
	public void cliquerBoutonCreateProject(){
		boutonCreateProject.click();
	}
	
	//Méthode de vérification de l'affichage de l'onglet planning
	public boolean verifierOngletProjectPlanningPresent() {
		boolean resultat = false;
		if (ongletProjectsPlanning.isDisplayed()) {
			resultat = true;
		}
		return resultat;
	}
	
	//Vérification de l'affichage de la page de creation d'un nouveau projet
	public boolean verifierAffichagePageCreateNewProject() {
		boolean resultat = false;
		if (CreateNewProjectName.isDisplayed() && CreateNewProjectTemplate.isDisplayed() && CreateNewProjectCode.isDisplayed() && CreateNewProjectStartingDate.isDisplayed() && CreateNewProjectDeadline.isDisplayed() && CreateNewProjectCustomer.isDisplayed() && CreateNewProjectCalendar.isDisplayed() && CreateNewProjectButtonAccept.isDisplayed() && CreateNewProjectButtonCancel.isDisplayed()) {
			resultat = true;
		}
		return resultat;
	}
	
	//Méthode pour saisir un nom de projet
	public void saisirChampName(String name){
		this.champName.sendKeys(name);
	}
	
	//Méthode pour décocher la case de génération de code automatique
	public void decocherCaseGenerationCode(){
		this.checkBoxGenerateCode.click();
	}
	
	//Méthode pour saisir un code dans le champ code
	public void saisirChampCode(String code){
		this.champCode.sendKeys(code);
	}
	
	//méthode pour effacer le champ code
	public void effacerChampCode(){
		this.champCode.clear();
	}
	
	// Méthode pour ajouter 5 jours à la date du jour
	public void dateAjouter5jours() {
		
		WebElement champCalendar = driver.findElement(By.xpath("//input[@id='"+prefix()+"k9-real']"));
		
		DateTime today = DateTime.now(); 
		System.out.println("Date du jour : "+today);
		
		DateTime todayPlusFive = today.plusDays(5);
		System.out.println("Ajout de 5 jours à la date du jour : "+todayPlusFive);
		
		SimpleDateFormat format = new SimpleDateFormat ("MMM d, yyyy", Locale.ENGLISH);
		String date = format.format(todayPlusFive.toDate());
		System.out.println("nouveau format de la date : "+date);
		
		champCalendar.clear();
		champCalendar.sendKeys(date);
	}
	
	//méthode pour ajouter 15 jours à la date du jour J
	public void dateAjouter15jours() {
		WebElement champDeadline = driver.findElement(By.xpath("//input[@id='"+prefix()+"n9-real']"));
		DateTime today = DateTime.now(); 
		DateTime todayPlusFifteen = today.plusDays(15);
		//System.out.println(today);
		//System.out.println(todayPlusFifteen);
		SimpleDateFormat format = new SimpleDateFormat ("MMM d, yyyy", Locale.ENGLISH);
		String date = format.format(todayPlusFifteen.toDate());
		//System.out.println(date);
		
		champDeadline.clear();
		champDeadline.sendKeys(date);
	}
	
	
	//méthode poru cliquer sur le bouton accepter afin de confirmation la création d'un projet
	public PageProjetDetails cliquerBoutonAccepterCreationProjet(){
		this.CreateNewProjectButtonAccept.click();
		return PageFactory.initElements(driver, PageProjetDetails.class);
	}
	
	//Méthode pour vérifier la présence du bouton ProjectDetails du menu vertical Gauche
	public boolean VerifierPresenceProjectDetailsMenuGauche(){
		boolean resultat = false;
		if (menuGaucheProjectsPlanning.isDisplayed()) {
			resultat = true;
		}
		return resultat;
	}
	
	//Méthode pour aller dans la page de présentation de la liste des projets
	public PageProjetList allerPageProjetList(){
		this.menuGaucheProjectsList.click();
		return PageFactory.initElements(driver, PageProjetList.class);
	}
}
