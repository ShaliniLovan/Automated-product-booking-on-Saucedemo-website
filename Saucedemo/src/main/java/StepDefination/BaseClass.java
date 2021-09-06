package StepDefination;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class BaseClass {
    WebDriver driver;

    @Given("browser and open {string}")
    public void browser_and_open(String string) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\shlovan\\Downloads\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(string);

    }

    @When("user enter Login ID and Password")
    public void user_enter_login_id_and_password() throws InterruptedException {

        String Username = "standard_user";
        String Password = "secret_sauce";

        WebElement user = driver.findElement(By.xpath("//input[@id='user-name']"));
        user.sendKeys(Username);

        WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
        password.sendKeys(Password);
        Thread.sleep(1000);

    }

    @Then("click on login")
    public void click_on_login() {
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
    }

    @Then("find Most Expensive Product")
    public void find_most_expensive_product() throws InterruptedException {
        WebElement expensive = driver.findElement(By.xpath("//select[@class=\"product_sort_container\"]"));
        Select slt = new Select(expensive);
        slt.selectByVisibleText("Price (high to low)");

        List<WebElement> products = driver.findElements(By.xpath("//div[@class='inventory_item_name']"));
        String Pname = products.get(1).getText();
        System.out.println("Most Expensive Product" + Pname);
        Thread.sleep(1000);
    }

    @Then("add to cart if value is less than {int}")
    public void add_to_cart_if_value_is_less_than(Integer int1) throws InterruptedException {
        By add_to_cart = By.xpath("//button[@id=\"add-to-cart-sauce-labs-fleece-jacket\"]");
        WebElement cart = driver.findElement(add_to_cart);

        List<WebElement> item_price = driver.findElements(By.xpath("//div[@class=\"inventory_item_price\"]"));
        String Price = item_price.get(0).getText();
        Price = Price.replace("$", " ");
        Double num = Double.parseDouble(Price);
        if (num > int1) {
            driver.close();
        }
        cart.click();
        Thread.sleep(1000);
    }

    @Then("Check if the REMOVE option is enabled")
    public void Check_if_the_REMOVE_option_is_enabled() {
        By remove_from_cart = (By.xpath("//button[@id=\"remove-sauce-labs-fleece-jacket\"]"));
        WebElement remove = driver.findElement(remove_from_cart);
        remove.click();
    }

    @And("again click on the same Add to Cart")
    public void again_click_on_the_same_Add_to_Cart() {
        By add_to_cart = By.xpath("//button[@id=\"add-to-cart-sauce-labs-fleece-jacket\"]");
        WebElement cart = driver.findElement(add_to_cart);
        cart.click();

    }

    @Then("Click on the CART icon")
    public void Click_on_the_CART_icon() {
        driver.findElement(By.xpath("//a[@class=\"shopping_cart_link\"]")).click();
    }

    @And("Click on Continue Shopping")
    public void Click_on_Continue_Shopping()
    {
        By continue_shop = (By.xpath("//button[@id=\"continue-shopping\"]"));
        WebElement click_continue = driver.findElement(continue_shop);
        click_continue.click();
    }

    @Then("find Least Expensive Product")
    public void find_least_expensive_product() throws InterruptedException {
        //before cart value
        String beforeCartValue = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText();
        int cartValue1 = Integer.parseInt(beforeCartValue);

        //select least product
        WebElement leastExpensive = driver.findElement(By.xpath("//select[@class=\"product_sort_container\"]"));
        Select select = new Select(leastExpensive);
        select.selectByVisibleText("Price (low to high)");
        //Thread.sleep(1000);

        //add the least value product to cart
        driver.findElement(By.xpath("//button[@name='add-to-cart-sauce-labs-onesie']")).click();
        // Thread.sleep(1000);

        //after cart value
        String afterCartValue = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText();
        int cartValue2 = Integer.parseInt(afterCartValue);

        if (cartValue2 == (cartValue1 + 1))
        {

            //click on cart icon
            Click_on_the_CART_icon();

        }
        else
        {   //refresh current page
            driver.navigate().refresh();
        }

        Thread.sleep(3000);
    }

    @Then("Verify Cart items Value with the items present in previous page")
    public void verify_cart_items_value_with_the_items_present_in_previous_page() {
        String newCartValue = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText();
        int newCartValue1 = Integer.parseInt(newCartValue);

        List<WebElement> item_qnt = driver.findElements(By.xpath("//div[@class='cart_quantity']"));
        if (newCartValue1 == item_qnt.size()) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,350)", "");
        } else {
            System.out.println("Item quantity and cart value are not same");
        }

    }

    @Then("click on Checkout")
    public void click_on_checkout() {
        By checkout = (By.xpath("//button[@id=\"checkout\"]"));
        WebElement checkOut = driver.findElement(checkout);
        checkOut.click();

    }
    @Then("Input the required details on CHECKOUT")
    public void input_the_required_details_on_checkout() {
        String firstName="Shalini";
        String lastName="Lovan";
        String postalCode="123456";

        By firstname= (By.xpath("//input[@id='first-name']"));
        WebElement ele1=driver.findElement(firstname);
        ele1.sendKeys(firstName);

        By Lastname=By.xpath("//input[@id='last-name']");
        WebElement ele2=driver.findElement(Lastname);
        ele2.sendKeys(lastName);

        By PostalCode=By.xpath("//input[@id='postal-code']");
        WebElement e1e3=driver.findElement(PostalCode);
        e1e3.sendKeys(postalCode);

        //click on continue
        By continues=(By.xpath("//input[@id=\"continue\"]"));
        WebElement cont=driver.findElement(continues);
        cont.click();

    }
    @Then("Verify the total amount displayed on CHECKOUT")
    public void verify_the_total_amount_displayed_on_checkout() {
        List<WebElement> prices = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));

        double total_item_value =0;

        for(WebElement p:prices)
        {
            String price_value = p.getText().substring(1);

            double value_float = Double.parseDouble(price_value);
            total_item_value=total_item_value+value_float;

        }

        WebElement itemTotal = driver.findElement(By.xpath("//div[@class='summary_subtotal_label']"));

        int subtotal = itemTotal.getText().indexOf('$');
        String total_items_price = itemTotal.getText().substring(subtotal+1);

        if(Double.parseDouble(total_items_price)==total_item_value)
        {
            System.out.println("Pass");
        }
        else
        {
            System.out.println("Fail");
        }

    }
    @Then("Click on FINISH and Verify the Success message")
    public void click_on_finish_and_verify_the_success_message() {
        driver.findElement(By.xpath("//button[@id='finish']")).click();

        String ActualMessage = driver.findElement(By.xpath("//h2[@class='complete-header']")).getText();
        String ExpectedMessage = "THANK YOU FOR YOUR ORDER";

        if (ActualMessage.equals(ExpectedMessage)) {
            System.out.println("THANK YOU FOR YOUR ORDER");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,-150)", "");
    }
        else
            System.out.println("Messages are not matching");

    }

}