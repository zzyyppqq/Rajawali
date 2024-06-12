package com.zyp.learn.mock

import kotlin.math.sin


object MockWaveData {

    fun generateDoubleArray(sampleRate: Int = 1000, time: Int = 1, amplitude: Double = 500.0): DoubleArray {
        val durationSeconds = 1
        val doubleArray = DoubleArray(durationSeconds * sampleRate * time)
        for (i in 0 until time) {
            val numSamples = durationSeconds * sampleRate
            val frequency = 1.0 / durationSeconds.toDouble()

            for (j in 0 until numSamples) {
                val t = j.toDouble() / sampleRate.toDouble()
                val sample = amplitude * sin(2.0 * Math.PI * frequency * t)
                doubleArray[j + i * numSamples] = sample
            }
        }
        return doubleArray
    }
}