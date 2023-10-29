package uk.co.stellar.test.presentation.livedata

import androidx.lifecycle.Observer

class TestObserver<T> : Observer<T> {

    private val _captured = mutableListOf<T>()
    val captured: List<T> = _captured
    override fun onChanged(value: T) {
        _captured.add(value)
    }
}