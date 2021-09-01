package com.klemer.githubrepos.view.fragments

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.klemer.githubrepos.R
import com.klemer.githubrepos.adapters.DetailListAdapter
import com.klemer.githubrepos.databinding.IssuesFragmentBinding
import com.klemer.githubrepos.interfaces.IssueOrPullRequestClickListener
import com.klemer.githubrepos.models.RepoInfoModel
import com.klemer.githubrepos.view.activities.WebViewActivity
import com.klemer.githubrepos.viewmodels.IssuesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IssuesFragment : Fragment(R.layout.issues_fragment), IssueOrPullRequestClickListener {

    companion object {
        fun newInstance() = IssuesFragment()
    }

    private lateinit var viewModel: IssuesViewModel
    private lateinit var binding: IssuesFragmentBinding
    private val issuesAdapter = DetailListAdapter(this)
    private lateinit var gitUsername: String
    private lateinit var gitRepoName: String

    private var issuesObserver = Observer<List<RepoInfoModel>> {
        if (it.isNotEmpty())
            issuesAdapter.update(it)
        else
            loadAnimation()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gitUsername = arguments?.getString("username").toString()
        gitRepoName = arguments?.getString("repository_name").toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[IssuesViewModel::class.java]
        binding = IssuesFragmentBinding.bind(view)

        setupObservers()
        setupRecyclerView()
        loadInitialData()

    }

    private fun setupObservers() {
        viewModel.issuesList.observe(viewLifecycleOwner, issuesObserver)
    }

    private fun loadAnimation() {
        binding.animation.root.visibility = View.VISIBLE
    }

    private fun loadInitialData() {
        viewModel.getIssues(gitUsername, gitRepoName)
    }

    private fun setupRecyclerView() {
        binding.recyclerViewIssues.adapter = issuesAdapter
        binding.recyclerViewIssues.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onItemClick(item: RepoInfoModel) {
        val act = Intent(requireContext(), WebViewActivity::class.java)
        act.putExtra("url", item.url)
        act.putExtra("title", item.title)
        startActivity(act)
    }
}