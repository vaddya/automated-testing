package com.vaddya.autotests.page.search;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.vaddya.autotests.page.BasePage;
import org.apache.commons.lang3.StringUtils;
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
    private static final By COUNT = By.xpath(".//div[contains(@class, 'portlet_h_name_t')]");
    private static final Pattern COUNT_PATTERN = Pattern.compile("\\w+([\\s\\d]+)\\w+");

    protected BaseSearchPage(@NotNull final WebDriver driver) {
        super(driver);
    }

    @NotNull
    protected abstract T wrapElement(@NotNull final WebElement element);

    @Override
    protected void check() {
        Assertions.assertTrue(explicitWaitVisible(SEARCH_DOMAINS), "No domain tabs on the search page!");
        Assertions.assertTrue(explicitWaitVisible(SEARCH_INPUT), "No search input on the search page!");
    }

    public BaseSearchPage<T> search(@NotNull final String query) {
        log.info("Searching {}...", query);
        type(SEARCH_INPUT, query);
        waitSearch();
        return this;
    }

    public BaseSearchPage<T> search() {
        log.info("Searching...");
        type(SEARCH_INPUT, Keys.ENTER);
        waitSearch();
        return this;
    }

    @NotNull
    public T getFirstResult() {
        log.info("Retrieving first result");
        return wrapElement(driver.findElement(RESULT_LIST_ITEMS));
    }

    @NotNull
    public List<T> getResults(final int number) {
        log.info("Retrieving first {} results", number);
        return driver.findElements(RESULT_LIST_ITEMS)
                .stream()
                .limit(number)
                .map(this::wrapElement)
                .collect(Collectors.toList());
    }

    public int count() {
        final String countText = driver.findElement(COUNT).getText();
        final Matcher matcher = COUNT_PATTERN.matcher(countText);
        if (matcher.find()) {
            String count = StringUtils.remove(matcher.group(), ' ');
            return Integer.parseInt(count);
        }
        throw new IllegalArgumentException("Wrong count text: " + countText);
    }

    protected void waitSearch() {
        final ExpectedCondition<?> searchInProgress = ExpectedConditions.presenceOfElementLocated(SEARCH_IN_PROGRESS);
        if (explicitWait(searchInProgress, 3, 500)) {
            log.warn("Unable to wait for search to start");
        } else {
            log.debug("Search started...");
        }

        final ExpectedCondition<?> searchFinished = ExpectedConditions.numberOfElementsToBe(SEARCH_IN_PROGRESS, 0);
        if (!explicitWait(searchFinished, 3, 500)) {
            log.warn("Unable to wait for search to finish. Searching again...");
            search();
        } else {
            log.debug("Search finished...");
        }
    }
}
