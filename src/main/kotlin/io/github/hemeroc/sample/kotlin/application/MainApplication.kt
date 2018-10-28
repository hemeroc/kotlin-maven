package io.github.hemeroc.sample.kotlin.application

import io.github.hemeroc.sample.kotlin.universe.service.SimpleUniverseService
import io.github.hemeroc.sample.kotlin.universe.ui.UniverseController
import io.github.hemeroc.sample.kotlin.util.logger
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

class MainApplication : Application() {

    private val log = logger()

    @Throws(Exception::class)
    override fun start(primaryStage: Stage) {
        log.debug("Application starting with arguments={}", parameters.named)

        // setup application
        primaryStage.title = "Universe Calculator"
        primaryStage.width = APPLICATION_WIDTH
        primaryStage.height = APPLICATION_HEIGHT
        primaryStage.centerOnScreen()
        primaryStage.setOnCloseRequest { log.debug("Application shutdown initiated") }

        // initiate service and controller
        val universeService = SimpleUniverseService()
        val universeController = UniverseController(universeService)

        // prepare fxml loader to inject controller
        val fxmlLoader = FXMLLoader(javaClass.getResource("/fxml/universe.fxml"))
        fxmlLoader.setControllerFactory {
            if (it.isInstance(universeController)) {
                universeController
            } else {
                null
            }
        }
        primaryStage.scene = Scene(fxmlLoader.load<Parent>())

        // show application
        primaryStage.show()
        primaryStage.toFront()
        log.debug("Application startup complete")
    }
}

fun main(args: Array<String>) {
    Application.launch(MainApplication::class.java, *args)
}

private const val APPLICATION_WIDTH = 1366.0
private const val APPLICATION_HEIGHT = 768.0
