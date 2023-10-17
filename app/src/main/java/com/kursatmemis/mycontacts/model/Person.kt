package com.kursatmemis.mycontacts.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "person_table")
data class Person(
    @ColumnInfo(name = "person_name")
    var personName: String,
    @ColumnInfo(name = "person_phone")
    var personPhone: String
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    private var personId: Int? = null

    fun getPersonId(): Int? {
        return personId
    }

    fun setPersonId(id: Int?) {
        personId = id
    }

}
