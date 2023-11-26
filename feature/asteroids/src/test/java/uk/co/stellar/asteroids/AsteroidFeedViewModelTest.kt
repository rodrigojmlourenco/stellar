package uk.co.stellar.asteroids

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.appmattus.kotlinfixture.kotlinFixture
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import uk.co.stellar.core.ktx.ErrorStringProvider
import uk.co.stellar.domain.Asteroid
import uk.co.stellar.domain.AsteroidNeoWsException
import uk.co.stellar.domain.AsteroidNeoWsRepository
import uk.co.stellar.test.presentation.coroutines.MainDispatcherRule
import uk.co.stellar.test.presentation.livedata.TestObserver

class AsteroidFeedViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val stateObserver = TestObserver<AsteroidFeedViewModel.State>()
    private val fixture = kotlinFixture()

    private val errorProvider = object : ErrorStringProvider {
        override fun getGenericError(): String = GENERIC_ERROR
    }
    private val repository: AsteroidNeoWsRepository = mockk()
    private lateinit var viewModel: AsteroidFeedViewModel

    @Before
    fun setup() {

    }

    @After
    fun teardown() {
        viewModel.state.removeObserver(stateObserver)
    }

    @Test
    fun `WHEN init THEN has one event`() {
        val asteroid = fixture<Asteroid>()
        coEvery { repository.getAsteroidsToday() } returns listOf(asteroid)

        initViewModel()

        Truth.assertThat(stateObserver.captured).hasSize(1)
    }

    @Test
    fun `WHEN init THEN is not loading`() {
        val asteroid = fixture<Asteroid>()
        coEvery { repository.getAsteroidsToday() } returns listOf(asteroid)
        initViewModel()

        val captured = stateObserver.captured.first()

        Truth.assertThat(captured.isLoading).isFalse()
    }

    @Test
    fun `WHEN init THEN has no error message`() {
        val asteroid = fixture<Asteroid>()
        coEvery { repository.getAsteroidsToday() } returns listOf(asteroid)
        initViewModel()

        val captured = stateObserver.captured.first()

        Truth.assertThat(captured.error).isNull()
    }

    @Test
    fun `WHEN init THEN has one asteroid`() {
        val asteroid = fixture<Asteroid>()
        coEvery { repository.getAsteroidsToday() } returns listOf(asteroid)
        initViewModel()

        val captured = stateObserver.captured.first()

        Truth.assertThat(captured.asteroids).hasSize(1)
    }

    @Test
    fun `WHEN init THEN the asteroid has the correct name`() {
        val asteroid = fixture<Asteroid>()
        coEvery { repository.getAsteroidsToday() } returns listOf(asteroid)
        initViewModel()

        val captured = stateObserver.captured.first()
        val capturedAsteroid = captured.asteroids.first()

        Truth.assertThat(capturedAsteroid.name).isEqualTo(asteroid.name)
    }

    @Test
    fun `WHEN init THEN the asteroid the expected size`() {
        val asteroid = fixture<Asteroid>().copy(diameterInMeters = 33.333333)
        coEvery { repository.getAsteroidsToday() } returns listOf(asteroid)
        initViewModel()

        val captured = stateObserver.captured.first()
        val capturedAsteroid = captured.asteroids.first()

        Truth.assertThat(capturedAsteroid.size).isEqualTo("33.3m")
    }

    @Test
    fun `GIVEN the asteroid size is too small to display WHEN init THEN the display size defaults to 1`() {
        val asteroid = fixture<Asteroid>().copy(diameterInMeters = 1.1)
        coEvery { repository.getAsteroidsToday() } returns listOf(asteroid)
        initViewModel()

        val captured = stateObserver.captured.first()
        val capturedAsteroid = captured.asteroids.first()

        Truth.assertThat(capturedAsteroid.displaySizeInDps).isEqualTo(1)
    }

    @Test
    fun `WHEN init THEN the display size is a third of the size`() {
        val asteroid = fixture<Asteroid>().copy(diameterInMeters = 33.3)
        coEvery { repository.getAsteroidsToday() } returns listOf(asteroid)
        initViewModel()

        val captured = stateObserver.captured.first()
        val capturedAsteroid = captured.asteroids.first()

        Truth.assertThat(capturedAsteroid.displaySizeInDps).isEqualTo(11)
    }

    @Test
    fun `WHEN init THEN has the expected miss distance`() {
        val asteroid = fixture<Asteroid>().copy(missDistanceInKm = 33.33333)
        coEvery { repository.getAsteroidsToday() } returns listOf(asteroid)
        initViewModel()

        val captured = stateObserver.captured.first()
        val capturedAsteroid = captured.asteroids.first()

        Truth.assertThat(capturedAsteroid.distance).isEqualTo("33.33km")
    }

    @Test
    fun `WHEN init THEN the display distance if 1000000 smaller than the original`() {
        val asteroid = fixture<Asteroid>().copy(missDistanceInKm = 3000000.0)
        coEvery { repository.getAsteroidsToday() } returns listOf(asteroid)
        initViewModel()

        val captured = stateObserver.captured.first()
        val capturedAsteroid = captured.asteroids.first()

        Truth.assertThat(capturedAsteroid.displayDistanceInDps).isEqualTo(3)
    }

    @Test
    fun `WHEN init THEN has the is dangerous`() {
        val asteroid = fixture<Asteroid>().copy(isPotentiallyHazardousAsteroid = false)
        coEvery { repository.getAsteroidsToday() } returns listOf(asteroid)
        initViewModel()

        val captured = stateObserver.captured.first()
        val capturedAsteroid = captured.asteroids.first()

        Truth.assertThat(capturedAsteroid.isDangerous).isFalse()
    }

    @Test
    fun `GIVEN AsteroidNeoWsException WHEN init THEN is not loading`() {
        coEvery { repository.getAsteroidsToday() } throws AsteroidNeoWsException()
        initViewModel()

        val captured = stateObserver.captured.first()

        Truth.assertThat(captured.isLoading).isFalse()
    }

    @Test
    fun `GIVEN AsteroidNeoWsException WHEN init THEN has error message`() {
        coEvery { repository.getAsteroidsToday() } throws AsteroidNeoWsException()
        initViewModel()

        val captured = stateObserver.captured.first()

        Truth.assertThat(captured.error).isEqualTo(GENERIC_ERROR)
    }

    private fun initViewModel() {
        viewModel = AsteroidFeedViewModel(repository, errorProvider)
        viewModel.state.observeForever(stateObserver)
    }

    companion object {
        private const val GENERIC_ERROR = "generic_error"
    }
}