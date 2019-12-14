package com.vaddya.autotests.page;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BasePage extends BaseElement {
    protected BasePage(@NotNull final WebDriver driver) {
        super(driver);

        check();
    }

    protected abstract void check();

    protected void type(
            @NotNull final By locator,
            @NotNull final CharSequence... text) {
        type(driver, locator, text);
    }

    protected void click(@NotNull final By locator) {
        click(driver, locator);
    }

    protected void select(
            @NotNull final By locator,
            @NotNull final String value) {
        select(driver, locator, value);
    }
    
    protected boolean isElementPresent(@NotNull final By locator) {
        return isElementPresent(driver, locator);
    }
}
