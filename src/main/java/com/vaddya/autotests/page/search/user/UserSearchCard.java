package com.vaddya.autotests.page.search.user;

import java.util.Optional;

import com.vaddya.autotests.page.search.BaseSearchCard;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserSearchCard extends BaseSearchCard {
    private static final Logger log = LoggerFactory.getLogger(UserSearchCard.class);
    private static final By LINK = By.xpath(".//a[contains(@class, 'dblock')]");
    private static final By SUMMARY = By.xpath(".//div[contains(@class, 'gs_result_i_t_addr')]");

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
    
    @NotNull
    public Optional<Integer> getAge() {
        final String[] summary = getSummary();
        if (summary.length < 3) {
            return Optional.empty();
        }
        try {
            final String[] ageLine = summary[0].split("\\s");
            final int age = Integer.parseInt(ageLine[0]);
            return Optional.of(age);
        } catch (NumberFormatException e) {
            log.warn("Unable to get user age from summary: {}", summary[0]);
            return Optional.empty();
        }
    }
    
    @NotNull
    public Optional<String> getCity() {
        final String[] summary = getSummary();
        if (summary.length < 2) {
            return Optional.empty();
        }
        return Optional.of(summary[summary.length - 2]);
    }
    
    @NotNull
    private String[] getSummary() {
        String summaryText = element.findElement(SUMMARY).getText();
        return StringUtils.split(summaryText, ", ");
    }
}
