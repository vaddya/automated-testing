package com.vaddya.autotests.page;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePage extends BasePage {
    private static final By AVATAR = By.xpath("hook_Block_Avatar");

    protected HomePage(@NotNull final WebDriver driver) {
        super(driver);
    }

    @Override
    protected void check() {
        assertTrue(isElementPresent(AVATAR));
    }
}
