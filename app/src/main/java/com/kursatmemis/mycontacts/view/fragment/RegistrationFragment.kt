package com.kursatmemis.mycontacts.view.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.kursatmemis.mycontacts.databinding.FragmentRegistrationBinding
import com.kursatmemis.mycontacts.model.Person
import com.kursatmemis.mycontacts.viewmodel.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment : BaseFragment<FragmentRegistrationBinding>() {

    private val registrationViewModel: RegistrationViewModel by viewModels()

    override fun setupUI() {
        binding.personSaveButton.setOnClickListener {
            val person = getPerson()
            if (person != null) {
                registrationViewModel.savePerson(person)
            }
        }
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRegistrationBinding {
        return FragmentRegistrationBinding.inflate(inflater, container, false)
    }

    private fun getPerson(): Person? {
        val personName = binding.personNameEditText.text.toString()
        val personPhone = binding.personPhoneEditText.text.toString()

        if (personName.isEmpty() || personPhone.isEmpty()) {
            val message = "Please fill in the fields."
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            return null
        } else {
            return Person(personName, personPhone)
        }

    }

}