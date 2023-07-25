package ui.methods;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.RandomGenerator;
import configs.Urls;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class NewProjectMethods {
    private static final String BASE_URL = Urls.getBaseUrl();
    private static String projectName;
    private static String projectId;
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
    public void projectCreator(Boolean isUsualUser){
        RandomGenerator generator = new RandomGenerator();
        this.projectName = generator.stringGen(8);
        Assert.assertTrue(dashboardPage.exists(), "Looks like you aren't logged");
        if (isUsualUser) {
            createProjectPersonalButton.click();
        } else {
            createProjectButton.click();
        }
        createProjectPopUp.shouldBe(Condition.visible);
        projectNameForm.shouldBe(Condition.visible).sendKeys(projectName);
        submitButton.click();
        projectCreateChecker();
        findBoardNum();
    }

    @AfterMethod(alwaysRun = true)
    public void cleanup() {
        closeWebDriver();
    }

    public void projectCreateChecker(){
        SelenideElement projectNamePath = $x("//title[contains(text(), '" + projectName + "')]");
        projectNamePath.shouldBe(Condition.exist);
    }

    private String findBoardNum(){
        String currentUrl = WebDriverRunner.url();
        int lastSlashIndex = WebDriverRunner.url().lastIndexOf("project/");
        return projectId = currentUrl.substring(lastSlashIndex + 8);
    }

    public static String getProjectId() {
        return projectId;
    }
}
