package FirstLiveProject;

import Utility.UtilityClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;


public class MRS  extends UtilityClass {
    public static void main(String[] args) throws InterruptedException {

        driver.get("https://demo.openmrs.org/openmrs/login.htm");
        System.out.println("Part1 done");
        WebElement loginBox = driver.findElement(By.id("username"));
        loginBox.sendKeys("Admin");
        WebElement passcodeBox = driver.findElement(By.id("password"));
        passcodeBox.sendKeys("Admin123"+ Keys.ENTER);
        String actualErrorMessage= driver.findElement(By.id("sessionLocationError")).getText();
        String exErrorMessage= "You must choose a location!";
        if (actualErrorMessage.equals(exErrorMessage)){
            System.out.println(true);
        }else{
            System.out.println(false);
        }
        System.out.println("Part 2 Done");

        List<WebElement> locationsList = driver.findElements(By.cssSelector("#sessionLocation>li"));
        List<String> locationsNamesOnLogin = new ArrayList<>();
        List<String> locationsColorBefore = new ArrayList<>();
        for (WebElement location : locationsList) {
            locationsColorBefore.add(location.getCssValue("background-color"));
            locationsNamesOnLogin.add(location.getText());
        }
        Actions actions = new Actions(driver);
        List<String> locationsColorAfter = new ArrayList<>();
        for (WebElement location : locationsList) {
            Action hoverOverLocation = actions.moveToElement(location).build();
            hoverOverLocation.perform();
            Thread.sleep(500);
            locationsColorAfter.add(location.getCssValue("background-color"));

        }
        for (int i = 0; i < locationsColorBefore.size(); i++) {
            if (locationsColorAfter.get(i).equals(locationsColorBefore.get(i))) {

                System.out.println("Part 4: Hover all of the locations " + (i + 1) + " is fail");
            } else {
                System.out.println("Part 4: Hover all of the  locations " + (i + 1) + " is pass");
            }
        }
        locationsList.get((int)(Math.random()*6)).click();

        WebElement loginBox1 = driver.findElement(By.id("loginButton"));
        loginBox1.click();
        System.out.println("part 5  done");

        WebElement locationButton = driver.findElement(By.id("selected-location"));
        locationButton.click();
        System.out.println("part 6  done");

        List<WebElement> locationsAfterLogIn = driver.findElements(By.cssSelector(".select>li"));
        List<String> locationsNamesAfterLogin = new ArrayList<>();
        for (WebElement location:locationsAfterLogIn) {
            locationsNamesAfterLogin.add(location.getText());
        }
        int count = 1;
        for (String location:locationsNamesOnLogin) {
            if (locationsNamesAfterLogin.contains(location)){
                System.out.println("part 7 for location "+count+" pass");
                count++;
            }
        }
        Action navigateRegDesk = actions.moveToElement(locationsAfterLogIn.get(3)).build();
        navigateRegDesk.perform();
        Action navigateOther = actions.moveToElement(driver.findElement(By.id("coreapps-systemadministration-homepageLink-coreapps-systemadministration-homepageLink-extension"))).build();
        navigateOther.perform();
        WebElement adminMenu = driver.findElement(By.cssSelector(".nav-item.identifier"));

        if (adminMenu.isDisplayed()){
            System.out.println("part 8 is Passed");
        }else {
            System.out.println("part 8 is Failed");
        }

        actions.moveToElement(adminMenu).perform();
        WebElement myAccount = driver.findElement(By.partialLinkText("My Account"));
        if (myAccount.isDisplayed()){
            System.out.println("part 9 is Passed");
        }else {
            System.out.println("part 9 is Failed");
        }

        myAccount.click();
        if (driver.getTitle().equals("My Account")){
            System.out.println("part 10 is Pass");
        }else {
            System.out.println("part 10 is Fail");
        }

        WebElement languages = driver.findElement(By.cssSelector(".task>.icon-cog"));
        languages.click();
        System.out.println("part 11 is done");


        if (driver.getTitle().equals("My Languages")){
            System.out.println("part 12 is pass");
        }else {
            System.out.println("part 12 is fail");
        }


        WebElement languagesBox = driver.findElement(By.id("default-locale-field"));
        Select select = new Select(languagesBox);
        select.selectByIndex((int)(Math.random()*5)+1);
        System.out.println("part 13 is done");


        List<WebElement> languagesList = driver.findElements(By.cssSelector("input[type='checkbox']"));
        System.out.println("validation of part 14");
        for (WebElement language:languagesList) {
            language.click();
            System.out.println(language.getAttribute("value")+" language is selected: "+language.isSelected());
        }

        for (WebElement language:languagesList) {
            language.click();
            System.out.println(language.getAttribute("value")+" language is selected: "+language.isSelected());
        }

        System.out.println("validation of part 15");
        for (WebElement language:languagesList) {
            if (language.isSelected()){
                System.out.println(language.getAttribute("value")+" language is selected: "+language.isSelected());

            }else {
                language.click();
                System.out.println(language.getAttribute("value")+" language is selected: "+language.isSelected());
            }
        }
        WebElement saveButton = driver.findElement(By.cssSelector("input[type='submit']"));
        saveButton.click();
        WebElement errorMessage = driver.findElement(By.xpath("//p[text()='User defaults could not be updated.']"));

        String actualResult = errorMessage.getText();

        String expectedResult = "User defaults could not be updated.";
        if (actualResult.equals(expectedResult)){
            System.out.println("part 16 is Pass");
        }else {
            System.out.println("part 16 is Fail");
        }
        quitDriver(7);
    }
}
