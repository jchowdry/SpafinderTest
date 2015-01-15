package spafinder.com;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import spafinder.com.locator.IGC_Locator;
import spafinder.com.value.IGC_Values;

public class IGC_GuestUsingFlyerMiles extends spafinder.com.base.USABaseClass {

	IGC_Locator gcl = new IGC_Locator ();
	IGC_Values  gcv = new IGC_Values ();
	
	@Test
	public void IGC_GuestUsingFlyerMilesInUSA () throws InterruptedException, IOException{
	
		driver.get(baseURL+"/Catalog/online-spagiftcertificates.htm?type=email");
		System.out.println ("");
		
//		driver.findElement(By.cssSelector("a.selector")).click();
//		driver.findElement(By.xpath("//div[2]/ul/li[5]")).click();
//		new Select(driver.findElement(By.id("instantGCInfoList[0].amount"))).selectByVisibleText("125");

		typeByXpath (gcl.deliveryEmailAddress, gcv.emaiValue);
		clickByXpath (gcl.PersonalizeIt);
		typeByXpath (gcl.from, gcv.firstNameValue);
		typeByXpath (gcl.to, gcv.lastNameValue);
		clickByXpath (gcl.ProceedToCheckout);
		clickByXpath (gcl.guestButton);
			
			 
// Flyer Miles Info
		scroll ();
		clickByXpath (gcl.airLineRadioButton);
		clickByLink (gcl.airLineLinkText);
		clickByXpath (gcl.airLineDropDownList);
		dropDownById (gcl.airLineDropDownMenu, gcv.AmericanAirlines);
		typeById  (gcl.airlineEditBoxLocator, gcv.AirlineValue);
		clickByXpath (gcl.airlineSubmitButton);		
		
//	Billing Information page
		typeByXpath (gcl.email, gcv.emaiValue);
		typeByXpath (gcl.FirstName, gcv.firstNameValue);
		typeByXpath (gcl.LastName, gcv.lastNameValue);
		dropDownById (gcl.countryBoxId, gcv.CountryValue);
		    
		typeByXpath (gcl.address, gcv.AddressValue);
		typeByXpath (gcl.city, gcv.CityValue);
		typeByXpath (gcl.zipCode, gcv.ZipcodeValue);
		scroll ();
		dropDownById (gcl.stateBoxById, gcv.StateValue);
		
		typeByXpath (gcl.phoneNumber, gcv.PhoneNumberValue);
		typeByXpath (gcl.cardNumber, gcv.CardNoValue);
		typeByXpath (gcl.securityCode, gcv.securityCodeValue);
		typeByXpath (gcl.expMM, gcv.ExpMMvalue);
		typeByXpath (gcl.expYY, gcv.ExpYYvalue);
				
	
		clickByXpath (gcl.CompleteYourOderButton);
		iWait (30);
		
		
		String totalMoney  = driver.findElement(By.xpath(gcl.totalPrice)).getText();
		Pattern p = Pattern.compile(".[0-9]{2,3}[.]?[0-9]{0,2}"); // period will print any char. Ex: $, � etc
	    Matcher m = p.matcher(totalMoney);
	     
	    boolean b = m.find();
	    if (b)
	    {
	    	String total = m.group();
	    	System.err.println ("=====================================================================");
	       
	   boolean PF = driver.findElement(By.xpath(gcl.orderNumber)).isDisplayed();
		if (PF == false)
		 {
			passData ("USA", "passFail", 10, gcv.Fail); // writing the text into excel from selenium
		 } else  { 
			 passData ("USA", "passFail", 10, gcv.Pass); // writing the text into excel from selenium
			 setCellData ("USA", "OrderNumber", 10, gcl.orderNumber); // Writing order number into excel file.
			 System.out.println(getCellData("USA", "OrderNumber", 10)); // reading from excel\

			 passData ("USA", "Total", 10, total);
			 System.out.println ("Total Price is: " + total);
			 System.err.println ("=====================================================================");	
	   						
		 		}
			
	    screenShot ("FlyerMiles_IGCGuest_USA");
	    }
	}
}
	

