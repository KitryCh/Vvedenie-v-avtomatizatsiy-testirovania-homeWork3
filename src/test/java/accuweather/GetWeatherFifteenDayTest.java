package accuweather;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.example.accuweather.weather.DailyForecast;
import org.example.accuweather.weather.Weather;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetWeatherFifteenDayTest extends AccuweatherAbstractTest {

    @Test
    void getWeatherFifteenDay_shouldReturn() {

        //тест будет падать, т.к. запрос за 15 дней будет возвращать 401 код ответа, у нас ограничен досутп по нащему токену и погоды за 15 дней получить не сможете
        Weather response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/forecasts/v1/daily/15day/306729")
                .then()
                .statusCode(401)
                .time(Matchers.lessThan(2000l))
                .extract()
                .response()
                .body().as(Weather.class);

        Assertions.assertEquals(15,response.getDailyForecasts().size());
        Assertions.assertNotNull(response.getHeadline());
    }

    @Test
    void getDailyForecastsList() {

        List<DailyForecast> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/forecasts/v1/daily/15day/306729")
                .then()
                .statusCode(401)
                .time(Matchers.lessThan(2000l))
                .extract()
                .response()
                .body().jsonPath().getList("DailyForecasts", DailyForecast.class);
        //тест будет падать, т.к. запрос за 15 дней будет возвращать 401 код ответа, у нас ограничен досутп по нащему токену и погоды за 15 дней получить не сможете
        Assertions.assertEquals(15, response.size());
    }

    @Test
    void getString() {
        //тест будет падать, т.к. запрос за 15 дней будет возвращать 401 код ответа, у нас ограничен досутп по нащему токену и погоды за 15 дней получить не сможете
        String response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/forecasts/v1/daily/15day/306729")
                .then()
                .statusCode(401)
                .time(Matchers.lessThan(2000l))
                .extract().asString();
        //тест будет падать, т.к. запрос за 15 дней будет возвращать 401 код ответа, у нас ограничен досутп по нащему токену и погоды за 15 дней получить не сможете
        Assertions.assertTrue(response.contains("Headline"));
        Assertions.assertTrue(response.contains("DailyForecasts"));
    }
}
