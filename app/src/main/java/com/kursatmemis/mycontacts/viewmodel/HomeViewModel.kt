package com.kursatmemis.mycontacts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursatmemis.mycontacts.model.Person
import com.kursatmemis.mycontacts.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val roomRepository: RoomRepository) : ViewModel() {

    val peopleLiveData : MutableLiveData<ArrayList<Person>>

    init {
        loadPeople()
        peopleLiveData = roomRepository.getPeopleLiveData()
    }

    fun loadPeople() {
        viewModelScope.launch {
            roomRepository.getAllPeopleFromRoom()
        }
    }

    fun deletePerson(person: Person) {
        viewModelScope.launch {
            roomRepository.deletePersonFromRoom(person)
        }
    }

}