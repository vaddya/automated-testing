package com.vaddya.autotests;

import com.vaddya.autotests.page.GamePage;
import com.vaddya.autotests.page.search.SearchDomain;
import com.vaddya.autotests.page.search.SearchPage;
import com.vaddya.autotests.page.search.game.GameSearchCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FindGameByNameTest extends BaseTest {
    private static final String GAME_NAME = "Сокровища Пиратов";
    private static final String GAME_ID = "piratetreasures";

    @Test
    void testFindGameByName() {
        final SearchPage search = doLogin()
                .toolbarSearch()
                .withQuery(GAME_NAME)
                .submit();
        Assertions.assertEquals(SearchDomain.GAMES, search.getCurrentDomain(),
                "Query is not classified as game domain: " + GAME_NAME);

        final GameSearchCard gameCard = search
                .toGameDomain()
                .getFirstResult();
        Assertions.assertEquals(GAME_ID, gameCard.getId(),
                "Games IDs are different");

        final GamePage game = gameCard.play();
    }
}
