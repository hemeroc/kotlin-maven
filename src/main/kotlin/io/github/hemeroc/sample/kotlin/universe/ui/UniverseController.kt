package io.github.hemeroc.sample.kotlin.universe.ui

import io.github.hemeroc.sample.kotlin.universe.service.UniverseService
import io.github.hemeroc.sample.kotlin.util.logger
import javafx.animation.FadeTransition
import javafx.animation.ParallelTransition
import javafx.concurrent.Task
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.util.Duration

class UniverseController(private val universeService: UniverseService) {

    private val log = logger()

    private val fadeOutTransition = FadeTransition(ONE_SECOND)
    private val fadeInTransition = FadeTransition(THREE_SECONDS)

    @Suppress("LateinitUsage")
    @FXML
    private lateinit var btnQuestion: Button

    @Suppress("LateinitUsage")
    @FXML
    private lateinit var lblAnswer: Label

    init {
        // some transitions for a visually appealing effect
        fadeOutTransition.fromValue = VISIBLE
        fadeOutTransition.toValue = INVISIBLE
        fadeInTransition.fromValue = INVISIBLE
        fadeInTransition.toValue = VISIBLE
    }

    fun calculateAnswerButtonPressed() {
        log.trace("called calculateAnswerButtonPressed")
        // create a thread and task to prevent ui from freezing on when doing long running operations
        Thread(object : Task<String>() {
            override fun call(): String {
                return universeService.calculateAnswer()
            }

            override fun succeeded() {
                super.succeeded()
                log.debug("calculation succeeded with value {}", value)
                // update ui with animation
                lblAnswer.text = value
                fadeOutTransition.node = btnQuestion
                fadeInTransition.node = lblAnswer
                ParallelTransition(
                    fadeOutTransition,
                    fadeInTransition
                ).playFromStart()
            }
        }, "calculation").start()
    }
}

@Suppress("MagicNumber")
private val ONE_SECOND = Duration.seconds(1.0)
@Suppress("MagicNumber")
private val THREE_SECONDS = Duration.seconds(3.0)
private const val VISIBLE = 1.0
private const val INVISIBLE = 0.0
