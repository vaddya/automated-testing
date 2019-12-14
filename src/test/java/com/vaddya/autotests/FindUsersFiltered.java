package com.vaddya.autotests;

import java.util.List;

import com.vaddya.autotests.page.search.SearchPage;
import com.vaddya.autotests.page.search.user.UserSearchCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FindUsersFiltered extends BaseTest {
    private static final String CITY_MOSCOW = "Москва";
    private static final int AGE_FROM = 20;
    private static final int AGE_TO = 30;
    private static final int USERS_TO_CHECK = 20;

    @Test
    void testUserFiltered() {
        final SearchPage search = doLogin()
                .toolbarSearch()
                .submit();
        final List<UserSearchCard> users = search
                .toUserDomain()
                .withFromAge(AGE_FROM)
                .withTillAge(AGE_TO)
                .withCity(CITY_MOSCOW)
                .search()
                .getResults(USERS_TO_CHECK);

        users.forEach(user -> {
            user.getAge().ifPresent(age -> Assertions.assertTrue(age >= AGE_FROM && age <= AGE_TO,
                    "Wrong user age: " + age));
            user.getCity().ifPresent(city -> Assertions.assertEquals(CITY_MOSCOW, city,
                    "Wrong user city: " + city));
        });
    }
}
