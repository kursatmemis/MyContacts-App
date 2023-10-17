package com.kursatmemis.mycontacts.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.kursatmemis.mycontacts.R
import com.kursatmemis.mycontacts.view.adapter.PersonAdapter
import com.kursatmemis.mycontacts.databinding.FragmentHomeBinding
import com.kursatmemis.mycontacts.model.Person
import com.kursatmemis.mycontacts.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private lateinit var personAdapter: PersonAdapter
    private val homeViewModel: HomeViewModel by viewModels()
    private var personList = ArrayList<Person>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        observeLiveData()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun setupUI() {
        personAdapter = PersonAdapter(requireContext(), personList, homeViewModel)
        binding.myContactsListView.adapter = personAdapter

        binding.goToRegistractionFragmentFab.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_registrationFragment)
        }

        binding.myContactsListView.setOnItemClickListener { _, view, position, _ ->
            val person = personList[position]
            navigateToDetailFragmentWithPerson(view, person)
        }
    }

    private fun observeLiveData() {
        homeViewModel.peopleLiveData.observe(viewLifecycleOwner) {
            personList = it
            personAdapter.updateAdapter(personList)
        }
    }

    private fun navigateToDetailFragmentWithPerson(view: View, person: Person) {
        val navDirections = HomeFragmentDirections.actionHomeFragmentToDetailFragment(person)
        Navigation.findNavController(view).navigate(navDirections)
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.loadPeople()
    }

}