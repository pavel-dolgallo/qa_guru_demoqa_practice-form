package guru.qa;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PracticeFormTests {

    @BeforeEach
    void openPage () {
        Selenide.open("https://demoqa.com/automation-practice-form");
    }


    @Test
    void assertTest (){

    }

}
