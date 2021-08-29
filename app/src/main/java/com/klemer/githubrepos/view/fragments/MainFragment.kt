package com.klemer.githubrepos.view.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.klemer.githubrepos.R
import com.klemer.githubrepos.databinding.MainFragmentBinding
import com.klemer.githubrepos.models.GithubLanguages
import com.klemer.githubrepos.viewmodels.MainViewModel

import android.widget.AutoCompleteTextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.klemer.githubrepos.adapters.RepoListAdapter
import com.klemer.githubrepos.models.RepositoryResponse
import com.klemer.githubrepos.view.activities.MainActivity


class MainFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding
    private val repoAdapter = RepoListAdapter()

    private val repositoriesObserver = Observer<RepositoryResponse> {
        repoAdapter.update(it.repositories.toMutableList())
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = MainFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.repositoriesList.observe(viewLifecycleOwner, repositoriesObserver)

        setupRecyclerView()

        viewModel.getRepositories()
    }

    private fun setupRecyclerView() {
        binding.repositoriesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.repositoriesRecyclerView.adapter = repoAdapter

    }

}