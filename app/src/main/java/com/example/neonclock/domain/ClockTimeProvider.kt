package com.example.neonclock.domain

interface ClockTimeProvider {
    fun currentTime(pattern: String = DEFAULT_PATTERN): String

    companion object {
        const val DEFAULT_PATTERN = "HH:mm"
    }
}
