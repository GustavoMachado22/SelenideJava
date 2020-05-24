package tests;

import static com.codeborne.selenide.Condition.text;
import common.BaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginProvider2 extends BaseTest {

    @DataProvider(name = "login-alerts")
    public Object[][] loginProvider() {
        return new Object[][]{
                {"gustavo@hotmail.com", "1234567", "Usuário e/ou senha inválidos"},
                {"ghm@hotmail.com", "123456", "Usuário e/ou senha inválidos"},
                {"", "123456", "Opps. Cadê o email?"},
                {"gustavo@hotmail.com", "", "Opps. Cadê a senha"}
        };
    }

    @Test
    public void shouldSeeLoggerdUser() {

        login
                .open()
                .with("gustavo@hotmail.com", "123456");

        side.loggerUser().shouldHave(text("Gustavo"));

    }


    // DDT (DATA DRIVEN TESTING)
    @Test(dataProvider = "login-alerts")
    public void shouldSeeLoginAlerts(String email, String pass, String expectAlert) {
        login
                .open()
                .with(email, pass)
                .alert().shouldHave(text(expectAlert));
    }

    @AfterMethod
    public void cleanup() {
        login.clearSession();
    }
}