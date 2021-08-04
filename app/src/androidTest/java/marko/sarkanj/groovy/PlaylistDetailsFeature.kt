package marko.sarkanj.groovy

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions
import org.junit.Rule
import org.junit.Test
import java.util.EnumSet.allOf

class PlaylistDetailsFeature: BaseUITest() {
    @Test
    fun displaysPlaylistNameAndDetails() {
        navigateToDetailsScreen()
        onView(withId(R.id.playlists_details_name)).check(matches(withText("Hard Rock Cafe")))
        //onView(withId(R.id.playlists_details_details)).check(matches(withText("Rock your senses with this timeless signature vibe list. \\n\\n • Poison \\n • You shook me all night \\n • Zombie \\n • Rock'n Me \\n • Thunderstruck \\n • I Hate Myself for Loving you \\n • Crazy \\n • Knockin' on Heavens Door")))
    }

    @Test
    fun displaysLoaderWhileFetchingThePlaylistsDetails() {
        IdlingRegistry.getInstance().unregister(idlingResource)

        Thread.sleep(2000)
        navigateToDetailsScreen()
        BaristaVisibilityAssertions.assertDisplayed(R.id.details_loader)
    }

    @Test
    fun hidesLoader() {
        navigateToDetailsScreen()

        BaristaVisibilityAssertions.assertNotDisplayed(R.id.details_loader)
    }
}