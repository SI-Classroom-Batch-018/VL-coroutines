package com.syntax_institut.android_vl_coroutines.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomeViewModel: ViewModel() {

    private var _timer = MutableLiveData<Int>(0)
    val timer: LiveData<Int>
        get() = _timer

    private var delay = 1000L
    private var isStarted = false
    private lateinit var job: Job


    fun startStop() {
        if(isStarted) {
            stop()
        } else {
            start()
        }
    }


    private fun start() {
        var counter = 0
        isStarted = true
        job = viewModelScope.launch {
            while(true) {
                delay(delay)
                counter++
                _timer.postValue(counter)
            }
        }
    }

    private fun stop() {
        job.cancel()
        isStarted = false
    }

    fun slower() {
        delay *= 2
    }

    fun faster() {
        delay /= 2
    }
}