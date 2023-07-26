package ui.methods;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import configs.Urls;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class AddCommentInTaskMethods {
    private static final String BASE_URL = Urls.getBaseUrl();
    private static SelenideElement commentWindow = $x("//div[@id='modal-content']");
    private static SelenideElement coomentField = $x("//div[@id='modal-content']//textarea[@name='comment']");
    private static SelenideElement submitButton = $x("//div[@id='modal-content']//button[@type='submit']");
    private static SelenideElement commentAddedSuccessfullyChecker = $x("//div[contains(text(), 'Comment added successfully.')]");

    @BeforeMethod
    public void setUp() {
        Configuration.baseUrl = BASE_URL;
    }

    public void addComment(String boardIdent, String taskTitle, int taskNum, String comment){
        Selenide.open(BASE_URL + "/board/" + boardIdent);
        taskFinderPath(taskTitle).click();
        addCommentButtonPathCreator(taskNum).click();
        commentWindow.shouldBe(Condition.visible);
        coomentField.sendKeys(comment);
        submitButton.click();
        commentAddedSuccessfullyChecker.shouldBe(Condition.visible);
    }

    private SelenideElement taskFinderPath(String taskTitle){
        return $x("//div[@class='task-board-expanded']/div['task-board-title']/a[contains(text(), '" + taskTitle + "')]");
    }

    private SelenideElement addCommentButtonPathCreator(int taskNum){
        return $x("//li/a[@href='/task/" + taskNum + "/comment/create']");
    }

    @AfterMethod(alwaysRun = true)
    public void cleanup() {
        closeWebDriver();
    }
}
