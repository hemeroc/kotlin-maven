package io.github.hemeroc.sample.kotlin.universe.service

import io.github.hemeroc.sample.kotlin.util.logger

import java.time.Duration

import java.time.temporal.ChronoUnit.SECONDS

class SimpleUniverseService : UniverseService {

    private val log = logger()

    override fun calculateAnswer(): String {
        log.debug("called calculateAnswer")
        // sleep to simulate heavy load
        try {
            log.trace("Going to sleep for {} seconds", SLEEP_SECONDS)
            Thread.sleep(Duration.of(SLEEP_SECONDS.toLong(), SECONDS).toMillis())
        } catch (e: InterruptedException) {
            log.warn("Failed to sleep cause {}", e.message)
        }
        return "42!"
    }
}

private const val SLEEP_SECONDS = 2
