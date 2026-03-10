package com.example.neonclock

import com.example.neonclock.data.SystemClockTimeProvider
import org.junit.Assert.assertTrue
import org.junit.Test

class SystemClockTimeProviderTest {
    @Test
    fun currentTime_returnsFormattedTime() {
        val provider = SystemClockTimeProvider()
        val value = provider.currentTime("HH:mm")
        assertTrue("Expected HH:mm format", Regex("^\\d{2}:\\d{2}$").matches(value))
    }
}
