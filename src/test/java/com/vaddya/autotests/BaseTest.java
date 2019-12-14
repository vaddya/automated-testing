package com.vaddya.autotests;

import com.vaddya.autotests.page.HomePage;
import com.vaddya.autotests.page.login.LoginPage;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class BaseTest {
    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);
    private static final String BASE_URL = "https://ok.ru";
    private static final String EMAIL = "TechoBot4";
    private static final String PASSWORD = "TechnoPolis19";

    @BeforeEach
    void init() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
    }

    @AfterEach
    void shutdown() {
        driver.close();
    }

    private WebDriver driver;

    @NotNull
    HomePage doLogin(
            @NotNull final String email,
            @NotNull final String password) {
        log.info("Logging in using {} and {}", email, password);
        return LoginPage.create(driver)
                .withEmail(email)
                .withPassword(password)
                .submit();
    }

    @NotNull
    HomePage doLogin() {
        return doLogin(EMAIL, PASSWORD);
    }
}
