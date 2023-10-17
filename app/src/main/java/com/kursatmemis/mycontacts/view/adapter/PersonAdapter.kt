package com.kursatmemis.mycontacts.view.adapter

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.kursatmemis.mycontacts.R
import com.kursatmemis.mycontacts.databinding.ItemMyContactBinding
import com.kursatmemis.mycontacts.model.Person
import com.kursatmemis.mycontacts.util.AlertDialogUtil
import com.kursatmemis.mycontacts.viewmodel.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonAdapter constructor(
    context: Context,
    private val personList: ArrayList<Person>,
    private val homeViewModel: HomeViewModel
) :
    ArrayAdapter<Person>(context, R.layout.item_my_contact, personList) {

    private lateinit var binding: ItemMyContactBinding

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        if (convertView == null) {
            val inflater = LayoutInflater.from(context)
            binding = ItemMyContactBinding.inflate(inflater, parent, false)
        } else {
            binding = ItemMyContactBinding.bind(convertView)
        }

        val person = personList[position]

        binding.personNameTextView.text = person.personName
        binding.personPhoneTextView.text = person.personPhone
        binding.deletePersonImageView.setOnClickListener {
            val alertDialog = createAlertDialog(person)
            alertDialog.show()
        }

        return binding.root
    }

    fun updateAdapter(personList: ArrayList<Person>) {
        clear()
        addAll(personList)
        notifyDataSetChanged()
    }

    private fun createAlertDialog(person: Person): AlertDialog.Builder {
        val positiveButton = DialogInterface.OnClickListener { _, _ ->
            CoroutineScope(Dispatchers.IO).launch {
                homeViewModel.deletePerson(person)
            }
        }

        val alertDialogUtil = AlertDialogUtil(
            context,
            "Are You Sure?",
            "Are you sure to remove this record from your contacts?",
            positiveButton
        )

        return alertDialogUtil.createAlertDialog()
    }

}