package com.klemer.githubrepos.view.fragments

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.klemer.githubrepos.R
import com.klemer.githubrepos.adapters.DetailListAdapter
import com.klemer.githubrepos.databinding.PullRequestsFragmentBinding
import com.klemer.githubrepos.interfaces.IssueOrPullRequestClickListener
import com.klemer.githubrepos.models.RepoInfoModel
import com.klemer.githubrepos.models.Repository
import com.klemer.githubrepos.view.activities.WebViewActivity
import com.klemer.githubrepos.viewmodels.PullRequestsViewModel

class PullRequestsFragment : Fragment(R.layout.pull_requests_fragment),
    IssueOrPullRequestClickListener {

    companion object {
        fun newInstance() = PullRequestsFragment()
    }

    private lateinit var viewModel: PullRequestsViewModel
    private lateinit var binding: PullRequestsFragmentBinding
    private val pullAdapter = DetailListAdapter(this)
    private lateinit var gitUsername: String
    private lateinit var gitRepoName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        gitUsername = arguments?.getString("username").toString()
        gitRepoName = arguments?.getString("repository_name").toString()

    }

    private var pullRequestObserver = Observer<List<RepoInfoModel>> {
        if (it.isNotEmpty())
            pullAdapter.update(it)
        else
            loadAnimation()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(PullRequestsViewModel::class.java)
        binding = PullRequestsFragmentBinding.bind(view)

        setupObservers()
        setupRecyclerView()
        loadInitialData()
    }

    private fun setupObservers() {
        viewModel.pullRequestList.observe(viewLifecycleOwner, pullRequestObserver)
    }

    private fun loadInitialData() {
        viewModel.getPullRequests(gitUsername, gitRepoName)
    }

    private fun setupRecyclerView() {
        binding.recyclerViewPullRequests.adapter = pullAdapter
        binding.recyclerViewPullRequests.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun loadAnimation() {
        binding.animation.root.visibility = View.VISIBLE
    }

    override fun onItemClick(item: RepoInfoModel) {
        val act = Intent(requireContext(), WebViewActivity::class.java)
        act.putExtra("url", item.url)
        act.putExtra("title", item.title)
        startActivity(act)
    }

}