package com.kursatmemis.mycontacts.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kursatmemis.mycontacts.model.Person

@Dao
interface PersonDao {

    @Insert
    suspend fun insertPerson(person: Person): Long // Eklenen öğenin primary key'ini return eder.

    @Delete
    suspend fun deletePerson(person: Person): Int // Etkilenen satır sayısını return eder.

    @Insert
    suspend fun insertPeople(people: List<Person>): List<Long> // Eklenen öğelerin primary key'lerini return eder.

    @Query("DELETE FROM person_table")
    suspend fun deleteAllPeople()

    @Update
    suspend fun updatePerson(person: Person): Int // Etkilenen satır sayısını return eder.

    @Query("Select * FROM person_table")
    suspend fun getAllPeople(): List<Person>

}