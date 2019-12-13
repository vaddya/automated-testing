package com.vaddya.autotests.page.login;

import com.vaddya.autotests.page.HomePage;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public interface LoginPage {
    @NotNull
    LoginPage withEmail(@NotNull String email);
    
    @NotNull
    LoginPage withPassword(@NotNull String password);
    
    @NotNull
    HomePage submit();
    
    @NotNull
    static LoginPage create(@NotNull final WebDriver driver) {
        final By loginBlockV2 = By.xpath(".//*[contains(@class, 'wrap__akasw')]");
        try {
            driver.findElement(loginBlockV2);
            return new LoginPageV2(driver);
        } catch (NoSuchElementException e) {
            return new LoginPageV1(driver);
        }
    }
}
