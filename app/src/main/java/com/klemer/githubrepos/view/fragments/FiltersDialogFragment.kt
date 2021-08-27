package com.klemer.githubrepos.view.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.klemer.githubrepos.R
import com.klemer.githubrepos.databinding.FiltersDialogFragmentBinding
import com.klemer.githubrepos.extensions.setWidthPercent
import com.klemer.githubrepos.models.GithubLanguages

class FiltersDialogFragment : DialogFragment(R.layout.filters_dialog_fragment) {

    companion object {
        fun newInstance() = FiltersDialogFragment()
    }

    private lateinit var viewModel: FiltersDialogViewModel
    private lateinit var binding: FiltersDialogFragmentBinding

    private val observerLanguagesList = Observer<List<GithubLanguages>> {
        configureAutoComplete(it)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(FiltersDialogViewModel::class.java)
        binding = FiltersDialogFragmentBinding.bind(view)

        setWidthPercent(80, 80)
        viewModel.fetchAllLangsFromDB(requireContext())

        loadObservers()
    }

    private fun loadObservers(){
        viewModel.languagesList.observe(viewLifecycleOwner, observerLanguagesList)
    }

    private fun configureAutoComplete(languages: List<GithubLanguages>) {
        val languagesName = mutableListOf<String>()

        languages.forEach {
            languagesName.add(it.name)
        }

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line, languagesName
        )

        val autoCompleteTextView = binding.autoComplete
        autoCompleteTextView.setAdapter(adapter)

        autoCompleteTextView.setOnItemClickListener { adapterView, p1, position, p3 ->
            val selection = adapterView.getItemAtPosition(position).toString()
            println(selection)
        }

    }


}