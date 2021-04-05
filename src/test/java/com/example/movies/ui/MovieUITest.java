package com.example.movies.ui;


import com.example.movies.dataFactory.Creator;
import com.example.movies.repository.FilmRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Import(Creator.class)
public class MovieUITest {

    private WebDriver driver;

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    Creator creator;

    @BeforeAll
    public static void setupWebdriverChromeDriver() {
        String chromeDriverPath = "C:\\Users\\lukas\\Desktop\\INPIA - WWW\\movies\\src\\test\\resources\\chromedriver.exe"; //TODO
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
    }

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        filmRepository.deleteAll();
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void verifyMovieCreation() {
        createMovieTest();
    }

    @Test
    public void verifyMovieCreation2() {
        createMovieTest();
    }


    private void createMovieTest() {
        driver.get("http://localhost:8080/movie-form");

        driver.findElement(By.id("title")).sendKeys("NewMovieSelenium");
        driver.findElement(By.id("year")).sendKeys("2000");
        driver.findElement(By.id("length")).sendKeys("123");

        driver.findElement(By.xpath("//input[@type='submit']")).click();

        Assert.assertEquals(1, driver.findElements(By.xpath("//h3[text()='NewMovieSelenium']")).size());
    }
}