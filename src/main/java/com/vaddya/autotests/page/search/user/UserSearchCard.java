package com.vaddya.autotests.page.search.user;

import com.vaddya.autotests.page.search.BaseSearchCard;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserSearchCard extends BaseSearchCard {
    private static final By LINK = By.xpath(".//a[contains(@class, 'dblock')]");

    UserSearchCard(
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
}
