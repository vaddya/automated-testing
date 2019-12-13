package com.vaddya.autotests.page.search.music;

import com.vaddya.autotests.page.search.BaseSearchPage;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TrackSearchPage extends BaseSearchPage<TrackSearchCard> {
    TrackSearchPage(@NotNull final WebDriver driver) {
        super(driver);
        waitSearch();
    }

    @NotNull
    @Override
    protected TrackSearchCard wrapElement(@NotNull final WebElement element) {
        return new TrackSearchCard(driver, element);
    }
}
