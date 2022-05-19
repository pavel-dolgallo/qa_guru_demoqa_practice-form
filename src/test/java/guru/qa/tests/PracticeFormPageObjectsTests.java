package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import guru.qa.page.PracticeFormPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public class PracticeFormPageObjectsTests {

    PracticeFormPage practiceFormPage = new PracticeFormPage();

    @BeforeEach
    void openPage() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    public void fillFormTest() {

        Faker faker = new Faker(new Locale("en"));

        String firstName = faker.name().firstName(),
                lastName = faker.name().lastName(),
                fullName = format("%s %s", firstName, lastName),
                userEmail = faker.internet().emailAddress(),
                gender = "Male",
                userNumber = faker.phoneNumber().subscriberNumber(10),
                subjects = "Math",
                hobbies = "Music",
                currentAddress = faker.address().fullAddress(),
                state = "NCR",
                city = "Delhi",
                file = "picture.png",
                title = "Thanks for submitting the form";


        practiceFormPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(userEmail)
                .setGender(gender)
                .setUserNumber(userNumber)
                .setCurrentAddress(currentAddress)
                .setState(state)
                .setCity(city)
                .uploadPicture(file)
                .subjects(subjects)
                .hobbiesInput(hobbies)
                .setSubmit();


        //$("dateOfBirthInput").click();
        //$(".react-datepicker__month-select").selectOption(month);
        //$(".react-datepicker__year-select").selectOption("1989");
        //$("[aria-label$='September 28th, 1989']").click();


        //Asserts
        practiceFormPage
                .checkTitle(title)
                .checkResult("Student Name", fullName)
                .checkResult("Student Email", userEmail)
                .checkResult("Gender", gender)
                .checkResult("Mobile", userNumber)
                //.checkResult("Date of Birth", userNumber)
                .checkResult("Subjects", subjects)
                .checkResult("Hobbies", hobbies)
                .checkResult("Picture", "picture.png")
                .checkResult("Address", currentAddress)
                .checkResult("State and City", state + " " + city);

    }

}
