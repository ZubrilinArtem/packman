package com.zubrilin.database.model

import org.jetbrains.exposed.sql.Table

object Relations: Table() {

    val id = integer("id").autoIncrement()
    val user = integer("user") references Users.id
    val companion = integer("companion") references Users.id
    val relations = integer("relations").default(RelationsConstants.FRIEND)

    override val primaryKey = PrimaryKey(id, name = "relations_id")

    init {
        uniqueIndex(user, companion)
    }


}
