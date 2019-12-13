package com.vaddya.autotests.page.search;

import com.vaddya.autotests.page.BaseElement;
import com.vaddya.autotests.page.search.game.GameSearchPage;
import com.vaddya.autotests.page.search.user.UserSearchPage;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPage extends BaseElement {
    private static final By DOMAIN_TABS = By.xpath(".//span[contains(@class, 'gs_tab')]");
    private static final By ACTIVE_DOMAIN_TABS = By.xpath(".//span[contains(@class, 'gs_tab') and contains(@class, '__active')]");
    private static final String DOMAIN_TAB_TEMPLATE = ".//span[@data-field_mode='%s']";
    private static final String DATA_FIELD_MODE = "data-field_mode";

    SearchPage(@NotNull final WebDriver driver) {
        super(driver);
    }

    @Override
    protected void check() {
        Assertions.assertTrue(explicitWaitVisible(DOMAIN_TABS), "No domain tabs on search page!");
    }

    @NotNull
    public SearchDomain getCurrentDomain() {
        WebElement activeTab = driver.findElement(ACTIVE_DOMAIN_TABS);
        String modeAttribute = activeTab.getAttribute(DATA_FIELD_MODE);
        return SearchDomain.valueOf(modeAttribute.toUpperCase());
    }

    @NotNull
    public UserSearchPage toUserDomain() {
        clickOnDomain(SearchDomain.USERS);
        return new UserSearchPage(driver);
    }

    @NotNull
    public GameSearchPage toGameDomain() {
        clickOnDomain(SearchDomain.GAMES);
        return new GameSearchPage(driver);
    }

    private void clickOnDomain(@NotNull final SearchDomain domain) {
        final String domainTabPath = String.format(DOMAIN_TAB_TEMPLATE, domain.capitalizedName());
        final By domainLocator = By.xpath(domainTabPath);
        click(domainLocator);
    }
}
