package libreplanTest;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PageProjetDetails extends PageMenu{

	public PageProjetDetails(WebDriver driver) {
		super(driver);
	}

	
	@FindBy (how=How.XPATH, using="//td[@class='z-button-cm'][.='Project Details']")
	public WebElement menuGaucheProjectDetails;
	
	@FindBy (how=How.XPATH, using="//span[@class='perspective order-scheduling z-button']/table/tbody/tr[2]/td[2][@class='z-button-cm'][.='Project Scheduling']")
	public WebElement menuGaucheProjectScheduling;
	
	@FindBy (how=How.XPATH, using="//td[@class='z-button-cm'][.='Resources Load']")
	public WebElement menuGaucheResourcesLoad;
	
	@FindBy (how=How.XPATH, using="//td[@class='z-button-cm'][.='Advanced Allocation']")
	public WebElement menuGaucheAdvancedAllocation;
	
	@FindBy (how=How.XPATH, using="//td[@class='z-button-cm'][.='Dashboard']")
	public WebElement menuGaucheDashboard;
	
	@FindBy (how=How.XPATH, using="//span[@class='z-tab-text'][.='WBS (tasks)']")
	public WebElement ongletWBS;
	
	@FindBy (how=How.XPATH, using="//span[@class='z-tab-text'][.='General data']")
	public WebElement ongletGeneralData;
	
	@FindBy (how=How.XPATH, using="//span[@class='z-tab-text'][.='Cost']")
	public WebElement ongletCost;
	
	@FindBy (how=How.XPATH, using="//span[@class='z-tab-text'][.='Progress']")
	public WebElement ongletProgress;
	
	@FindBy (how=How.XPATH, using="//span[@class='z-tab-text'][.='Labels']")
	public WebElement ongletLabels;
	
	@FindBy (how=How.XPATH, using="//span[@class='z-tab-text'][.='Criterion Requirement']")
	public WebElement ongletCriterionRequirement;
	
	@FindBy (how=How.XPATH, using="//span[@class='z-tab-text'][.='Materials']")
	public WebElement ongletMaterials;
	
	@FindBy (how=How.XPATH, using="//span[@class='z-tab-text'][.='Task quality forms']")
	public WebElement ongletTaskQualityForms;
	
	@FindBy (how=How.XPATH, using="//span[@class='z-tab-text'][.='Authorizations']")
	public WebElement ongletAuthorizations;	
	
	@FindBy (how=How.XPATH, using="//img[@src='/libreplan/common/img/ico_save.png']")
	public WebElement boutonSaveProjet;	
	
	@FindBy (how=How.XPATH, using="//img[@src='/libreplan/common/img/ico_back.png']")
	public WebElement boutonCancelProjet;	
	
	@FindBy (how=How.XPATH, using="//div[@class='z-messagebox']/span[@class='z-label'][.='Unsaved changes will be lost. Are you sure?']")
	public WebElement textWindowCancelProjet;
	
	@FindBy (how=How.XPATH, using="//div[@class='z-separator-hor-bar']/following-sibling::table//td[@class='z-button-cm'][.='OK']")
	public WebElement boutonOKWindowCancelProjet;
	
	@FindBy (how=How.XPATH, using="//div[@class='z-separator-hor-bar']/following-sibling::table//td[@class='z-button-cm'][.='Cancel']")
	public WebElement boutonCancelWindowCancelProjet;
	
	@FindBy (how=How.XPATH, using="//tr[@class='ruta']//strong[.='START']")
	public WebElement filArianeStart;
	
	@FindBy (how=How.XPATH, using="//tr[@class='ruta']//span[.='Planning']")
	public WebElement filArianePlanning;
	
	@FindBy (how=How.XPATH, using="//tr[@class='ruta']//span[.='Project Details']")
	public WebElement filArianeProjectDetails;
	
	@FindBy (how=How.XPATH, using="//span[.='New task']/../following-sibling::td[2]/input")
	public WebElement champNewTask;
	
	@FindBy (how=How.XPATH, using="//span[.='Hours']/../following-sibling::td[2]/input")
	public WebElement champHours;
	
	@FindBy (how=How.XPATH, using="//span[.='Hours']/../following-sibling::td[4]/span/table/tbody/tr[2]/td[2]")
	public WebElement boutonAdd;
	
	@FindBy (how=How.XPATH, using="//span[.='Project saved']")
	public WebElement texteProjectSavedTask;
	
	@FindBy (how=How.XPATH, using="//div[@class='z-separator-hor-bar']/following-sibling::table[1]//td[@class='z-button-cm'][.='OK']")
	public WebElement boutonOKSaveTask;
	
	@FindBy (how=How.XPATH, using="//div[@class='z-window-modal-header z-window-modal-header-move']/div")
	public WebElement boutonCroixSaveTask;
	
	
	//Méthode de récupération de l'ID variable
	public String prefix(){
		WebElement bouton = driver.findElement(By.xpath("//body/div"));
		String toto = bouton.getAttribute("id");
		String prefix = toto.substring(0, 6);
		return prefix;
	}
	
	//Méthode pour vérifier la présence du bouton Project Details dans le menu vertical gauche et la présence de l'onglet WBS dans la barre horizontale
	public boolean VerifierBoutonProjectDetailsEtOngletWBS(){
		
		boolean resultat = false;
		if (menuGaucheProjectDetails.isDisplayed() && ongletWBS.isDisplayed()) {
			resultat = true;
		}
		return resultat;
	}
	
	//Méthode pour vérifier la présence des boutons du menu gauche
	public boolean VerifierBoutonsMenuGauche(){
		
		boolean resultat = false;
		if (menuGaucheProjectDetails.isDisplayed() && menuGaucheProjectScheduling.isDisplayed() && menuGaucheResourcesLoad.isDisplayed() && menuGaucheAdvancedAllocation.isDisplayed() && menuGaucheDashboard.isDisplayed()) {
			resultat = true;
		}
		return resultat;
	}
	
	//Méthode pour vérifier la présence des onglets constituant la barre horizontale
	public boolean VerifierOngletsBarreHorizontale(){
		
		boolean resultat = false;
		if (ongletWBS.isDisplayed() && ongletGeneralData.isDisplayed() && ongletCost.isDisplayed() && ongletProgress.isDisplayed() && ongletLabels.isDisplayed() && ongletCriterionRequirement.isDisplayed() && ongletMaterials.isDisplayed() && ongletTaskQualityForms.isDisplayed() && ongletAuthorizations.isDisplayed()) {
			resultat = true;
		}
		return resultat;
	}
	
	//Méthode pour vérifier la présence du bouton Save et Cancel 
	public boolean VerifierPresenceBoutonSaveEtCancel(){
		
		boolean resultat = false;
		if (boutonSaveProjet.isDisplayed() && boutonCancelProjet.isDisplayed()) {
			resultat = true;
		}
		return resultat;
	}
	
	//Méthode pour cliquer sur le bouton cancel
	public void cliquerBoutonCancel(){
		this.boutonCancelProjet.click();
	}
	
	
	//Méthode pour vérifier la présence du texte, des boutons Cancel et Ok de la fenêtre Cancel Projet
	public boolean VerifierPresenceTexteEtBoutonsOKEtCancelFenetreCancelProjet(){
		boolean resultat = false;
		if (textWindowCancelProjet.isDisplayed() && boutonOKWindowCancelProjet.isDisplayed() && boutonCancelWindowCancelProjet.isDisplayed()) {
			resultat = true;
		}
		return resultat;
	}

	//Vérifier la présence du bouton Cancel dans la fenetre d'annulation d'edidion
	public void cliquerBoutonCancelFenetreAnnulationEdition(){
		this.boutonCancelWindowCancelProjet.click();
	}
	
	
	//Méthode pour cliquer sur le bouton Ok dans la fenetre d'annulation d'édition
	public PageCalendrier cliquerBoutonOKFenetreAnnulationEdition(){
		this.boutonOKWindowCancelProjet.click();
		return PageFactory.initElements(driver, PageCalendrier.class);
	}
	
	
	//Méthode pour vérifier l'onglet WBS dans la barre horizontale
	public boolean VerifierOngletWBS(){
		
		boolean resultat = false;
		if (ongletWBS.isDisplayed()) {
			resultat = true;
		}
		return resultat;
	}
	
	//Méthode pour vérifier le fil d'ariane
	public boolean VerifierFilAriane(String nomProjet){
		WebElement filArianeNoMduProjet = driver.findElement(By.xpath("//tr[@class='ruta']//span[.='"+nomProjet+"']"));
		boolean resultat = false;
		if (filArianeStart.isDisplayed() && filArianePlanning.isDisplayed() && filArianeNoMduProjet.isDisplayed() &&
				filArianeProjectDetails.isDisplayed()
				) {
			resultat = true;
		}
		return resultat;
	}
	
	//Méthode pour saisir une valeur dans le champ de nouvelle tache
	public void saisirChampNewTask(String newTask){
		this.champNewTask.sendKeys(newTask);
	}
	
	//Méthode pour saisir dans le champ des heures lors de la création d'une tache
	public void saisirChampHours(String hours){
		this.champHours.clear();
		this.champHours.sendKeys(hours);
	}
	
	//Méthore pour cliquer sur le bout ajouter une tache
	public void clickBoutonAdd(){
		this.boutonAdd.click();
	}
	
	
	//méthode pour vérifier la présence de la nouvelle tache créée dans la liste des taches
	public boolean verifierPresenceNewTask(String code, String name, String hours, String budget, String MustStartAfter, String deadline) {
		
		List<WebElement> lignes = driver.findElements(By.xpath("//div[@class='z-dottree-header-bg']/following-sibling::div[1]/table/tbody[2]/tr"));
			boolean resultat = false;
			for(WebElement ligne : lignes){
				List<WebElement> cases=ligne.findElements(By.xpath("./td"));
				
				if(cases.get(0).findElement(By.xpath("//span[@title='Fully scheduled'][.='F']")).isDisplayed() && 
						cases.get(0).findElement(By.xpath("//span[@title='Fully scheduled'][.='F']/following-sibling::span[2]/table/tbody/tr[2]/td[2]/img")).isDisplayed() && 
						cases.get(1).findElement(By.xpath(".//input")).getAttribute("value").equals(code) && 	
						cases.get(2).findElement(By.xpath(".//input")).getAttribute("value").equals(name) && 
						cases.get(3).findElement(By.xpath(".//input")).getAttribute("value").equals(hours) && 
						cases.get(4).findElement(By.xpath(".//input")).getAttribute("value").equals(budget) && 
						cases.get(5).findElement(By.xpath(".//input")).getAttribute("value").equals(MustStartAfter) && 
						cases.get(6).findElement(By.xpath(".//input")).getAttribute("value").equals(deadline) && 
						cases.get(7).findElement(By.xpath("//div[@class='z-dottree-header-bg']/following-sibling::div[1]//span[@title='Edit']")).isDisplayed() &&
						cases.get(7).findElement(By.xpath("//div[@class='z-dottree-header-bg']/following-sibling::div[1]//span[@title='Delete']")).isDisplayed()

						){
					resultat = true;
				}
			}
			return resultat;
	}
	
	//Méthode pour sélectionner une tache
	public void selectTask(String nomTache){
		
		WebElement select = driver.findElement(By.xpath("//input[@value='"+nomTache+"']"));
		select.click();
	}
	
	//Méthode pour cliquer sur la fleche descendante permettant de descendre une tache d'un niveau dans la liste des taches
	public void clickArrowDown(){
		
		WebElement arrowDown = driver.findElement(By.xpath("//span[@title='Move selected task down']"));
		arrowDown.click();
	}
	
	//Méthode pour cliquer sur la fleche montante permettant de monter une tache d'un niveau dans la liste des taches
	public void clickArrowUp(){
		
		WebElement arrowDown = driver.findElement(By.xpath("//span[@title='Move selected task up']"));
		arrowDown.click();
	}
	
	//Méthode pour saisir un code à une tache
	public void saisirCodeTaches() {
		
		List<WebElement> lignes = driver.findElements(By.xpath("//div[@class='z-dottree-header-bg']/following-sibling::div[1]/table/tbody[2]/tr"));
			for(WebElement ligne : lignes){
				WebElement cases=ligne.findElement(By.xpath("./td[3]/div/input"));
				String toto = cases.getAttribute("value");
				String toto1 = toto.substring(5,6);
				WebElement caseARemplir = ligne.findElement(By.xpath("./td[2]/div/input"));
				caseARemplir.sendKeys("T"+toto1);
			}
	}
	
	//Méthode pour saisir une date pour la tache associé
	public void SaisirDateTache() {
		
		DateTime today = DateTime.now(); 
		DateTime todayPlusFive = today.plusDays(5);
		DateTime todayPlusEight = today.plusDays(8);
		DateTime todayPlusThree = today.plusDays(3);
		SimpleDateFormat format = new SimpleDateFormat ("M/d/yy", Locale.ENGLISH);
		String datePlusFive = format.format(todayPlusFive.toDate());
		String datePlusEight = format.format(todayPlusEight.toDate());
		String datePlusThree = format.format(todayPlusThree.toDate());
		
		List<WebElement> lignes = driver.findElements(By.xpath("//div[@class='z-dottree-header-bg']/following-sibling::div[1]/table/tbody[2]/tr"));
			for(WebElement ligne : lignes){
				WebElement cases=ligne.findElement(By.xpath("./td[3]/div/input"));
				String toto = cases.getAttribute("value");
				if (toto.equals("Tache1-P1")){
					WebElement caseARemplir = ligne.findElement(By.xpath("./td[6]/div/table/tbody/tr/td/table/tbody/tr/td/input"));
					caseARemplir.sendKeys(datePlusFive);
				}
				if (toto.equals("Tache2-P1")) {
					WebElement caseARemplir = ligne.findElement(By.xpath("./td[6]/div/table/tbody/tr/td/table/tbody/tr/td/input"));
					caseARemplir.sendKeys(datePlusEight);
				}
				if (toto.equals("Tache3-P1")) {
					WebElement caseARemplir = ligne.findElement(By.xpath("./td[7]/div/table/tbody/tr/td/table/tbody/tr/td/input"));
					caseARemplir.sendKeys(datePlusThree);
				}
				if (toto.equals("Tache4-P1")) {
					WebElement caseARemplir = ligne.findElement(By.xpath("./td[7]/div/table/tbody/tr/td/table/tbody/tr/td/input"));
					caseARemplir.sendKeys(datePlusFive);
				}
			}
	}
	
	//Méthode pour cliquer sur le bouton save afin de sauvegarder les nouvelles taches créées
	public void cliquerBoutonSaveTache(){
		this.boutonSaveProjet.click();
	}
	
	//Méthode pour vérifier la présence du texte et des boutons OK et croix relatifs à la fenêtre de confirmation de sauvegardes des taches
	public boolean VerifierPresenceTexteEtBoutonsOKEtCroixFenetreSauvegardeTask(){
		boolean resultat = false;
		if (texteProjectSavedTask.isDisplayed() && boutonOKSaveTask.isDisplayed() && boutonCroixSaveTask.isDisplayed()) {
			resultat = true;
		}
		return resultat;
	}
	
	//méthode pour cliquer sur le bouton OK de la fenêtre de confirmation de sauvegardes des taches
	public void cliquerBoutonOKFenetreSaveTask(){
		this.boutonOKSaveTask.click();
	}
	
	//méthode pour cliquer sur le bouton ProjetScheduling du menu Vertical Gauche
	public void cliquerBoutonProjectSchedulingMenuVerticalGauche(){
		this.menuGaucheProjectScheduling.click();
	}
}
