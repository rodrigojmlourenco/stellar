package uk.co.stellar.dagger

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import uk.co.stellar.core.BuildConfigProperties

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideJson(): Json {
        return Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
    }

    @Provides
    fun provideRetrofit(json: Json, buildConfigProperties: BuildConfigProperties): Retrofit {
        return Retrofit.Builder()
            .baseUrl(buildConfigProperties.getNasaUrl())
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }
}
