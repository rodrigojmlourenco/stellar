package uk.co.asteroids.wiring

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import uk.co.stellar.core.ktx.DateProvider
import uk.co.stellar.core.ktx.IBuildConfigProperties
import uk.co.stellar.data.AsteroidNeoWsAPI
import uk.co.stellar.data.AsteroidNeoWsRepositoryImpl
import uk.co.stellar.data.AsteroidsRemoteDataSource
import uk.co.stellar.domain.AsteroidNeoWsRepository
import java.util.Locale

@Module
@InstallIn(SingletonComponent::class)
class AsteroidsModule {

    @Provides
    fun provideRepository(
        buildConfigProperties: IBuildConfigProperties,
        retrofit: Retrofit,
        locale: Locale
    ): AsteroidNeoWsRepository {
        return AsteroidNeoWsRepositoryImpl(
            AsteroidsRemoteDataSource(
                buildConfigProperties,
                retrofit.create(AsteroidNeoWsAPI::class.java),
                locale
            ),
            DateProvider()
        )
    }
}