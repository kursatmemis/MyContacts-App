package com.kursatmemis.mycontacts.config

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kursatmemis.mycontacts.dao.PersonDao
import com.kursatmemis.mycontacts.model.Person

@Database(entities = [Person::class], version = 1)
abstract class AppDataBase : RoomDatabase(){

    abstract fun personDao() : PersonDao

}