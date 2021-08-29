package com.klemer.githubrepos.view.fragments

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import com.klemer.githubrepos.R
import com.klemer.githubrepos.databinding.MainFragmentBinding
import com.klemer.githubrepos.viewmodels.MainViewModel

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.klemer.githubrepos.adapters.RepoListAdapter
import com.klemer.githubrepos.extensions.hideKeyboard
import com.klemer.githubrepos.interfaces.RepositoryClickListener
import com.klemer.githubrepos.models.GithubLanguages
import com.klemer.githubrepos.models.Repository
import com.klemer.githubrepos.models.RepositoryResponse
import com.klemer.githubrepos.singletons.APICount
import com.klemer.githubrepos.view.activities.MainActivity
import com.klemer.githubrepos.view.activities.RepositoryDetailsActivity


class MainFragment : Fragment(R.layout.main_fragment), RepositoryClickListener {

    companion object {
        fun newInstance() = MainFragment()

        var languageColor = "#A97BFF"
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding
    private val repoAdapter = RepoListAdapter(this)
    private var selectedLang = "Kotlin"

    private val repositoriesObserver = Observer<RepositoryResponse> {
        showProgress(false)
        if (APICount.loadMore)
            repoAdapter.update(it.repositories.toMutableList(), false)
        else
            repoAdapter.update(it.repositories.toMutableList(), true)

        changeActivityTitle()

    }

    private val languagesObserver = Observer<List<GithubLanguages>> {
        configureAutoComplete(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = MainFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        loadObservers()
        setupRecyclerView()
        loadInitialData()
        addClickListeners()
        addRecyclerViewScrollListener()

    }

    private fun loadInitialData() {
        viewModel.fetchAllLangsFromDB(requireContext())
        viewModel.getRepositories(selectedLang)
    }

    private fun loadObservers() {
        viewModel.repositoriesList.observe(viewLifecycleOwner, repositoriesObserver)
        viewModel.languagesList.observe(viewLifecycleOwner, languagesObserver)

    }

    private fun setupRecyclerView() {
        binding.repositoriesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.repositoriesRecyclerView.adapter = repoAdapter

    }

    fun showFilters() {
        val actual = binding.includeFilter.root.visibility
        if (actual == View.GONE) binding.includeFilter.root.visibility = View.VISIBLE
        else
            binding.includeFilter.root.visibility = View.GONE
    }

    private fun configureAutoComplete(languages: List<GithubLanguages>) {

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line, languages
        )

        val autoCompleteTextView = binding.includeFilter.autoComplete
        autoCompleteTextView.setAdapter(adapter)

        autoCompleteTextView.setOnItemClickListener { adapterView, p1, position, p3 ->
            selectedLang = adapterView.getItemAtPosition(position).toString()
            val lang = adapterView.getItemAtPosition(position) as GithubLanguages
            languageColor = lang.color!!
        }
    }

    private fun showProgress(visible: Boolean) {
        binding.progressBar.visibility = if (visible) View.VISIBLE else View.GONE
    }

    private fun filterRepos() {
        showProgress(true)
        resetCounts()
        viewModel.getRepositories(selectedLang)
    }

    private fun resetCounts() {
        APICount.page = 1
        APICount.loadMore = false
    }

    private fun addClickListeners() {
        binding.includeFilter.buttonSearch.setOnClickListener {
            requireContext().hideKeyboard(it)
            filterRepos()
        }
    }

    private fun addRecyclerViewScrollListener() {
        binding.repositoriesRecyclerView.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) &&
                    newState == RecyclerView.SCROLL_STATE_IDLE
                ) {
                    showProgress(true)
                    viewModel.getRepositories(selectedLang)
                    APICount.loadMore = true
                }
            }
        })
    }

    override fun onRepositoryItemClick(repository: Repository) {
        val act = Intent(requireContext(), RepositoryDetailsActivity::class.java)
        act.putExtra("gitUser", repository.user.name)
        act.putExtra("gitRepo", repository.name)
        startActivity(act)
    }

    private fun changeActivityTitle() {
        (requireActivity() as MainActivity).changeAppBarTitle("Repos in $selectedLang")
    }


}