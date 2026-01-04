package testProject.tests;

import mainProject.pageObjects.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testProject.testComponents.BaseTest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    //String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String, String> input) throws InterruptedException {

        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
        List<WebElement> elements = productCatalogue.getProductList();
        productCatalogue.addProductToCart(input.get("product"));
        CartPage cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.verifyProductDisplay(input.get("product"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("India");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));

    }

    @Test(dependsOnMethods = {"submitOrder"}, dataProvider = "getData")
    public void orderHistoryTest(HashMap<String, String> input)
    {
        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
        OrderPage orderPage = productCatalogue.goToOrderPage();
        Assert.assertTrue(orderPage.verifyOrderDisplay(input.get("product")));
    }

    @DataProvider
    public Object[][] getData() throws IOException
    {
        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\testProject\\data\\PurchaseOrder.json");
        return new Object[][] {{data.get(0)},{data.get(1)}};
    }

//        HashMap<String, String> map = new HashMap<>();
//        map.put("email", "prthm@gmail.com");
//        map.put("password", "Prthm@6089");
//        map.put("product", "ZARA COAT 3");
//
//        HashMap<String, String> map1 = new HashMap<>();
//        map1.put("email", "miraje@gmail.com");
//        map1.put("password", "Miraje@6089");
//        map1.put("product", "ADIDAS ORIGINAL");

}
