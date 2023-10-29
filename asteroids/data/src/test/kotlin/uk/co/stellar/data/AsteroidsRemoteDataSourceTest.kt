package uk.co.stellar.data

import com.google.common.truth.Truth
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import uk.co.stellar.core.ktx.IBuildConfigProperties
import uk.co.stellar.data.helpers.enqueueResponse
import uk.co.stellar.data.helpers.getDate
import uk.co.stellar.data.helpers.readFileFromResource
import java.util.Locale

class AsteroidsRemoteDataSourceTest {

    private val mockWebServer = MockWebServer()

    private lateinit var dataSource: AsteroidsRemoteDataSource

    @Before
    fun setup() {
        mockWebServer.start()

        val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
        }

        val api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(AsteroidNeoWsAPI::class.java)

        dataSource = AsteroidsRemoteDataSource(
            object : IBuildConfigProperties {
                override fun getNasaAPIKey(): String = API_KEY

                override fun getNasaUrl(): String = ""
            },
            api,
            Locale.ROOT
        )
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `WHEN getAsteroidNews THEN has one entry for the 2023-10-28`() = runTest {
        val sample = this::class.java.classLoader.readFileFromResource("feed.json")
        mockWebServer.enqueueResponse(200, sample)
        val today = getDate(2023, 10, 28)

        val response = dataSource.getAsteroidNews(today, today)

        Truth.assertThat(response["2023-10-28"]).isNotNull()
    }

    @Test
    fun `GIVEN entry 2023-10-28 WHEN getAsteroidNews THEN has one asteroid`() = runTest {
        val sample = this::class.java.classLoader.readFileFromResource("feed.json")
        mockWebServer.enqueueResponse(200, sample)
        val today = getDate(2023, 10, 28)

        val response = dataSource.getAsteroidNews(today, today)
        val asteroids = response["2023-10-28"] ?: error("Should have asteroids on the 2023-10-28")

        Truth.assertThat(asteroids).hasSize(1)
    }

    @Test
    fun `GIVEN entry 2023-10-28 WHEN getAsteroidNews THEN asteroid has the correct id`() = runTest {
        val sample = this::class.java.classLoader.readFileFromResource("feed.json")
        mockWebServer.enqueueResponse(200, sample)
        val today = getDate(2023, 10, 28)

        val response = dataSource.getAsteroidNews(today, today)
        val asteroid = response["2023-10-28"]?.first() ?: error("Should have asteroids on the 2023-10-28")

        Truth.assertThat(asteroid.id).isEqualTo("3164401")
    }

    @Test
    fun `GIVEN entry 2023-10-28 WHEN getAsteroidNews THEN asteroid has the correct name`() = runTest {
        val sample = this::class.java.classLoader.readFileFromResource("feed.json")
        mockWebServer.enqueueResponse(200, sample)
        val today = getDate(2023, 10, 28)

        val response = dataSource.getAsteroidNews(today, today)
        val asteroid = response["2023-10-28"]?.first() ?: error("Should have asteroids on the 2023-10-28")

        Truth.assertThat(asteroid.name).isEqualTo("(2003 UY12)")
    }

    @Test
    fun `GIVEN entry 2023-10-28 WHEN getAsteroidNews THEN asteroid has the correct diameter`() = runTest {
        val sample = this::class.java.classLoader.readFileFromResource("feed.json")
        mockWebServer.enqueueResponse(200, sample)
        val today = getDate(2023, 10, 28)

        val response = dataSource.getAsteroidNews(today, today)
        val asteroid = response["2023-10-28"]?.first() ?: error("Should have asteroids on the 2023-10-28")

        Truth.assertThat(asteroid.diameterInMeters).isEqualTo(69.9125232246)
    }

    @Test
    fun `GIVEN entry 2023-10-28 WHEN getAsteroidNews THEN asteroid is not hazardous`() = runTest {
        val sample = this::class.java.classLoader.readFileFromResource("feed.json")
        mockWebServer.enqueueResponse(200, sample)
        val today = getDate(2023, 10, 28)

        val response = dataSource.getAsteroidNews(today, today)
        val asteroid = response["2023-10-28"]?.first() ?: error("Should have asteroids on the 2023-10-28")

        Truth.assertThat(asteroid.isPotentiallyHazardousAsteroid).isFalse()
    }

    @Test
    fun `GIVEN entry 2023-10-28 WHEN getAsteroidNews THEN asteroid has the correct relative velocity`() = runTest {
        val sample = this::class.java.classLoader.readFileFromResource("feed.json")
        mockWebServer.enqueueResponse(200, sample)
        val today = getDate(2023, 10, 28)

        val response = dataSource.getAsteroidNews(today, today)
        val asteroid = response["2023-10-28"]?.first() ?: error("Should have asteroids on the 2023-10-28")

        Truth.assertThat(asteroid.relativeVelocity).isEqualTo(49134.9412616713)
    }

    @Test
    fun `GIVEN entry 2023-10-28 WHEN getAsteroidNews THEN asteroid has miss distance`() = runTest {
        val sample = this::class.java.classLoader.readFileFromResource("feed.json")
        mockWebServer.enqueueResponse(200, sample)
        val today = getDate(2023, 10, 28)

        val response = dataSource.getAsteroidNews(today, today)
        val asteroid = response["2023-10-28"]?.first() ?: error("Should have asteroids on the 2023-10-28")

        Truth.assertThat(asteroid.missDistanceInKm).isEqualTo(2.1870225107683048E7)
    }

    @Test
    fun `WHEN getAsteroidNews THEN uses the correct api key`() = runTest {
        val sample = this::class.java.classLoader.readFileFromResource("feed.json")
        mockWebServer.enqueueResponse(200, sample)
        val today = getDate(2023, 10, 28)

        dataSource.getAsteroidNews(today, today)

        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.requestUrl!!.queryParameter("api_key")).isEqualTo(API_KEY)
    }

    @Test
    fun `WHEN getAsteroidNews THEN uses the correct start_date`() = runTest {
        val sample = this::class.java.classLoader.readFileFromResource("feed.json")
        mockWebServer.enqueueResponse(200, sample)
        val today = getDate(2023, 10, 28)

        dataSource.getAsteroidNews(today, today)

        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.requestUrl!!.queryParameter("start_date")).isEqualTo("2023-10-28")
    }

    @Test
    fun `WHEN getAsteroidNews THEN uses the correct end_date`() = runTest {
        val sample = this::class.java.classLoader.readFileFromResource("feed.json")
        mockWebServer.enqueueResponse(200, sample)
        val today = getDate(2023, 10, 28)

        dataSource.getAsteroidNews(today, today)

        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.requestUrl!!.queryParameter("end_date")).isEqualTo("2023-10-28")
    }

    companion object {
        private const val API_KEY = "api-key"
    }
}
