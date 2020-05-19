package tests;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.SideBar;

import static com.codeborne.selenide.Condition.text;

public class LoginProvider2 {

    protected static LoginPage login;
    protected static SideBar side;

    @DataProvider(name = "login-alerts")
    public Object[][] loginProvider() {
        return new Object[][]{
                {"gustavo@hotmail.com", "1234567", "Usuário e/ou senha inválidos"},
                {"ghm@hotmail.com", "123456", "Usuário e/ou senha inválidos"},
                {"", "123456", "Opps. Cadê o email?"},
                {"gustavo@hotmail.com", "", "Opps. Cadê a senha?"}
        };
    }

    @BeforeMethod
    public void start() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "http://ninjaplus-web:5000";

        login = new LoginPage();
        side = new SideBar();
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
