package guru.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PracticeFormTests {

    @BeforeEach
    void openPage() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void fillFormTest() {

        String firstName = "Ivan";
        String lastName = "Ivanov";
        String userEmail = "IvanIvanov@mail.com";
        String gender = "Male";
        String userNumber = "1234567890";
        String subjectsInput = "Math";
        String hobbies = "Music";
        String currentAddress = "Saint-P, Nevskiy, 1";
        String state = "NCR";
        String city = "Delhi";
        SelenideElement tableResponsive = $(".table-responsive");

        open("/automation-practice-form");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $(byText(gender)).click();
        $("#userNumber").setValue(userNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("September");
        $(".react-datepicker__year-select").selectOption("1989");
        $("[aria-label$='September 28th, 1989']").click();
        $("#subjectsInput").setValue(subjectsInput).pressEnter();
        $(byText(hobbies)).click();
        $("#uploadPicture").uploadFromClasspath("picture.png");
        $("#currentAddress").setValue(currentAddress);
        $("#state").click();
        $("#state").$(byText(state)).click();
        $("#city").click();
        $("#city").$(byText(city)).click();
        $("#submit").click();

        //Asserts
        $("#example-modal-sizes-title-lg").$(byText("Thanks for submitting the form"));
        $(tableResponsive).$(byText("Student Name")).parent().shouldHave(text(firstName+ " "+lastName));
        $(tableResponsive).$(byText("Student Email")).parent().shouldHave(text(userEmail));
        $(tableResponsive).$(byText("Gender")).parent().shouldHave(text(gender));
        $(tableResponsive).$(byText("Mobile")).parent().shouldHave(text(userNumber));
        $(tableResponsive).$(byText("Date of Birth")).parent().shouldHave(text("28 September,1989"));
        $(tableResponsive).$(byText("Subjects")).parent().shouldHave(text(subjectsInput));
        $(tableResponsive).$(byText("Hobbies")).parent().shouldHave(text(hobbies));
        $(tableResponsive).$(byText("Picture")).parent().shouldHave(text("picture.png"));
        $(tableResponsive).$(byText("Address")).parent().shouldHave(text(currentAddress));
        $(tableResponsive).$(byText("State and City")).parent().shouldHave(text(state + " " + city));
    }

}
