package com.vaddya.autotests.page.search.video;

import java.util.Arrays;

import com.vaddya.autotests.page.search.BaseSearchCard;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class VideoSearchCard extends BaseSearchCard {
    private static final By LINK = By.xpath(".//a[contains(@class, 'video-card_lk')]");
    private static final By DURATION = By.xpath(".//div[contains(@class, 'video-card_duration')]");

    VideoSearchCard(@NotNull final WebDriver driver, @NotNull final WebElement element) {
        super(driver, element);
    }

    @Override
    @NotNull
    public String getId() {
        final String href = element.findElement(LINK).getAttribute("href");
        return idFromHref(href);
    }
    
    public int getDurationInSeconds() {
        final String durationText = element.findElement(DURATION).getText();
        final String[] durationTexts = StringUtils.split(durationText, ':');
        return Arrays.stream(durationTexts)
                .mapToInt(Integer::parseInt)
                .reduce(0, (acc, x) -> acc * 60 + x);
    }
}
