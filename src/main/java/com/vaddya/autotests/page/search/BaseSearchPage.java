package com.vaddya.autotests.page.search;

import java.util.List;
import java.util.stream.Collectors;

import com.vaddya.autotests.page.BasePage;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseSearchPage<T extends BaseSearchCard> extends BasePage {
    private static final Logger log = LoggerFactory.getLogger(BaseSearchPage.class);
    private static final By SEARCH_DOMAINS = By.id("categoriesList");
    private static final By SEARCH_INPUT = By.xpath(".//input[@id='query_usersearch']");
    private static final By RESULT_LIST_ITEMS = By.xpath((".//div[@id='gs_result_list']/div"));
    private static final By SEARCH_IN_PROGRESS = By.xpath(".//div[contains(@class, 'search-input_searching')]");

    public BaseSearchPage(@NotNull final WebDriver driver) {
        super(driver);
    }

    @Override
    protected void check() {
        Assertions.assertTrue(explicitWaitVisible(SEARCH_DOMAINS), "No domain tabs on the search page!");
        Assertions.assertTrue(explicitWaitVisible(SEARCH_INPUT), "No search input on the search page!");
    }

    public BaseSearchPage<T> search(@NotNull final String query) {
        type(SEARCH_INPUT, query);
        waitSearch();
        return this;
    }

    public BaseSearchPage<T> search() {
        type(SEARCH_INPUT, Keys.ENTER);
        waitSearch();
        return this;
    }

    @NotNull
    public T getFirstResult() {
        return wrapElement(driver.findElement(RESULT_LIST_ITEMS));
    }

    @NotNull
    public List<T> getResults(final int number) {
        return driver.findElements(RESULT_LIST_ITEMS)
                .stream()
                .limit(number)
                .map(this::wrapElement)
                .collect(Collectors.toList());
    }

    @NotNull
    abstract protected T wrapElement(@NotNull final WebElement element);

    protected void waitSearch() {
        final ExpectedCondition<?> searchInProgress = ExpectedConditions.presenceOfElementLocated(SEARCH_IN_PROGRESS);
        if (!explicitWait(searchInProgress, 3, 500)) {
            log.warn("Unable to wait for search to start");
        }

        final ExpectedCondition<?> searchFinished = ExpectedConditions.numberOfElementsToBe(SEARCH_IN_PROGRESS, 0);
        if (!explicitWait(searchFinished, 3, 500)) {
            log.warn("Unable to wait for search to finish. Searching again...");
            search();
        }
    }
}
