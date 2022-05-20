package com.zubrilin

import com.zubrilin.plugins.configureRouting
import com.zubrilin.plugins.configureSecurity
import com.zubrilin.plugins.configureSerialization
import com.zubrilin.plugins.configureSockets
import io.ktor.server.application.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureRouting()
    configureSockets()
    configureSerialization()
    configureSecurity()
}
