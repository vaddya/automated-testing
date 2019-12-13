package com.vaddya.autotests.page.search.music;

import com.vaddya.autotests.page.BasePage;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MusicSearchPage extends BasePage {
    private static final By CATEGORY_LIST = By.id("musicType");
    private static final By CATEGORY_TRACKS = By.xpath(".//div[@data-field_mmode='Track']");

    public MusicSearchPage(@NotNull final WebDriver driver) {
        super(driver);
    }

    @Override
    protected void check() {
        Assertions.assertTrue(explicitWaitVisible(CATEGORY_LIST), "No category list on the music page!");
        Assertions.assertTrue(explicitWaitVisible(CATEGORY_TRACKS), "No tracks category on the music page!");
    }

    @NotNull
    public TrackSearchPage toTracks() {
        click(CATEGORY_TRACKS);
        return new TrackSearchPage(driver);
    }
}
