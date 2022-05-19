package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public class PracticeFormTests {

    @BeforeEach
    void openPage() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void fillFormTest() {

        Faker faker = new Faker(new Locale("en"));

        String firstName = faker.name().firstName(),
                lastName = faker.name().lastName(),
                fullName = format("%s %s", firstName, lastName ),
                userEmail = faker.internet().emailAddress(),
                gender = "Male",
                userNumber = faker.phoneNumber().subscriberNumber(10),
                subjectsInput = "Math",
                hobbies = "Music",
                currentAddress = faker.address().fullAddress(),
                state = "NCR",
                city = "Delhi",
                month = "September";

        SelenideElement tableResponsive = $(".table-responsive");

        open("/automation-practice-form");

        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");


        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $(byText(gender)).click();
        $("#userNumber").setValue(userNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
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
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(tableResponsive).$(byText("Student Name")).parent().shouldHave(text(fullName));
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
