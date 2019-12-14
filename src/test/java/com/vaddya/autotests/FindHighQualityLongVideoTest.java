package com.vaddya.autotests;

import java.time.Duration;
import java.util.List;

import com.vaddya.autotests.page.search.SearchPage;
import com.vaddya.autotests.page.search.video.VideoSearchCard;
import com.vaddya.autotests.page.search.video.VideoSearchPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FindHighQualityLongVideoTest extends BaseTest {
    private static final String VIDEO_QUERY = "футбол";
    private static final Duration MIN_LONG_VIDEO = Duration.ofMinutes(20);
    private static final int VIDEO_TO_CHECK = 20;

    @Test
    void testFindHighQualityLongVideo() {
        final SearchPage search = doLogin()
                .toolbarSearch()
                .submit();

        final VideoSearchPage videoSearch = search.toVideoDomain();
        Assertions.assertTrue(videoSearch.isStub(),
                "No stub on video page without query");

        final int longVideoCount = videoSearch
                .withLongDuration()
                .search(VIDEO_QUERY)
                .count();
        Assertions.assertTrue(longVideoCount > 0,
                "No video found by query: " + VIDEO_QUERY);

        final int longVideoWithHighQualityCount = videoSearch
                .withHighQuality()
                .search(VIDEO_QUERY)
                .count();
        Assertions.assertTrue(longVideoWithHighQualityCount < longVideoCount,
                "Count of high quality video must be less than total count");

        final List<VideoSearchCard> videos = videoSearch
                .search(VIDEO_QUERY)
                .getResults(VIDEO_TO_CHECK);
        videos.forEach(video -> Assertions.assertTrue(video.getDurationInSeconds() >= MIN_LONG_VIDEO.toSeconds(),
                "Video is not long: " + video.getDurationInSeconds()));
    }
}
