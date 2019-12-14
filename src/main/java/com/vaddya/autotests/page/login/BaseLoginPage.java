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

    @Override
    protected void check() {
        Assertions.assertTrue(explicitWaitVisible(submitLocator()), "No submit button on login page!");
    }

    @Override
    @NotNull
    public LoginPage withEmail(@NotNull final String email) {
        driver.findElement(emailLocator()).sendKeys(email);
        return this;
    }

    @Override
    @NotNull
    public LoginPage withPassword(@NotNull final String password) {
        driver.findElement(passwordLocator()).sendKeys(password);
        return this;
    }

    @Override
    @NotNull
    public HomePage submit() {
        driver.findElement(submitLocator()).click();
        return new HomePage(driver);
    }

    @NotNull
    abstract protected By emailLocator();

    @NotNull
    abstract protected By passwordLocator();

    @NotNull
    abstract protected By submitLocator();
}
