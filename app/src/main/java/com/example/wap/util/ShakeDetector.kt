package com.example.wap.util

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlin.math.sqrt

class ShakeDetector: SensorEventListener {

    private lateinit var listener: OnShakeListener
    private var shakeTimestamp: Long = 0
    private var shakeCount = 0

    fun setOnShakeListener(listener: OnShakeListener){
        this.listener = listener
    }

    companion object{
        private const val SHAKE_THRESHOLD_GRAVITY = 2.7f
        private const val SHAKE_SKIP_TIME = 500
    }


    interface OnShakeListener{
        fun onShake(count: Int)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let{
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            val gx = x / SensorManager.GRAVITY_EARTH
            val gy = y / SensorManager.GRAVITY_EARTH
            val gz = z / SensorManager.GRAVITY_EARTH

            val gForce: Float = sqrt(gx * gx + gy * gy + gz + gz)
            if(gForce > SHAKE_THRESHOLD_GRAVITY){
                val curTime = System.currentTimeMillis()
                if(shakeTimestamp + SHAKE_SKIP_TIME > curTime){
                    return
                }
                shakeTimestamp = curTime
                shakeCount++
                listener.onShake(shakeCount)
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }
}