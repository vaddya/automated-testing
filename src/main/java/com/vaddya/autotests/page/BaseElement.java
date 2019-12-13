package com.vaddya.autotests.page;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Preconditions;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BaseElement {
    protected final WebDriver driver;

    public BaseElement(@NotNull final WebDriver driver) {
        this.driver = driver;
    }

    protected void type(
            @NotNull final SearchContext context,
            @NotNull final By locator,
            @NotNull final CharSequence... text) {
        context.findElement(locator).clear();
        context.findElement(locator).sendKeys(text);
    }

    protected void click(
            @NotNull final SearchContext context,
            @NotNull final By locator) {
        context.findElement(locator).click();
    }

    protected boolean isElementPresent(
            @NotNull final SearchContext context,
            @NotNull final By locator) {
        try {
            context.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected boolean explicitWait(
            @NotNull final ExpectedCondition<?> condition,
            final long maxCheckTimeInSeconds,
            final long millisecondsBetweenChecks) {
        Preconditions.checkNotNull(condition, "Condition must be not null");
        Preconditions.checkArgument(TimeUnit.MINUTES.toSeconds(3) > maxCheckTimeInSeconds, "Max check time in seconds should be less than 3 minutes");
        checkConditionTimeouts(maxCheckTimeInSeconds, millisecondsBetweenChecks);
        try {
            driver.manage()
                    .timeouts()
                    .implicitlyWait(0, TimeUnit.SECONDS);
            final WebDriverWait explicitWait = new WebDriverWait(driver, maxCheckTimeInSeconds, millisecondsBetweenChecks);
            explicitWait.until(condition);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }

    protected boolean explicitWaitVisible(@NotNull final By locator) {
        return explicitWait(ExpectedConditions.visibilityOfElementLocated(locator), 10, 500);
    }

    private void checkConditionTimeouts(
            final long maxCheckTimeInSeconds,
            final long millisecondsBetweenChecks) {
        Preconditions.checkState(maxCheckTimeInSeconds > 0, "maximum check time in seconds must be not 0");
        Preconditions.checkState(millisecondsBetweenChecks > 0, "milliseconds count between checks must be not 0");
        Preconditions.checkState(millisecondsBetweenChecks < (maxCheckTimeInSeconds * 1000),
                "Millis between checks must be less than max seconds to wait");
    }
}
