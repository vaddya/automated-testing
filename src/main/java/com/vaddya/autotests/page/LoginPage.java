package com.vaddya.autotests.page;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPage extends BasePage {
    private static final By EMAIL_FIELD = By.id("field_email");
    private static final By PASSWORD_FIELD = By.id("field_password");
    private static final By SUBMIT_BUTTON = By.xpath(".//*[@class='form-actions']//input");

    public LoginPage(@NotNull final WebDriver driver) {
        super(driver);
    }

    @Override
    protected void check() {
        assertTrue(isElementPresent(EMAIL_FIELD));
    }

    public LoginPage withEmail(@NotNull final String email) {
        driver.findElement(EMAIL_FIELD).sendKeys(email);
        return this;
    }

    public LoginPage withPassword(@NotNull final String password) {
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        return this;
    }

    public HomePage submit() {
        driver.findElement(SUBMIT_BUTTON).click();
        return new HomePage(driver);
    }
}