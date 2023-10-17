package com.kursatmemis.mycontacts.repository

import androidx.lifecycle.MutableLiveData
import com.kursatmemis.mycontacts.model.Person
import com.kursatmemis.mycontacts.dao.PersonDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RoomRepository @Inject constructor(
    private val personDao: PersonDao,
) {

    private var peopleLiveData = MutableLiveData<ArrayList<Person>>()

    fun getPeopleLiveData(): MutableLiveData<ArrayList<Person>> {
        return peopleLiveData
    }

    suspend fun savePersonToRoom(person: Person) {
        personDao.insertPerson(person)
    }

    suspend fun updatePersonOnRoom(person: Person) {
        personDao.updatePerson(person)
    }

    suspend fun deletePersonFromRoom(person: Person) {
        personDao.deletePerson(person)
        getAllPeopleFromRoom()
    }

    suspend fun getAllPeopleFromRoom() {
        peopleLiveData.value = personDao.getAllPeople() as ArrayList<Person>
    }

    suspend fun deleteAllPeople() {
        personDao.deleteAllPeople()
    }

}