package com.vaddya.autotests.page.search.game;

import com.vaddya.autotests.page.GamePage;
import com.vaddya.autotests.page.search.BaseSearchCard;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GameSearchCard extends BaseSearchCard {
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
        String href = element.findElement(LINK).getAttribute("href");
        return href.substring(href.lastIndexOf('/') + 1);
    }
    
    @NotNull
    public GamePage play() {
        click(element, PLAY);
        return new GamePage(driver);
    }
}
