package hoonstudio.com.tutory.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import hoonstudio.com.tutory.data.network.response.LyricResponse
import hoonstudio.com.tutory.data.network.response.SongResponse
import kotlinx.coroutines.Deferred

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://developer.musixmatch.com/documentation
//https://api.musixmatch.com/ws/1.1/track.lyrics.get?track_id=15953433&apikey=72249f17600a4ff504f618946bd0d0ef

// this interface will be used by retrofit to fetch data from the API
const val API_KEY = "72249f17600a4ff504f618946bd0d0ef"

interface MusixmatchLyricApiService {

    @GET("track.get")
    fun getSong(
        @Query("track_id") trackID: String
    ): Deferred<SongResponse>

    @GET("track.lyrics.get")
    fun getLyrics(
        @Query("track_id") trackID: String
    ): Deferred<LyricResponse>

    companion object {
        operator fun invoke(
            connectivityInterceptor : ConnectivityInterceptor
        ): MusixmatchLyricApiService {
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("apikey", API_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.musixmatch.com/ws/1.1/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MusixmatchLyricApiService::class.java)

        }
    }
}