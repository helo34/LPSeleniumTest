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

public class PageProjetList extends PageMenu{

	public PageProjetList(WebDriver driver) {
		super(driver);

	}
	
	@FindBy(how=How.XPATH,using="//td[@class='z-button-cm'][.='Projects List']")
	public WebElement boutonProjectsListMenuGauche;

	public boolean verifierPresenceBoutonProjectsListMenuGauche(){
		boolean resultat = false;
		if (boutonProjectsListMenuGauche.isDisplayed()) {
			resultat = true;
		}
		return resultat;
	}

	
	//méthode pour vérifier la présence du nouveau projet créé
	public boolean verifierPresenceProjetCree(String name, String code, String customer, String totalBugdet, String hours, String states) {
		
		DateTime today = DateTime.now(); 
		DateTime todayPlusFive = today.plusDays(5);
		DateTime todayPlusFifteen = today.plusDays(15);
		//System.out.println("date format normal"+todayPlusFifteen);
		SimpleDateFormat format = new SimpleDateFormat ("MMM d, yyyy", Locale.ENGLISH);
		String datePlusFive = format.format(todayPlusFive.toDate());
		//System.out.println("date nouveau format" +datePlusFive);
		String datePlusFifteen = format.format(todayPlusFifteen.toDate());
		
		List<WebElement> lignes = driver.findElements(By.xpath("//div[@class='z-grid-header-bg']/following-sibling::div[1]/table/tbody[2]/tr"));
			boolean resultat = false;
			for(WebElement ligne : lignes){
				List<WebElement> cases=ligne.findElements(By.xpath("./td"));
				
				if(cases.get(0).getText().equals(name) && cases.get(1).getText().equals(code) && 
						cases.get(2).getText().equals(datePlusFive) && cases.get(3).getText().equals(datePlusFifteen) && 
						cases.get(4).getText().equals(customer) && cases.get(5).getText().equals(totalBugdet) && cases.get(6).getText().equals(hours) && 
						cases.get(7).getText().equals(states) && cases.get(8).findElement(By.xpath(".//img[@src='/libreplan/common/img/ico_editar1.png']")).isDisplayed() &&
						cases.get(8).findElement(By.xpath(".//img[@src='/libreplan/common/img/ico_borrar1.png']")).isDisplayed() &&
						cases.get(8).findElement(By.xpath(".//img[@src='/libreplan/common/img/ico_planificador1.png']")).isDisplayed() &&
						cases.get(8).findElement(By.xpath(".//img[@src='/libreplan/common/img/ico_derived1.png']")).isDisplayed()
						) {
					resultat = true;
				}
			}
			return resultat;
	}
	
	//méthode pour cliquer sur le nouveau projet afin d'en ouvrir le contenu
	public PageProjetDetails cliquerProjetCree(String nomDuProduit) {
		
		
		List<WebElement> lignes = driver.findElements(By.xpath("//tr[contains(@class,'clickable-rows')]"));
			for(WebElement ligne : lignes){
				List<WebElement> cases=ligne.findElements(By.xpath("./td"));
				//System.out.println("nb cases: "+cases.size());
				//System.out.println("nom produit: "+nomDuProduit);
				//System.out.println("text case: "+cases.get(0).getText());
				if(cases.get(0).getText().equals(nomDuProduit)){
					//System.out.println("testok");
					cases.get(0).click();
					//System.out.println("clic fait");
				}
			} 
			return PageFactory.initElements(driver, PageProjetDetails.class);
	} 
	
	
}
