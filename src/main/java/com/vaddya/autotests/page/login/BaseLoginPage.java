package com.vaddya.autotests.page.login;

import com.vaddya.autotests.page.BasePage;
import com.vaddya.autotests.page.HomePage;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

abstract class BaseLoginPage extends BasePage implements LoginPage {
    BaseLoginPage(@NotNull final WebDriver driver) {
        super(driver);
    }

    @NotNull
    protected abstract By emailLocator();

    @NotNull
    protected abstract By passwordLocator();

    @NotNull
    protected abstract By submitLocator();

    @Override
    protected void check() {
        Assertions.assertTrue(explicitWaitVisible(submitLocator()), "No submit button on login page!");
    }

    @Override
    @NotNull
    public LoginPage withEmail(@NotNull final String email) {
        type(emailLocator(), email);
        return this;
    }

    @Override
    @NotNull
    public LoginPage withPassword(@NotNull final String password) {
        type(passwordLocator(), password);
        return this;
    }

    @Override
    @NotNull
    public HomePage submit() {
        click(submitLocator());
        return new HomePage(driver);
    }
}
