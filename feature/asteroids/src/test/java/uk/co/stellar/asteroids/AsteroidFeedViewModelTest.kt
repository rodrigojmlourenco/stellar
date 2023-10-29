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
    fun `WHEN init THEN has the correct asteroid`() {
        val asteroid = fixture<Asteroid>()
        coEvery { repository.getAsteroidsToday() } returns listOf(asteroid)
        initViewModel()

        val captured = stateObserver.captured.first()

        Truth.assertThat(captured.asteroids).containsExactly(AsteroidUIModel(asteroid.id, asteroid.name))
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