package com.vaddya.autotests.page.search.music;

import com.vaddya.autotests.page.search.BaseSearchCard;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrackSearchCard extends BaseSearchCard {
    private static final Logger log = LoggerFactory.getLogger(TrackSearchCard.class);
    private static final By ARTIST = By.xpath(".//a[contains(@class, 'mus-tr_artist')]");
    private static final By PLAY = By.xpath(".//span[contains(@class, '__play')]");
    private static final By PAUSE = By.xpath(".//span[contains(@class, '__pause')]");
    private static final String TRACK_ID_ATTRIBUTE = "data-track-id";

    TrackSearchCard(
            @NotNull final WebDriver driver,
            @NotNull final WebElement element) {
        super(driver, element);
    }

    @Override
    @NotNull
    public String getId() {
        return element.findElement(PLAY).getAttribute(TRACK_ID_ATTRIBUTE);
    }

    @NotNull
    public String getArtist() {
        explicitWaitVisible(ARTIST);
        return element.findElement(ARTIST).getText();
    }

    public boolean isPlaying() {
        return isElementPresent(element, PAUSE);
    }

    public void play() {
        log.info("Playing track {}", getId());
        click(element, PLAY);
        final ExpectedCondition<?> playing = ExpectedConditions.presenceOfElementLocated(PAUSE);
        explicitWait(playing, 2, 500);
    }

    public void pause() {
        log.info("Pausing track {}", getId());
        click(element, PAUSE);
        final ExpectedCondition<?> paused = ExpectedConditions.not(ExpectedConditions.presenceOfAllElementsLocatedBy(PAUSE));
        explicitWait(paused, 2, 500);
    }
}
