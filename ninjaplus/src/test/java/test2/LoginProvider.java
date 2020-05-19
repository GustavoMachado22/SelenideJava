package test2;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.isChrome;

public class LoginProvider {


    @DataProvider(name = "login-alerts")
    public Object[][] loginProvider() {
        return new Object[][]{
                {"gustavo@hotmail.com", "1234567", "Usuário e/ou senha inválidos"},
                {"ghm@hotmail.com", "123456", "Usuário e/ou senha inválidos"},
                {"", "123456", "Opps. Cadê o email?"},
                {"gustavo@hotmail.com", "", "Opps. Cadê a senha?"}
        };

    }

    @Test
    public void shouldSeeLoggerdUser() {
        isChrome();
        open("http://ninjaplus-web:5000/");

        $("input[name=email]").setValue("gustavo@hotmail.com");
        $("#passId").setValue("123456");
        $(byText("Entrar")).click();
        $(".user .info span").shouldHave(text("Gustavo"));

    }

    // DDT (DATA DRIVEN TESTING)
    @Test(dataProvider = "login-alerts")
    public void shouldSeeLoginAlerts(String email, String pass, String expectAlert) {
        isChrome();

        executeJavaScript("localStorage.clear();");
        open("http://ninjaplus-web:5000/");

        $("input[name=email]").setValue(email);
        $("#passId").setValue(pass);
        $(byText("Entrar")).click();

        $(".alert span").shouldHave(text(expectAlert));

    }

}
