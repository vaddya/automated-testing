package com.vaddya.autotests.page.search;

import com.vaddya.autotests.page.BaseElement;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ToolbarSearchBlock extends BaseElement {
    private static final By SEARCH_INPUT = By.xpath(".//input[@id='field_query']");
    private static final By SEARCH_BUTTON = By.xpath(".//input[@id='lsBtn']");

    public ToolbarSearchBlock(@NotNull final WebDriver driver) {
        super(driver);
    }

    @Override
    protected void check() {
        Assertions.assertTrue(explicitWaitVisible(SEARCH_INPUT), "No search input in the toolbar search block!");
    }

    @NotNull
    public ToolbarSearchBlock withQuery(@NotNull final String query) {
        type(SEARCH_INPUT, query);
        return this;
    }

    @NotNull
    public SearchPage submit() {
        click(SEARCH_BUTTON);
        return new SearchPage(driver);
    }
}
