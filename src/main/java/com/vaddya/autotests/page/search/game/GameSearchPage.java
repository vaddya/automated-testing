package com.vaddya.autotests.page.search.game;

import com.vaddya.autotests.page.search.BaseSearchPage;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GameSearchPage extends BaseSearchPage<GameSearchCard> {
    public GameSearchPage(@NotNull WebDriver driver) {
        super(driver);
    }

    @Override
    @NotNull
    protected GameSearchCard wrapElement(@NotNull final WebElement element) {
        return new GameSearchCard(driver, element);
    }
}
