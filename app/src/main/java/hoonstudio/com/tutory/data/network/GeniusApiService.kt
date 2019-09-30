package hoonstudio.com.tutory.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import hoonstudio.com.tutory.Keys
import hoonstudio.com.tutory.data.network.response.SearchResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

val CLIENT_KEY = Keys.GENIUS_CLIENT_ACCESS_TOKEN

// https://api.genius.com/search?access_token=CLIENT_KEY&q=love%20yourself
interface GeniusApiService{

    @GET(value = "search")
    fun getSong(
        @Query(value = "q") songName: String
    ): Deferred<SearchResponse>

    companion object{
        //can do something like fun create() instead of operator fun invoke() but this allows you to call
        // GeniusApiService() instead of having to do GeniusApiService.create()
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): GeniusApiService{
            //Interceptor is an anonymous class with 1 function so you can use the lambda syntax
            val requestInterceptor = Interceptor { chain ->
                //adds the CLIENT KEY into the url where it says 'access_token='
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("access_token", CLIENT_KEY)
                    .build()
                //adds the updated url into the chain
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            // this makes every call get intercepted by requestInterceptor
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.genius.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GeniusApiService::class.java)
        }
    }
}