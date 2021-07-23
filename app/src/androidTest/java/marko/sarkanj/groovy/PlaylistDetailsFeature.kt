package marko.sarkanj.groovy

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import java.util.EnumSet.allOf

class PlaylistDetailsFeature: BaseUITest() {
    @Test
    fun displaysPlaylistNameAndDetails() {
        navigateToDetailsScreen()
        onView(withId(R.id.playlists_details_name)).check(matches(withText("Hard Rock Cafe")))
        onView(withId(R.id.playlists_details_details)).check(matches(withText("Rock your senses with this timeless signature vibe list. \\n\\n • Poison \\n • You shook me all night \\n • Zombie \\n • Rock'n Me \\n • Thunderstruck \\n • I Hate Myself for Loving you \\n • Crazy \\n • Knockin' on Heavens Door")))
    }
}