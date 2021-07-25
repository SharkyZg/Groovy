package marko.sarkanj.groovy.details

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runBlockingTest
import marko.sarkanj.groovy.utils.BaseUnitTest
import org.junit.Test

class PlaylistDetailsServiceShould : BaseUnitTest() {
    lateinit var service: PlaylistDetailsService
    private val id = "100"
    private val api: PlaylistDetailsAPI = mock()
    private val playlistDetails: PlaylistDetails = mock()

    @Test
    fun fetchPlaylistDetailsFromAPI() = runBlockingTest {
        service = PlaylistDetailsService(api)

        service.fetchPlaylistDetails(id).single()

        verify(api, times(1)).fetchPlaylistDetails(id)
    }

    @Test
    fun convertValuesToFlowResultAndEmitThem() = runBlockingTest {
        whenever(api.fetchPlaylistDetails(id)).thenReturn(playlistDetails)

        service = PlaylistDetailsService(api)

        assertEquals(Result.success(playlistDetails), service.fetchPlaylistDetails(id).single())
    }
}