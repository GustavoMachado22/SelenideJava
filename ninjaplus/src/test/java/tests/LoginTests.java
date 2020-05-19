package tests;

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

    @Test
    public void IncorrectPassord(){
        isChrome();
        open("http://ninjaplus-web:5000/");

        $("input[name=email]").setValue("gustavo@hotmail.com");
        $("#passId").setValue("1234567");
        $(byText("Entrar")).click();
        $(".alert span").shouldHave(text("Usuário e/ou senha inválidos"));

    }

    @Test
    public void UserNotFound(){
        isChrome();
        open("http://ninjaplus-web:5000/");

        $("input[name=email]").setValue("ghm@hotmail.com");
        $("#passId").setValue("123456");
        $(byText("Entrar")).click();
        $(".alert span").shouldHave(text("Usuário e/ou senha inválidos"));

    }
    @Test
    public void EmailRequided(){
        isChrome();
        open("http://ninjaplus-web:5000/");

        $("#passId").setValue("123456");
        $(byText("Entrar")).click();
        $(".alert span").shouldHave(text("Opps. Cadê o email?"));
    }


    @Test
    public void PasswordRequided(){
        isChrome();
        open("http://ninjaplus-web:5000/");

        $("input[name=email]").setValue("ghm@hotmail.com");
        $(byText("Entrar")).click();
        $(".alert span").shouldHave(text("Opps. Cadê a senha?"));

    }

}
