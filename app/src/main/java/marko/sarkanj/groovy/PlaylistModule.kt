package marko.sarkanj.groovy

import com.jakewharton.espresso.OkHttp3IdlingResource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import marko.sarkanj.groovy.details.PlaylistDetailsAPI
import marko.sarkanj.groovy.playlist.PlaylistAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val client = OkHttpClient()
val idlingResource = OkHttp3IdlingResource.create("okhttp", client)

@Module
@InstallIn(FragmentComponent::class)
class PlaylistModule {
    @Provides
    fun playlistAPI(retrofit: Retrofit) = retrofit.create(PlaylistAPI::class.java)

    @Provides
    fun playlistDetailsAPI(retrofit: Retrofit) = retrofit.create(PlaylistDetailsAPI::class.java)

    @Provides
    fun retrofit() = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:3000/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
