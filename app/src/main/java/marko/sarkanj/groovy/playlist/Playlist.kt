package marko.sarkanj.groovy.playlist

import marko.sarkanj.groovy.R

data class Playlist(
    val id: String,
    val name: String,
    val category: String,
    val image: Int = R.mipmap.playlist
    )