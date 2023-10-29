package uk.co.stellar.data

import retrofit2.HttpException
import uk.co.stellar.core.ktx.IBuildConfigProperties
import uk.co.stellar.domain.Asteroid
import uk.co.stellar.domain.AsteroidNeoWsException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AsteroidsRemoteDataSource(
    private val buildConfigProperties: IBuildConfigProperties,
    private val api: AsteroidNeoWsAPI,
    private val locale: Locale
) {

    @Throws(AsteroidNeoWsException::class)
    suspend fun getAsteroidNews(from: Date, to: Date): Map<String, List<Asteroid>> {
        return try {
            val response = api.getFeed(from.toYYYYMMDD(), to.toYYYYMMDD(), buildConfigProperties.getNasaAPIKey())

            response.nearEarthObjects.mapValues {
                val values = it.value.map { obj -> obj.toAsteroid() }

                values
            }
        } catch (e: HttpException) {
            throw AsteroidNeoWsException(cause = e)
        }
    }

    private fun Date.toYYYYMMDD(): String {
        return SimpleDateFormat("yyyy-MM-dd", locale).format(this)
    }
}
