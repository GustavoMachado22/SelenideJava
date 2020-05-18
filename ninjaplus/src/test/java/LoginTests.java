import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.isChrome;

public class LoginTests {

    @Test
    public void shouldSeeLoggerdUser(){
        isChrome();
        open("http://ninjaplus-web:5000/");

        $("input[name=email]").setValue("gustavo@hotmail.com");
        $("#passId").setValue("123456");
        $(byText("Entrar")).click();
        $(".user .info span").shouldHave(text("Gustavo"));

    }

}
