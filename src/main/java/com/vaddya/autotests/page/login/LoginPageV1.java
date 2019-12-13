package com.vaddya.autotests.page.login;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

class LoginPageV1 extends BaseLoginPage {
    private static final By EMAIL_FIELD = By.id("field_email");
    private static final By PASSWORD_FIELD = By.id("field_password");
    private static final By SUBMIT_BUTTON = By.xpath(".//*[contains(@class, 'button-pro')]");

    LoginPageV1(@NotNull final WebDriver driver) {
        super(driver);
    }

    @Override
    @NotNull
    protected By emailLocator() {
        return EMAIL_FIELD;
    }

    @Override
    @NotNull
    protected By passwordLocator() {
        return PASSWORD_FIELD;
    }

    @Override
    @NotNull
    protected By submitLocator() {
        return SUBMIT_BUTTON;
    }
}