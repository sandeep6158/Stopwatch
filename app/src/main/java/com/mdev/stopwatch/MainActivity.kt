package com.mdev.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os
import android.os.Bundle
import android.os.PersistableBundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.Chronometer

class MainActivity : AppCompatActivity() {
    lateinit var stopwatch: Chronometer
    var running = false
    var offset: Long = 0
    var offset_key = "offset"
    val running_key = "running"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stopwatch = findViewById<Chronometer>(R.id.chronometer)
        if (savedInstanceState != null) {
            offset = savedInstanceState.getLong(offset_key)
            running = savedInstanceState.getBoolean(running_key)

            settimer(0)
            if (running) {
                stopwatch.start()

            }
        }
        val one_sec = findViewById<Button>(R.id.one_sec_button)
        one_sec.setOnClickListener {
            Log.v( tag:"button click", msg: "Add 1 sec")
            setTimer(1000)
        }
        val five_sec = findViewById<Button>(R.id.five_sec_button)
        five_sec.setOnClickListener {
            Log.v( tag:"button click", msg: "Add 5 sec")
            setTimer(5000)
        }
        val ten_sec = findViewById<Button>(R.id.start_button)
        startButton.setOnClicklistener { it:View!
            if (!running) {
                setBaseTime()
                stopwatch.start()
                running = true
            }
        }
        val stopButton = findViewById<Button>(R.id.stop_button)
        stopButton.setOnClickListener {
            if(running){
                saveOffset()
                stopwatch.stop()
                running = false
            }
        }
        val resetButton = findViewById<Button>(R.id.stop_button)
        resetButton.setOnClickListener {
            offset = 0
            setBaseTime()
        }

    }
    fun settimer(value:Long){
        offset+=value
        stopwatch.base= SystemClock.elapsedRealtime() -offset
    }

    fun saveOffset() {
        stopwatch.base = SystemClock.elapsedRealtime() - offset

    }
    fun setBaseTime(){
        stopwatch.base= SystemClock.elapsedRealtime() -offset
    }
}