package com.vaddya.autotests.page.search.user;

import com.vaddya.autotests.page.search.BaseSearchPage;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserSearchPage extends BaseSearchPage<UserSearchCard> {
    private static final Logger log = LoggerFactory.getLogger(UserSearchPage.class);
    private static final By SEARCH_BY_ID_INPUT = By.xpath(".//input[@id='field_refs']");

    public UserSearchPage(@NotNull final WebDriver driver) {
        super(driver);
    }

    @Override
    @NotNull
    protected UserSearchCard wrapElement(@NotNull final WebElement element) {
        return new UserSearchCard(driver, element);
    }

    @NotNull
    public UserSearchPage moveToFilters() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)");
        return this;
    }

    @NotNull
    public UserSearchPage searchOnlyById() {
        click(SEARCH_BY_ID_INPUT);
        return this;
    }

    @NotNull
    public UserSearchPage search(@NotNull final String query) {
        super.search(query);
        return this;
    }
}
