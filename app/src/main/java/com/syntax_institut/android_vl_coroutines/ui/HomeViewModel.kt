package com.syntax_institut.android_vl_coroutines.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    // Live Data um Timer Wert zu beinhalten
    private var _timer = MutableLiveData<Int>(0)
    val timer: LiveData<Int>
        get() = _timer

    // Der Job wird als lateinit Variable angelegt
    private lateinit var timerJob: Job

    // Boolean um zu prüfen ob Timer läuft
    private var isTimerRunning = false

    // Delay-Variable wird gesetzt um diese später anzupassen
    private var delay = 1000L

    // Init-Block um den Timer zu starten
    // Wichtig ist: Alle Variablen, die die Init-Funktion benötigt müssen bereits vorher angelegt worden sein
    init {
        startTimer()
    }

    // Funktion um Timer zu starten
    private fun startTimer() {

        // Werte werden auf 0 gesetzt
        _timer.value = 0
        var count = 0
        // Boolean, der anzeigt ob Timer bereits läuft
        isTimerRunning = true

        // Coroutine wird gestartet
        timerJob = viewModelScope.launch {
            // while true, damit Timer immer weiterläuft
            while (true) {
                // delay um eine Sekunde zu warten
                delay(delay)
                // Count wird erhöht
                count++
                // Wert von Count wird in die LiveData gepostet
                _timer.postValue(count)
            }
        }

    }

    // Funktion um Timer-Job zu stoppen
    private fun stopTimer() {
        timerJob.cancel()
        isTimerRunning = false
    }

    // Funktion um Timer entweder zu stoppen oder zu starten
    fun toggleTimer() {
        if (!isTimerRunning) {
            startTimer()
        } else {
            stopTimer()
        }
    }

    // Funktion um Delay des Timers zu verändern
    fun faster() {
        delay /= 2
    }

}