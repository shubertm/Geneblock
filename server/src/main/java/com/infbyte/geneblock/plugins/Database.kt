package com.infbyte.geneblock.plugins

import io.ktor.server.application.Application
import java.sql.Connection
import java.sql.DriverManager

fun Application.configureDatabase(embedded: Boolean = false): Connection = connectToPostgres(embedded)

fun Application.connectToPostgres(embedded: Boolean = false): Connection {
    Class.forName("org.postgresql.Driver")
    return if (embedded) {
        val connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "root", "")

        val statement = connection.prepareStatement(DELETE_ALL_TABLES)
        statement.executeUpdate()

        connection
    } else {
        val url = environment.config.property("postgres.url").getString()
        val user = environment.config.property("postgres.user").getString()
        val password = environment.config.property("postgres.password").getString()

        return DriverManager.getConnection(url, user, password)
    }
}

// For unit tests with in memory database
private const val DELETE_ALL_TABLES = "DROP TABLE IF EXISTS genesis_blocks"
