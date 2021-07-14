package marko.sarkanj.groovy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import marko.sarkanj.groovy.utils.MainCoroutineScopeRule
import org.junit.Test
import org.junit.Rule
import marko.sarkanj.groovy.utils.getValueForTest

class PlaylistViewModelShould {
    @get:Rule
    val coroutinesTestRule = MainCoroutineScopeRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository: PlaylistRepository = mock()
    private val playlists = mock<List<Playlist>>()
    private val expected = Result.success(playlists)


    @Test
    fun getPlaylistsFromRepository() = runBlockingTest {
        runBlocking {
            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }
        val viewModel = PlaylistViewModel(repository)

        viewModel.playlists.getValueForTest()

        verify(repository, times(1)).getPlaylists()
    }

    @Test
    fun emitsPlaylistsFromRepository() = runBlockingTest {
        runBlocking {
            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }
        val viewModel = PlaylistViewModel(repository)

        assertEquals(expected, viewModel.playlists.getValueForTest())
    }
}