package com.vaddya.autotests;

import com.vaddya.autotests.page.search.SearchDomain;
import com.vaddya.autotests.page.search.SearchPage;
import com.vaddya.autotests.page.search.user.UserSearchCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FindUserByIdTest extends BaseTest {
    private static final String USER_ID = "vaddyacom";

    @Test
    void testFindUserById() {
        final SearchPage search = doLogin()
                .toolbarSearch()
                .submit();
        Assertions.assertEquals(SearchDomain.USERS, search.getCurrentDomain(),
                "Users are not default domain");

        final UserSearchCard userCard = search.toUserDomain()
                .moveToFilters()
                .withSearchOnlyById()
                .search(USER_ID)
                .getFirstResult();
        Assertions.assertEquals(USER_ID, userCard.getId(),
                "Wrong user found using ID-only search: " + userCard.getId());
    }
}
