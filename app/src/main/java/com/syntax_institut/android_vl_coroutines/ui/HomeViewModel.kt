package com.syntax_institut.android_vl_coroutines.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomeViewModel: ViewModel() {

    // Livedata für den timerwert
    private var _timer = MutableLiveData<Int>(0)
    val timer: LiveData<Int>
        get() = _timer

    // Coroutine als Job abspeichern, damit wir mehr Kontrolle über diesen haben
    private lateinit var timerJob: Job

    // Information darüber ob gerade hochzählt oder nicht
    private var isRunnig = false

    // Delay außerhalb definieren, damit wir kontrolle über Zählgeschwindigkeit haben
    private var delay: Long = 1000

    init {
        startTimer()
    }

    // Funktion zum hochzählen den Timers
    private fun startTimer() {
        // Erstellen Counter zum Zählen initial der alte Wert
        var counter = _timer.value!!
        isRunnig = true
        // Starten Coroutine und speichern diese in dem vorher erstellen Job, damit wir mehr Kontrolle darüber haben
        timerJob = viewModelScope.launch {
            while (true) {
                // Verzögere das Zählen um delay
                delay(delay)
                // Mit Postvalue können wir die value einer livedata aus einer coroutine aktualisieren
                _timer.postValue(counter++)
            }
        }
    }

    // Für startStop
    fun toggleTimer() {
        if(isRunnig) {
            stopTimer()
        } else {
            startTimer()
        }
    }

    // Job wird cecanceld um die resourcen des nebenthreads freizugeben
    private fun stopTimer() {
        timerJob.cancel()
        isRunnig = false
        delay = 1000
    }

    fun faster() {
        delay /= 2
    }

    fun slower() {
        delay *= 2
    }
}