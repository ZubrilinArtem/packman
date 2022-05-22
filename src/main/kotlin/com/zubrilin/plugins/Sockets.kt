package com.zubrilin.plugins

import io.ktor.serialization.kotlinx.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.time.Duration

@Serializable
data class TestExample(
    val text: String
)

fun Application.configureSockets() {
    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
        contentConverter = KotlinxWebsocketSerializationConverter(Json)
    }

    routing {
        webSocket("/") {
            sendSerialized(TestExample(text = "hi"))
//            for (frame in incoming) {
//                when (frame) {
//                    is Frame.Text -> {
//                        val text = frame.readText()
//                        outgoing.send(Frame.Text("YOU SAID: $text"))
//                        if (text.equals("bye", ignoreCase = true)) {
//                            close(CloseReason(CloseReason.Codes.NORMAL, "Client said BYE"))
//                        }
//                    }
//                }
//            }
        }

        webSocket("test"){
            val test = receiveDeserialized<TestExample>()
            println(" A test example text: ${test.text}")

//            for (frame in incoming){
//                when (frame){
//                    is Frame.Text -> {
//                        val test = receiveDeserialized<TestExample>()
//                        println(" A test example text: ${test.text}")
//                    }
//                }
//            }
        }
    }
}
