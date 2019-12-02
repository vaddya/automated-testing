package com.vaddya.autotests;

import com.vaddya.autotests.page.HomePage;
import com.vaddya.autotests.page.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

class LoginTest extends BaseTest {
    @BeforeEach
    void init() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
    }

    @Test
    void test() {
        HomePage homePage = new LoginPage(driver)
                .withEmail(Creds.EMAIL)
                .withPassword(Creds.PASSWORD)
                .submit();
    }

    @AfterEach
    void shutdown() {
        driver.close();
    }
}
