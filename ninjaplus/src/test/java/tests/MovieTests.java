package tests;

import common.BaseTest;
import models.MovieModel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.text;

public class MovieTests extends BaseTest {

    @BeforeMethod
    public void setup() {
        login
                .open()
                .with("gustavo@hotmail.com", "123456");

        side.loggerUser().shouldHave(text("Gustavo"));
    }

    @Test
    public void shoudlRegistreANewMovie() {

        MovieModel movieData = new MovieModel(
                "Jumanji - Próxima fase",
                "Pré-venda",
                2020,
                "16/01/2020",
                Arrays.asList("The Rock", "Jack Black", " Kevin Hart", "Karen Gillan", "Danny DeVito"),
                "Tentado a revisitar o mundo de Jumanji, Spencer decide "
                        + "consertar o bug do jogo que permite que sejam transportados de volta ao game.",
                "jumanji2.jpg"
        );

        movie.add().create(movieData);
    }
}
