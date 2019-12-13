package com.vaddya.autotests.page;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GamePage extends BaseElement {
    private static final By GAME_SCREEN = By.id("appMainFullScreen");

    public GamePage(@NotNull final WebDriver driver) {
        super(driver);
    }

    @Override
    protected void check() {
        Assertions.assertTrue(explicitWaitVisible(GAME_SCREEN), "Main screen is not found on a game page!");
    }
}
