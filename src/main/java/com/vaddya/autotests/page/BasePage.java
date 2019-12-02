package com.vaddya.autotests.page;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    protected final WebDriver driver;

    protected BasePage(@NotNull final WebDriver driver) {
        this.driver = driver;

        check();
    }
    
    protected abstract void check();

    protected boolean isElementPresent(@NotNull final By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
