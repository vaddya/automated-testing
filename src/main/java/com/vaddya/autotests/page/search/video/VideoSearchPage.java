package com.vaddya.autotests.page.search.video;

import com.vaddya.autotests.page.search.BaseSearchPage;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class VideoSearchPage extends BaseSearchPage<VideoSearchCard> {
    private static final By STUB = By.xpath(".//div[contains(@class, 'stub-empty')]");
    private static final By LONG_DURATION = By.id("vlnLONGSpan");
    private static final By HIGH_QUALITY = By.id("field_vqt");

    public VideoSearchPage(@NotNull final WebDriver driver) {
        super(driver);
    }

    @NotNull
    @Override
    protected VideoSearchCard wrapElement(@NotNull final WebElement element) {
        return new VideoSearchCard(driver, element);
    }

    public boolean isStub() {
        return isElementPresent(STUB);
    }

    public VideoSearchPage withLongDuration() {
        click(LONG_DURATION);
        return this;
    }

    public VideoSearchPage withHighQuality() {
        click(HIGH_QUALITY);
        return this;
    }
}
