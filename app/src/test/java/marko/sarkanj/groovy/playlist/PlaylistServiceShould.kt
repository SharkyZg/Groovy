package marko.sarkanj.groovy.playlist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import junit.framework.TestCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import marko.sarkanj.groovy.utils.BaseUnitTest
import org.junit.Test
import java.lang.RuntimeException

class PlaylistServiceShould {
    class PlaylistServiceShould : BaseUnitTest() {
        private val playlistAPI: PlaylistAPI = mock()
        private lateinit var service: PlaylistService
        private val playlists: List<Playlist> = mock()

        @Test
        fun fetchPlaylistsFromAPI() = runBlockingTest {
            service = PlaylistService(playlistAPI)
            service.fetchPlaylists().first()

            verify(playlistAPI, times(1)).fetchAllPlaylists()
        }


        @Test
        fun convertValuesToFlowResultAndEmitThem() = runBlockingTest {
            mockSuccessfulCase()

            assertEquals(Result.success(playlists), service.fetchPlaylists().first())
        }

        private suspend fun mockSuccessfulCase() {
            whenever(playlistAPI.fetchAllPlaylists()).thenReturn(playlists)
            service = PlaylistService(playlistAPI)
        }

        @Test
        fun emitsErrorResultWhenNetworkFails() = runBlockingTest {
            mockErrorCase()

            assertEquals("Something went wrong", service.fetchPlaylists().first()
                .exceptionOrNull()?.message)
        }

        private suspend fun mockErrorCase() {
            whenever(playlistAPI.fetchAllPlaylists())
                .thenThrow(RuntimeException("Backend exception"))

            service = PlaylistService(playlistAPI)
        }
    }
}