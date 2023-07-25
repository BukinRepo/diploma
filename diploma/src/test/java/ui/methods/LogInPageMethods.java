package ui.methods;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import configs.Urls;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class LogInPageMethods {
    private static final String BASE_URL = Urls.getBaseUrl();
    private static SelenideElement dashboardPage = $("#main");
    private static SelenideElement loginError = $x("//div/p[@class='alert alert-error']");
    private static SelenideElement usernameForm = $("#form-username");
    private static SelenideElement passwordForm = $("#form-password");
    private static SelenideElement submitButton = $x("//button[@type='submit']");

    @BeforeMethod
    public void setUp() {
        Configuration.baseUrl = BASE_URL;
    }

    private void logInPageOpener(){
        Selenide.open(BASE_URL);
    }

    public void logInByUser(String username, String password){
        logInPageOpener();
        usernameForm.shouldBe(Condition.visible).sendKeys(username);
        passwordForm.shouldBe(Condition.visible).sendKeys(password);
        submitButton.click();
//        dashboardPage.shouldBe(Condition.visible);
//        Assert.assertTrue(dashboardPage.exists());
    }



    @AfterMethod(alwaysRun = true)
    public void cleanup() {
        closeWebDriver();
    }

    public static SelenideElement getDashboardPage() {
        return dashboardPage;
    }

    public static SelenideElement getLoginError() {
        return loginError;
    }

    public void successLogin(){
        getDashboardPage().shouldBe(Condition.visible);
        Assert.assertTrue(getDashboardPage().exists());
    }

    public void checkLoginError(){
        getLoginError().shouldBe(Condition.visible);
        Assert.assertTrue(getLoginError().exists());
    }
}
