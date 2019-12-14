package com.vaddya.autotests.page.search.user;

import com.vaddya.autotests.page.search.BaseSearchPage;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserSearchPage extends BaseSearchPage<UserSearchCard> {
    private static final By SEARCH_BY_ID_INPUT = By.id("field_refs");
    private static final By SEARCH_FROM_AGE = By.id("field_fromage");
    private static final By SEARCH_TILL_AGE = By.id("field_tillage");
    private static final String SEARCH_CITY_TEMPLATE = ".//div[contains(@data-field_location, '%s')]";

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
    public UserSearchPage withSearchOnlyById() {
        click(SEARCH_BY_ID_INPUT);
        return this;
    }

    @NotNull
    public UserSearchPage withFromAge(final int from) {
        select(SEARCH_FROM_AGE, String.valueOf(from));
        return this;
    }

    @NotNull
    public UserSearchPage withTillAge(final int till) {
        select(SEARCH_TILL_AGE, String.valueOf(till));
        return this;
    }

    @NotNull
    public UserSearchPage withCity(@NotNull final String city) {
        final String cityPath = String.format(SEARCH_CITY_TEMPLATE, city);
        final By cityLocator = By.xpath(cityPath);
        click(cityLocator);
        return this;
    }
}
