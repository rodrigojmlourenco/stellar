package uk.co.stellar.asteroids

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AsteroidFeedViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableLiveData(State(emptyList(), true, null))
    internal val state : LiveData<State> = _state

    data class State(
        val asteroids: List<AsteroidUIModel>,
        val isLoading: Boolean = false,
        val error: String? = null
    )
}