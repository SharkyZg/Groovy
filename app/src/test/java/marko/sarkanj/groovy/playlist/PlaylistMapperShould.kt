package marko.sarkanj.groovy.playlist

import junit.framework.TestCase.assertEquals
import kotlinx.android.synthetic.main.playlist_item.view.*
import marko.sarkanj.groovy.R
import marko.sarkanj.groovy.utils.BaseUnitTest
import org.junit.Test

class PlaylistMapperShould: BaseUnitTest() {

    private val playlistRaw = PlaylistRaw("1", "da name", "da category")
    private val playlistRawRock = PlaylistRaw("1", "da name", "rock")

    private val mapper = PlaylistMapper()

    private val playlists = mapper(listOf(playlistRaw))

    private val playlist = playlists[0]

    private val playlistRock = mapper(listOf(playlistRawRock))[0]

    @Test
    fun keepSameId() {
        assertEquals(playlistRaw.id, playlist.id)
    }

    @Test
    fun keepSameName() {
        assertEquals(playlistRaw.name, playlist.name)
    }

    @Test
    fun keepSameCategory() {
        assertEquals(playlistRaw.category, playlist.category)
    }

    @Test
    fun mapDefaultImageWhenNotRocCategory() {
        assertEquals(R.mipmap.playlist, playlist.image)
    }

    @Test
    fun mapRockImageWhenRockCategory() {
        assertEquals(R.mipmap.rock, playlistRock.image)
    }
}