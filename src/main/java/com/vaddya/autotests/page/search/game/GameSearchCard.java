package com.vaddya.autotests.page.search.game;

import com.vaddya.autotests.page.GamePage;
import com.vaddya.autotests.page.search.BaseSearchCard;
import com.vaddya.autotests.page.search.music.TrackSearchCard;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameSearchCard extends BaseSearchCard {
    private static final Logger log = LoggerFactory.getLogger(TrackSearchCard.class);
    private static final By LINK = By.xpath(".//div[contains(@class, 'gs_result_i_t')]/a");
    private static final By PLAY = By.xpath(".//div[contains(@class, 'gs_result_i')]/a");

    GameSearchCard(
            @NotNull final WebDriver driver,
            @NotNull final WebElement element) {
        super(driver, element);
    }

    @Override
    @NotNull
    public String getId() {
        final String href = element.findElement(LINK).getAttribute("href");
        return idFromHref(href);
    }
    
    @NotNull
    public GamePage play() {
        log.info("Playing game {}", getId());
        click(element, PLAY);
        return new GamePage(driver);
    }
}
