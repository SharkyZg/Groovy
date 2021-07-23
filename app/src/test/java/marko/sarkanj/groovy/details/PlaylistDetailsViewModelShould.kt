package marko.sarkanj.groovy.details

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import marko.sarkanj.groovy.utils.BaseUnitTest
import marko.sarkanj.groovy.utils.getValueForTest
import org.junit.Test

class PlaylistDetailsViewModelShould : BaseUnitTest() {

    lateinit var viewModel: PlaylistDetailsViewModel
    private val id = "1"
    private val service : PlaylistDetailsService = mock()

    @Test
    fun getPlaylistDetailsFromService() {
        viewModel = PlaylistDetailsViewModel(service)

        viewModel.getPlaylistDetails(id)

        viewModel.playlistDetails.getValueForTest()

        verify(service, times(1)).fetchPlaylistDetails(id)


    }
}