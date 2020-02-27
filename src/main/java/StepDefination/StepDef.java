package StepDefination;


import java.net.URL;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import pages.ScreenLocators;

public class StepDef {
	String bluetoothReq="Yes";
	ScreenLocators ScObject =new ScreenLocators();
	String emailID="singh.ivsingh@gmail.com";
	String passWord="Singh@2302";
	String cityName="koppenberg";
	AndroidDriver<AndroidElement> driver;
	String mobileType="Redmi";
	DesiredCapabilities dc=new DesiredCapabilities();
	
	
	@Given("^user is already on TACX mobile app$")
	public void user_is_already_on_TACX_mobile_app() {
       dc.setCapability(MobileCapabilityType.PLATFORM, "Android");
		
		if(mobileType.equalsIgnoreCase("Samsung")) {
			dc.setCapability(MobileCapabilityType.PLATFORM_VERSION,"9.0"); 
			dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy A70");
		   	dc.setCapability(MobileCapabilityType.UDID, "RZ8M50SS8KB");
		}
		else {
			dc.setCapability(MobileCapabilityType.PLATFORM_VERSION,"6.0.1"); 
			dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Redmi Note 3");
	     	dc.setCapability(MobileCapabilityType.UDID, "d22eb408");
		}
		//Other Capabilities
		dc.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 1000);
		dc.setCapability(MobileCapabilityType.EVENT_TIMINGS, "true");
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		dc.setCapability("uiautomator2ServerInstallTimeout", 50000);
		//Tacx Application capabilities
		dc.setCapability("appPackage","tacx.android");
       dc.setCapability("appActivity",".ui.startup.SplashActivity");
 //     dc.setCapability(MobileCapabilityType.APP,"C:\\Users\\Ishuvir singh\\eclipse-workspace\\TacxSmokeAutomation\\src\\test\\resources\\apps\\Tacx Training_v4.5.0_apkpure.com.apk");

		
		
		try {
			URL url=new URL("http://127.0.0.1:4723/wd/hub");
			 //       driver=new AppiumDriver<MobileElement>(url,dc);
			        driver=new AndroidDriver<AndroidElement>(url,dc); 
			        
			System.out.println("Running Login screen validation Test");
	//		System.out.println("Running Login screen validation Test");
			Thread.sleep(5000);
			AndroidElement logo=(AndroidElement) driver.findElementById(ScObject.tacxLogo);
			boolean logostatus=logo.isDisplayed();
			System.out.println("Tacx logo is displayed :"+logostatus);
			
				}
				catch(Exception e) {
					System.out.println("Cause of Failure is :"+e.getCause());
					System.out.println("Failure Message is:"+e.getMessage());
					e.printStackTrace();
					
				}
	}

	@When("^User loggedin <Username> & <Password> as registered user$")
	public void user_login_with_Username_Password_registered_user() throws InterruptedException  {
	    // Write code here that turns the phrase above into concrete actions
		new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.id(ScObject.emailField)));
	    driver.findElement(By.id(ScObject.emailField)).click();
	    
	    Actions act=new Actions(driver);
	    act.sendKeys(emailID).perform();
	    //Password Field
	    driver.pressKey(new KeyEvent(AndroidKey.BACK));
	    new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.id(ScObject.passField)));
		driver.findElement(By.id(ScObject.passField)).click();
		Actions act2=new Actions(driver);
		act2.sendKeys(passWord).perform();
		Thread.sleep(3000);
		new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.id(ScObject.loginButton)));
		driver.findElement(By.id(ScObject.loginButton)).click();
		Thread.sleep(3000);
	}

	@Then("^User validate the Tacx user account$")
	public void user_validate_the_Tacx_user_account() throws Throwable {
		new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.id(ScObject.tutorialSkip)));
	    driver.findElement(By.id(ScObject.tutorialSkip)).click();
	    //Login verification 
	    new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.id(ScObject.homeTacxLogo)));
	    AndroidElement loginLogo=(AndroidElement) driver.findElementById(ScObject.homeTacxLogo);
		boolean Loginstatus=loginLogo.isDisplayed();
		System.out.println("User is successfully login :"+Loginstatus);
		new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(ScObject.newRideButton)));
	    System.out.println("New Ride tab is Displayed on Home Screen");
	}

	@Then("^User select the New Ride option$")
	public void user_select_the_New_Ride_option() throws Throwable {
		try {
		    driver.findElement(By.xpath(ScObject.newRideButton)).click();
		    Thread.sleep(3000);
		    //Clicking on search microscope bar
		    new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.className(ScObject.SearchBar)));
		    driver.findElement(By.className(ScObject.SearchBar)).click();
		    System.out.println("User clicked on Search bar to search for new location");
				
		} catch(Exception exp1) {
			System.out.println("Cause of Failure is :"+exp1.getCause());
			System.out.println("Failure Message is:"+exp1.getMessage());
			exp1.printStackTrace();
			System.out.println("Test Case caught an Exception :Failed");
			
		}

	}

	@Then("^User search <CityName> city for ride$")
	public void user_search_CityName_city_for_ride() {
		driver.findElement(By.id(ScObject.locSearch)).click();
	    Actions act3=new Actions(driver);
	    act3.sendKeys(cityName).perform();
	    System.out.println("User searched with city name as :"+cityName);
	    //Close the keyboard
	    driver.pressKey(new KeyEvent(AndroidKey.BACK));
	    new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.id(ScObject.firstsearchResult)));
	    System.out.println("Search result displayed with selected location");
	    driver.findElement(By.id(ScObject.firstsearchResult)).click();
	}

	@Then("^User Enable location service for new trip$")
	public void user_Enable_location_service_for_new_trip() {
		try {
			 //start the trip
			new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(ScObject.tripStartbutton)));
		    System.out.println("Start button is displayed");
		    driver.findElement(By.xpath(ScObject.tripStartbutton)).click();
		    System.out.println("User Selected start button :Trip Started"); 
		    System.out.println("Bluetooth is not Enabled");
		    System.out.println("Enabling bluetooth");
		    if(bluetoothReq.equalsIgnoreCase("Yes")) {
		    	new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.id(ScObject.bluetoothB)));
		    	driver.findElement(By.id(ScObject.bluetoothB)).click();
		    }
		    else {
		    	new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.id(ScObject.bluetoothSkip)));
		    	driver.findElement(By.id(ScObject.bluetoothSkip)).click();
		    	
		    }
		    System.out.println("Bluetooth is Enabled now");
		    System.out.println("Bluetooth is enabled now");
		    Thread.sleep(4000);
		    //location permission
		 AndroidElement locationPerB=(AndroidElement) driver.findElement(By.xpath(ScObject.locationB));
		    if(locationPerB.isDisplayed()){
		    	System.out.println("Permission required for location");
		    	locationPerB.click();
		    	System.out.println("Permission granted for Location");
		   }
		    Thread.sleep(3000);
		    Alert alert = driver.switchTo().alert();
		    alert.accept();
		    System.out.println("Permission granted for Location:Location Enabled");
		    }
			catch(Exception e2){
			System.out.println("Cause of Failure is :"+e2.getCause());
			System.out.println("Failure Message is:"+e2.getMessage());
			e2.printStackTrace();
			System.out.println("Test Case caught an Exception :Failed");
		}
	}

	@Then("^User starts the demo trip$")
	public void user_starts_the_demo_trip()  {
		System.out.println("Demo Trip validation Test");
		new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.id(ScObject.demolabel)));
		driver.findElement(By.id(ScObject.demolabel)).click();
		System.out.println("Demo Started by User");
		new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(ScObject.trainingB)));
		System.out.println("Training Screen is displayed");
	    driver.findElement(By.xpath(ScObject.trainingB)).click();
	    System.out.println("Training started :Awaiting new trip..");
	    new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(ScObject.tripStartB)));
	    System.out.println("Start now option is displayed to user in trip");
	    driver.findElement(By.xpath(ScObject.tripStartB)).click();
	    System.out.println("Trip Started by User");
	}

	@Then("^User validated the features in trip$")
	public void user_validated_the_features_in_trip() throws Throwable {
		System.out.println("Tacx Feature validation Test");
		try {
			Thread.sleep(5000);
			   List<AndroidElement> list=driver.findElements(By.id(ScObject.tacxIndicator));
			   int listcount=list.size();
			   for(int i=0;i<listcount;i++) {
				   if(i==0) {
				   System.out.println("Power in Watt is :"+list.get(i).getAttribute("text"));
				   System.out.println("Power in Watt is :"+list.get(i).getAttribute("text"));
			   }
				   else if(i==1) {
					   System.out.println("Percentage value is :"+list.get(i).getAttribute("text"));
					   System.out.println("Percentage value is :"+list.get(i).getAttribute("text"));
				   }
				   else if(i==2) {
					   System.out.println("Current Speed in KM/H is :"+list.get(i).getAttribute("text"));
					   System.out.println("Current Speed in KM/H is :"+list.get(i).getAttribute("text"));
				   }
				   else if(i==3) {
					   System.out.println("RPM  value is :"+list.get(i).getAttribute("text"));
					   System.out.println("RPM  value is :"+list.get(i).getAttribute("text"));
				   }
				   else if(i==4) {
					   System.out.println("BPM value is :"+list.get(i).getAttribute("text"));
					   System.out.println("BPM value is :"+list.get(i).getAttribute("text"));
				   }
				   else {
					   System.out.println("Calorie in KCAL is :"+list.get(i).getAttribute("text"));
					   System.out.println("Calorie in KCAL is :"+list.get(i).getAttribute("text"));   
				   }
			   }
			   
			  System.out.println("Tacx trip screen is displayed is validated succesfully"); 
			  new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.id(ScObject.menuIndicator)));
			  driver.findElement(By.id(ScObject.menuIndicator)).click();
			  System.out.println("Tacx Training menu is selected by User");
			  AndroidElement progressBar=(AndroidElement) driver.findElement(By.id(ScObject.progressBar));	  
			   Assert.assertTrue(progressBar.isDisplayed());
			   System.out.println("Distance progress bar is displayed");
			   System.out.println("Distance progress bar is displayed");
			   
		}
		catch(Exception exc){
			System.out.println("Cause of Failure is :"+exc.getCause());
			System.out.println("Failure Message is:"+exc.getMessage());
			exc.printStackTrace();
			System.out.println("Test Case caught an Exception :Failed");
		}

	}

	@Then("^User Stops the trip$")
	public void user_Stops_the_trip() throws Throwable {
		System.out.println("Tacx Ride stop validation Test");
		try {
			Thread.sleep(2000);
			new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(ScObject.stoptrip)));
			System.out.println("Stop button is dispalyed in Menu");
			driver.findElement(By.xpath(ScObject.stoptrip)).click();
			System.out.println("Reporter Selected the Stop trip option");
			new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(ScObject.stopPopup)));
			driver.findElement(By.xpath(ScObject.stopPopup)).click();
			}
		catch(Exception exp){
				System.out.println("Cause of Failure is :"+exp.getCause());
				System.out.println("Failure Message is:"+exp.getMessage());
				exp.printStackTrace();
				Assert.fail();
				System.out.println("Test Case caught an Exception :Failed");
			}

	}

	@Then("^User logOut of application$")
	public void user_logOut_of_application() throws Throwable {
		System.out.println("Tacx Logout Feature validation Test");
		try {
	    driver.pressKey(new KeyEvent(AndroidKey.BACK));
	    Thread.sleep(3000);
	 //   driver.pressKey(new KeyEvent(AndroidKey.BACK));
	   
	    driver.findElement(By.id(ScObject.locSearch)).clear();
	    Thread.sleep(2000);
	    driver.findElement(By.id(ScObject.SearchCross)).click();
	    new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(ScObject.SettingIcon)));
	    driver.findElement(By.xpath(ScObject.SettingIcon)).click();
	    System.out.println("User selected the setting ICON :ICON displayed");
	    System.out.println("User selected the setting ICON :ICON displayed");
	    System.out.println("Correct user account name is displayed");
	    new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(ScObject.logOutButton)));
	    driver.findElement(By.xpath(ScObject.logOutButton)).click();
	    new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(ScObject.LogoutPopup)));
	    driver.findElement(By.xpath(ScObject.LogoutPopup)).click();
	    System.out.println("User is successfully logged from account");
	    Thread.sleep(3000);
	    AndroidElement logo=(AndroidElement) driver.findElementById(ScObject.tacxLogo);
		Assert.assertTrue(logo.isDisplayed());  
		boolean logostatus=logo.isDisplayed();
		System.out.println("Tacx logo is displayed :"+logostatus);
		System.out.println("Tacx logo is displayed :"+logostatus);
		driver.quit();
		}
		catch(Exception ex) {
			System.out.println("Cause of Failure is :"+ex.getCause());
			System.out.println("Failure Message is:"+ex.getMessage());
			ex.printStackTrace();
			Assert.fail();
			System.out.println("Test Case caught an Exception :Failed");
	}
		
	}
}
