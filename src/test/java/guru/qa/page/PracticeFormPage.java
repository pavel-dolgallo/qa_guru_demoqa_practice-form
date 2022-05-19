package guru.qa.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {

    //locators
    SelenideElement tableResponsive = $(".table-responsive");
    SelenideElement firstNameInput = $("#firstName");
    SelenideElement lastNameInput = $("#lastName");
    SelenideElement userEmailInput = $("#userEmail");
    SelenideElement userNumberInput = $("#userNumber");
    //SelenideElement dateOfBirthInput =   $("#dateOfBirthInput");
    SelenideElement currentAddressInput = $("#currentAddress");
    SelenideElement stateInput = $("#state");
    SelenideElement cityInput = $("#city");
    SelenideElement submitInput = $("#submit");
    SelenideElement uploadPictureInput = $("#uploadPicture");
    SelenideElement subjectsInput = $("#subjectsInput");
    SelenideElement titleText = $("#example-modal-sizes-title-lg");


    //actions
    public PracticeFormPage openPage() {
        open("/automation-practice-form");

        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        return this;
    }

    public PracticeFormPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    public PracticeFormPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    public PracticeFormPage setUserEmail(String userEmail) {
        userEmailInput.setValue(userEmail);
        return this;
    }

    public PracticeFormPage setGender(String gender) {
        $(byText(gender)).click();
        return this;
    }

    public PracticeFormPage setUserNumber(String userNumber) {
        userNumberInput.setValue(userNumber);
        return this;
    }

    public PracticeFormPage setCurrentAddress(String currentAddress) {
        currentAddressInput.setValue(currentAddress);
        return this;
    }

    public PracticeFormPage setState(String state) {
        stateInput.click();
        stateInput.$(byText(state)).click();
        return this;
    }

    public PracticeFormPage setCity(String city) {
        cityInput.click();
        cityInput.$(byText(city)).click();
        return this;
    }

    public PracticeFormPage setSubmit() {
        submitInput.click();
        return this;
    }

    public PracticeFormPage uploadPicture(String file) {
        uploadPictureInput.uploadFromClasspath(file);
        return this;
    }

    public PracticeFormPage subjects(String subjects) {
        subjectsInput.setValue(subjects).pressEnter();
        return this;
    }

    public PracticeFormPage hobbiesInput(String hobbies) {
        $(byText(hobbies)).click();
        return this;
    }


    //asserts
    public PracticeFormPage checkTitle(String title) {
        titleText.shouldHave(text(title));
        return this;
    }

    public PracticeFormPage checkResult(String key, String value) {
        tableResponsive.$(byText(key)).parent().shouldHave(text(value));
        return this;
    }
}
