package mainProject.pageObjects;

import mainProject.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Objects;

public class CheckoutPage extends AbstractComponent {

    WebDriver driver;
    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css="input[placeholder='Select Country']")
    WebElement searchCountry;

    @FindBy(css="div[class='actions'] a")
    WebElement submit;

    By result = By.cssSelector("div[class='form-group'] button");

    public void selectCountry(String countryName)
    {
        Actions a = new Actions(driver);
        a.sendKeys(searchCountry, countryName).build().perform();

        waitForElementToAppear(result);
        List<WebElement> countryList = driver.findElements(result);

        Objects.requireNonNull(countryList.stream().filter(option ->
                option.findElement(By.cssSelector("span")).getText().equals(countryName)).findFirst().orElse(null)).click();

    }

    public ConfirmationPage submitOrder()
    {
        submit.click();
        return new ConfirmationPage(driver);
    }
}
