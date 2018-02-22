package libreplanTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageDashbord extends PageSource {

	public PageDashbord(WebDriver driver) {
		super(driver);
	}

public boolean verifConnexion(){
	WebElement myDashBoard = driver.findElement(By.xpath("//div[.='My dashboard']"));
	boolean tata = false;
	if (myDashBoard.isDisplayed()){
		tata=true;
	}
	return tata;
}
}
