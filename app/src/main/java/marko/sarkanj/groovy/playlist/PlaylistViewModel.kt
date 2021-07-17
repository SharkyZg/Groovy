package marko.sarkanj.groovy.playlist

import androidx.lifecycle.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PlaylistViewModel(
    private val repository: PlaylistRepository
) : ViewModel() {

    val loader = MutableLiveData<Boolean>()

    val playlists = liveData<Result<List<Playlist>>> {
        loader.postValue(true)

        emitSource(repository.getPlaylists().asLiveData())
    }
}
