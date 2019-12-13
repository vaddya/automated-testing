package com.vaddya.autotests.page;

import com.vaddya.autotests.page.search.ToolbarSearchBlock;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BaseElement {
    private static final By AVATAR = By.xpath(".//div[@id='hook_Block_Avatar']");

    public HomePage(@NotNull final WebDriver driver) {
        super(driver);
    }

    @Override
    protected void check() {
        Assertions.assertTrue(explicitWaitVisible(AVATAR), "No avatar on home page!");
    }

    @NotNull
    public ToolbarSearchBlock toolbarSearch() {
        return new ToolbarSearchBlock(driver);
    }
}
