package mainProject.pageObjects;

import mainProject.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css=".totalRow button")
    WebElement checkoutEle;

    @FindBy(css=".cartSection h3")
    List<WebElement> productTiles;

    public Boolean verifyProductDisplay(String productName)
    {
        return productTiles.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
    }

    public CheckoutPage goToCheckout()
    {
        checkoutEle.click();
        return new CheckoutPage(driver);
    }
}
