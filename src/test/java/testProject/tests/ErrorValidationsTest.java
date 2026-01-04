package testProject.tests;

import mainProject.pageObjects.CartPage;
import mainProject.pageObjects.ProductCatalogue;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import testProject.testComponents.BaseTest;
import testProject.testComponents.Retry;

import java.io.IOException;
import java.util.List;

public class ErrorValidationsTest extends BaseTest {

    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void errorValidationsTest() throws InterruptedException, IOException {

        //String productName = "ZARA COAT 3";
        ProductCatalogue productCatalogue = landingPage.loginApplication("prthm@gmail.com", "Prthm@608");
        Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email password.");

    }

    @Test
    public void productErrorValidation() throws InterruptedException {
        String productName = "ZARA COAT 3";
        ProductCatalogue productCatalogue = landingPage.loginApplication("prthm@gmail.com", "Prthm@6089");
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
        Assert.assertFalse(match);
    }
}
