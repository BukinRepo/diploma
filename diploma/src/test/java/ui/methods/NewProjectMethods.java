package ui.methods;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.RandomGenerator;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class NewProjectMethods {
    private static final String BASE_URL = "http://127.0.0.1";
    private static String projectName;
    private static SelenideElement dashboardPage = $("#main");
    private static SelenideElement createProjectPersonalButton = $x("//div[@class='page-header']/ul/li/a[@href='/project/create/personal']");

    private static SelenideElement createProjectButton = $x("//div[@class='page-header']/ul/li/a[@href='/project/create']");
    private static SelenideElement createProjectPopUp = $x("//div[@class = 'page-header']/h2");
    private static SelenideElement projectNameForm = $("#form-name");
    private static SelenideElement submitButton = $x("//button[@type='submit']");

    @BeforeMethod
    public void setUp() {
        Configuration.baseUrl = BASE_URL;
    }
    public void projectCreator(Boolean isPersonal){
        RandomGenerator generator = new RandomGenerator();
        this.projectName = generator.stringGen(8);
        Assert.assertTrue(dashboardPage.exists(), "Looks like you aren't logged");
        if (isPersonal) {
            createProjectPersonalButton.click();
        } else {
            createProjectButton.click();
        }
        createProjectPopUp.shouldBe(Condition.visible);
        projectNameForm.shouldBe(Condition.visible).sendKeys(projectName);
        submitButton.click();

    }

    @AfterMethod(alwaysRun = true)
    public void cleanup() {
        closeWebDriver();
    }

    public void projectCreateChecker(String projName){
        SelenideElement projectNamePath = $x("//title[contains(text(), '" + projName + "')]");
//        Assert.assertTrue(projectNamePath.exists(), "Something went wrong. Please try again later");
        projectNamePath.shouldBe(Condition.exist);
    }

    public static String getProjectName() {
        return projectName;
    }
}
