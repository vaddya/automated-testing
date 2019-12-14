package com.vaddya.autotests;

import java.util.List;

import com.vaddya.autotests.page.search.SearchDomain;
import com.vaddya.autotests.page.search.SearchPage;
import com.vaddya.autotests.page.search.music.TrackSearchCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FindAndPlayMusicTest extends BaseTest {
    private static final String ARTIST = "Metallica";
    private static final int TRACKS_TO_CHECK = 20;
    
    @Test
    void testFindAndPlayMusic() {
        final SearchPage search = doLogin()
                .toolbarSearch()
                .withQuery(ARTIST)
                .submit();
        Assertions.assertEquals(SearchDomain.MUSIC, search.getCurrentDomain(),
                "Query is not classified as music query: " + ARTIST);

        final List<TrackSearchCard> tracks = search
                .toMusicDomain()
                .toTracks()
//                .search(ARTIST)
                .getResults(TRACKS_TO_CHECK);
        tracks.forEach(track -> Assertions.assertEquals(ARTIST, track.getArtist(),
                "Artists are different"));

        final TrackSearchCard track = tracks.get(0);
        Assertions.assertFalse(track.isPlaying());
        track.play();
        Assertions.assertTrue(track.isPlaying());
        track.pause();
        Assertions.assertFalse(track.isPlaying());
    }
}
