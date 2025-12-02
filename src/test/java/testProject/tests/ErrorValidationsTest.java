package testProject.tests;

import mainProject.pageObjects.ProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.Test;
import testProject.testComponents.BaseTest;

import java.io.IOException;

public class ErrorValidationsTest extends BaseTest {

    @Test(groups = {"ErrorHandling"})
    public void submitOrder() throws InterruptedException, IOException {

        //String productName = "ZARA COAT 3";
        ProductCatalogue productCatalogue = landingPage.loginApplication("prthm@gmail.com", "Prthm@608");
        Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");

    }
}
