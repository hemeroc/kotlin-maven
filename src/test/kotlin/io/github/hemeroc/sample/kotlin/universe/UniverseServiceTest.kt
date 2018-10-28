package io.github.hemeroc.sample.kotlin.universe

import io.github.hemeroc.sample.kotlin.universe.service.SimpleUniverseService
import org.hamcrest.core.Is.`is`
import org.junit.Assert
import org.junit.Test

class UniverseServiceTest {

    private val universeService = SimpleUniverseService()

    @Test
    fun testSimpleUniverseService() {
        Assert.assertThat(universeService.calculateAnswer(), `is`("42!"))
    }
}
