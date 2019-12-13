package com.vaddya.autotests.page.search;

import com.vaddya.autotests.page.BaseElement;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BaseSearchCard extends BaseElement {
    protected final WebElement element;

    protected BaseSearchCard(
            @NotNull final WebDriver driver,
            @NotNull final WebElement element) {
        super(driver);
        this.element = element;
    }

    @Override
    protected void check() {
        // no checks for a card
    }

    @NotNull
    public abstract String getId();
}
