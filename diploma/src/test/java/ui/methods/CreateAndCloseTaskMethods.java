package ui.methods;

import com.codeborne.selenide.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import configs.Urls;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class CreateAndCloseTaskMethods {
    private static final String BASE_URL = Urls.getBaseUrl();
    private int taskNum;
    private static SelenideElement dropDownButton = $x("//div[@class='dropdown']/a[@class='dropdown-menu action-menu']");
    private static SelenideElement dropDownList = $("#dropdown");
    private static SelenideElement popUpWindow = $("#modal-box");
    private static SelenideElement titleNameField = $("#form-title");
    private static SelenideElement submitButton = $x("//button[@type='submit']");
    private static SelenideElement openBoardButton = $x("//a[@class='view-board']");
    private static SelenideElement closeTaskYesButton = $("#modal-confirm-button");
    private static SelenideElement closeTaskSuccessChecker = $x("//div[contains(text(), 'Task closed successfully.')]");
    private static SelenideElement successAlertWindow = $x("//div[@class='alert alert-success alert-fade-out']");

    @BeforeMethod
    public void setUp() {
        Configuration.baseUrl = BASE_URL;
    }


    public void taskCreator(String taskTitle, String boardId){
        dropDownButton.click();
        dropDownList.shouldBe(Condition.visible);
        createTaskButtonPathCreator(boardId).click();
        popUpWindow.shouldBe(Condition.visible);
        titleNameField.sendKeys(taskTitle);
        submitButton.click();
        successAlertWindow.shouldBe(Condition.visible);
        openBoardButton.click();
        taskFinderPath(taskTitle).shouldBe(Condition.visible);
        taskFinderPath(taskTitle).click();
        findCurrentTaskNum();
    }

    public void taskCloser(String taskTitle, String boardIdent){
        Selenide.open(BASE_URL + "/board/" + boardIdent);
        taskFinderPath(taskTitle).click();
        closeTaskButtonPathCreator().click();
        closeTaskYesButton.click();
        closeTaskSuccessChecker.shouldBe(Condition.visible);
    }

    private SelenideElement taskFinderPath(String taskTitle){
        return $x("//div[@class='task-board-expanded']/div['task-board-title']/a[contains(text(), '" + taskTitle + "')]");
    }

    private SelenideElement createTaskButtonPathCreator(String boardNum){
        return $("#dropdown a[href='/project/" + boardNum + "/task/create']");
    }

    private SelenideElement closeTaskButtonPathCreator(){
        findCurrentTaskNum();
        return $x("//li/a[@href='/task/" + taskNum + "/close']");
    }

    private int findCurrentTaskNum(){
        String currentUrl = WebDriverRunner.url();
        int lastSlashIndex = WebDriverRunner.url().lastIndexOf("task/");
        return this.taskNum = Integer.parseInt(currentUrl.substring(lastSlashIndex + 5));
    }

    public int getTaskNum() {
        return taskNum;
    }

    @AfterMethod(alwaysRun = true)
    public void cleanup() {
        closeWebDriver();
    }
}
