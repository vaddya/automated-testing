package com.vaddya.autotests;

import com.vaddya.autotests.page.HomePage;
import com.vaddya.autotests.page.login.LoginPage;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

abstract class BaseTest {
    public static final String BASE_URL = "https://ok.ru";
    public static final String EMAIL = "TechoBot4";
    public static final String PASSWORD = "TechnoPolis19";

    @BeforeEach
    void init() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
    }

    @AfterEach
    void shutdown() {
        driver.close();
    }

    protected WebDriver driver;

    @NotNull
    protected HomePage doLogin(
            @NotNull final String email,
            @NotNull final String password) {
        return LoginPage.create(driver)
                .withEmail(email)
                .withPassword(password)
                .submit();
    }

    @NotNull
    protected HomePage doLogin() {
        return doLogin(EMAIL, PASSWORD);
    }
}
