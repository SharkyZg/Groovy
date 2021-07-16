package marko.sarkanj.groovy.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.RuntimeException

class PlaylistService(
    private val playlistAPI: PlaylistAPI
    ) {
    suspend fun fetchPlaylists() : Flow<Result<List<Playlist>>>{

        return flow {
            emit(Result.success(playlistAPI.fetchAllPlaylists()))
        }
    }

}
