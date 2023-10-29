package uk.co.stellar.data

import com.google.common.truth.Truth
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import uk.co.stellar.core.ktx.DateProvider
import uk.co.stellar.core.ktx.IBuildConfigProperties
import uk.co.stellar.data.helpers.enqueueResponse
import uk.co.stellar.data.helpers.getDate
import uk.co.stellar.data.helpers.getMockServerRetrofit
import uk.co.stellar.data.helpers.readFileFromResource
import uk.co.stellar.domain.AsteroidNeoWsException
import uk.co.stellar.domain.AsteroidNeoWsRepository
import java.util.Locale

class AsteroidNeoWsRepositoryImplTest {

    private val mockWebServer = MockWebServer()
    private val mockDateProvider = mockk<DateProvider>()

    private lateinit var repository: AsteroidNeoWsRepository

    @Before
    fun setup() {
        mockWebServer.start()

        val api = getMockServerRetrofit(mockWebServer).create(AsteroidNeoWsAPI::class.java)
        val dataSource = AsteroidsRemoteDataSource(
            object : IBuildConfigProperties {
                override fun getNasaAPIKey(): String = "api_key"

                override fun getNasaUrl(): String = ""
            },
            api,
            Locale.ROOT
        )

        repository = AsteroidNeoWsRepositoryImpl(
            dataSource,
            mockDateProvider
        )
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `WHEN getAsteroidsToday THEN has one asteroid`() = runTest {
        every { mockDateProvider.getToday() } returns getDate(2023, 10, 28)
        val body = this::class.java.classLoader.readFileFromResource("feed.json")
        mockWebServer.enqueueResponse(200, body)

        val response = repository.getAsteroidsToday()

        Truth.assertThat(response).isNotNull()
    }

    @Test
    fun `WHEN getAsteroidsToday THEN has the correct start date`() = runTest {
        every { mockDateProvider.getToday() } returns getDate(2023, 10, 28)
        val body = this::class.java.classLoader.readFileFromResource("feed.json")
        mockWebServer.enqueueResponse(200, body)

        repository.getAsteroidsToday()
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.requestUrl!!.queryParameter("start_date")).isEqualTo("2023-10-28")
    }

    @Test
    fun `WHEN getAsteroidsToday THEN has the correct end date`() = runTest {
        every { mockDateProvider.getToday() } returns getDate(2023, 10, 28)
        val body = this::class.java.classLoader.readFileFromResource("feed.json")
        mockWebServer.enqueueResponse(200, body)

        repository.getAsteroidsToday()
        val request = mockWebServer.takeRequest()

        Truth.assertThat(request.requestUrl!!.queryParameter("end_date")).isEqualTo("2023-10-28")
    }

    @Test(expected = AsteroidNeoWsException::class)
    fun `GIVEN failed response WHEN getAsteroidsToday THEN fails with AsteroidNeoWsException`() = runTest {
        every { mockDateProvider.getToday() } returns getDate(2023, 10, 28)
        val body = this::class.java.classLoader.readFileFromResource("feed.json")
        mockWebServer.enqueueResponse(400, body)

        repository.getAsteroidsToday()
    }
}
