package com.kursatmemis.mycontacts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursatmemis.mycontacts.model.Person
import com.kursatmemis.mycontacts.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val roomRepository: RoomRepository) : ViewModel() {

    fun updatePerson(person: Person) {
        viewModelScope.launch {
            roomRepository.updatePersonOnRoom(person)
        }
    }

}