package marko.sarkanj.groovy

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotExist
import org.hamcrest.CoreMatchers
import org.junit.Rule
import org.junit.Test
import java.lang.Thread.sleep
import java.util.EnumSet.allOf

class PlaylistDetailsFeature: BaseUITest() {
    @Test
    fun displaysPlaylistNameAndDetails() {
        navigateToDetailsScreen(0)
        onView(withId(R.id.playlists_details_name)).check(matches(withText("Hard Rock Cafe")))
        //onView(withId(R.id.playlists_details_details)).check(matches(withText("Rock your senses with this timeless signature vibe list. \\n\\n • Poison \\n • You shook me all night \\n • Zombie \\n • Rock'n Me \\n • Thunderstruck \\n • I Hate Myself for Loving you \\n • Crazy \\n • Knockin' on Heavens Door")))
    }

    @Test
    fun displaysLoaderWhileFetchingThePlaylistsDetails() {
        IdlingRegistry.getInstance().unregister(idlingResource)

        Thread.sleep(2000)
        navigateToDetailsScreen(0)
        BaristaVisibilityAssertions.assertDisplayed(R.id.details_loader)
    }

    @Test
    fun hidesLoader() {
        navigateToDetailsScreen(0)

        BaristaVisibilityAssertions.assertNotDisplayed(R.id.details_loader)
    }

    @Test
    fun displaysErrorMessageWhenNetworkFails() {
        navigateToDetailsScreen(1)

        assertDisplayed(R.string.generic_error)
    }

    @Test
    fun hidesErrorMessageWhenNetworkFails() {
        navigateToDetailsScreen(1)
        sleep(3000)
        assertNotExist(R.string.generic_error)
    }
}