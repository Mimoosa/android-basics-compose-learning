package fi.monami.president_app.network

import androidx.core.location.LocationRequestCompat
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val BASE_URL =
    "https://en.wikipedia.org/w/"




private val json = Json {
    ignoreUnknownKeys = true
}

private val retrofit = Retrofit.Builder()
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

val retrofitService: WikipediaApiService = retrofit.create(WikipediaApiService:: class.java)

interface WikipediaApiService{
    @Headers("User-Agent: MonamiApp/1.0 (contact: monami@example.com)")
    @GET("api.php")
    suspend fun searchPresident(
        @Query("srsearch") name: String,
        @Query("action") action: String = "query",
        @Query("format") format: String = "json",
        @Query("list") list: String = "search"
    ): WikiResponse
}