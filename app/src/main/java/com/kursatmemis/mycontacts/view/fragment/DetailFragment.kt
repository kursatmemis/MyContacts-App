package com.kursatmemis.mycontacts.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.kursatmemis.mycontacts.databinding.FragmentDetailBinding
import com.kursatmemis.mycontacts.model.Person
import com.kursatmemis.mycontacts.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private lateinit var person: Person
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle: DetailFragmentArgs by navArgs()
        person = bundle.person
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(inflater, container, false)
    }

    override fun setupUI() {
        setNameAndPhoneOnEditText()

        binding.updatePersonButton.setOnClickListener {
            updatePerson()
            detailViewModel.updatePerson(person)
        }
    }

    private fun updatePerson() {
        val personName = binding.personNameEditText.text.toString()
        val personPhone = binding.personPhoneEditText.text.toString()

        if (personName.isEmpty() || personPhone.isEmpty()) {
            val message = "Please fill in the fields."
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        } else {
            person.personName = personName
            person.personPhone = personPhone
        }
    }

    private fun setNameAndPhoneOnEditText() {
        binding.personNameEditText.setText(person.personName)
        binding.personPhoneEditText.setText(person.personPhone)
    }

}