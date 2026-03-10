package com.example.neonclock.data

import com.example.neonclock.domain.ClockTimeProvider
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class SystemClockTimeProvider : ClockTimeProvider {
    override fun currentTime(pattern: String): String {
        val formatter = DateTimeFormatter.ofPattern(pattern)
        return LocalTime.now().format(formatter)
    }
}
