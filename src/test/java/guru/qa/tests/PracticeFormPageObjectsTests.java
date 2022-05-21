package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import guru.qa.pages.PracticeFormPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

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
                day = "28",
                month = "September",
                year = "1989",
                dataBirth = format("%s %s,%s", day, month, year),
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
                .setBirthDate(day, month, year)
                .setCurrentAddress(currentAddress)
                .setState(state)
                .setCity(city)
                .uploadPicture(file)
                .subjects(subjects)
                .hobbiesInput(hobbies)
                .setSubmit();

        //Asserts
        practiceFormPage
                .checkTitle(title)
                .checkResult("Student Name", fullName)
                .checkResult("Student Email", userEmail)
                .checkResult("Gender", gender)
                .checkResult("Mobile", userNumber)
                .checkResult("Date of Birth", dataBirth)
                .checkResult("Subjects", subjects)
                .checkResult("Hobbies", hobbies)
                .checkResult("Picture", "picture.png")
                .checkResult("Address", currentAddress)
                .checkResult("State and City", state + " " + city);

    }
}
