package com.zubrilin

import com.zubrilin.database.model.Relations
import com.zubrilin.database.model.Users
import com.zubrilin.plugins.configureRouting
import com.zubrilin.plugins.configureSecurity
import com.zubrilin.plugins.configureSerialization
import com.zubrilin.plugins.configureSockets
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>) {

    Database.connect("jdbc:postgresql://localhost:5432/packman", driver = "org.postgresql.Driver",
    user = "postgres", password = "")

    transaction {

        addLogger(StdOutSqlLogger)

        SchemaUtils.create(Users)
        SchemaUtils.create(Relations)
    }

    io.ktor.server.netty.EngineMain.main(args)

}


@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureRouting()
    configureSockets()
    configureSerialization()
    configureSecurity()
}
