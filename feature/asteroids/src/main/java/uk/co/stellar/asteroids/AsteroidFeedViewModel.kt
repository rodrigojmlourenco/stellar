package uk.co.stellar.asteroids

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uk.co.stellar.core.ktx.ErrorStringProvider
import uk.co.stellar.domain.Asteroid
import uk.co.stellar.domain.AsteroidNeoWsException
import uk.co.stellar.domain.AsteroidNeoWsRepository
import javax.inject.Inject

@HiltViewModel
class AsteroidFeedViewModel @Inject constructor(
    private val repository: AsteroidNeoWsRepository,
    private val errorStringProvider: ErrorStringProvider
) : ViewModel() {

    private val _state = MutableLiveData(State(emptyList(), true, null))
    internal val state: LiveData<State> = _state

    init {
        viewModelScope.launch {
            try {
                val asteroids = repository.getAsteroidsToday().toUIModel()
                updateState(newAsteroids = asteroids)
            } catch (e: AsteroidNeoWsException) {
                updateState(error = errorStringProvider.getGenericError())
            }
        }
    }

    private fun updateState(
        newAsteroids: List<AsteroidUIModel>? = null,
        isLoading: Boolean? = null,
        error: String? = null
    ) {
        _state.value?.let { original ->
            _state.value = original.copy(
                asteroids = newAsteroids ?: original.asteroids,
                isLoading = isLoading ?: false,
                error = error
            )
        }
    }

    private fun Asteroid.toUIModel(): AsteroidUIModel {
        return AsteroidUIModel(this.id, this.name)
    }

    private fun List<Asteroid>.toUIModel(): List<AsteroidUIModel> {
        return this.map { it.toUIModel() }
    }

    data class State(
        val asteroids: List<AsteroidUIModel>,
        val isLoading: Boolean = false,
        val error: String? = null
    )
}