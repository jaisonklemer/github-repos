package com.klemer.githubrepos.view.activities

import android.os.Bundle
import com.klemer.githubrepos.adapters.RepositoryDetailsPageAdapter
import com.klemer.githubrepos.databinding.ActivityRepositoryDetailsBinding
import com.klemer.githubrepos.models.Repository
import com.klemer.githubrepos.view.fragments.IssuesFragment
import com.klemer.githubrepos.view.fragments.MainFragment
import com.klemer.githubrepos.view.fragments.PullRequestsFragment

class RepositoryDetailsActivity : BaseActivity() {
    private lateinit var binding: ActivityRepositoryDetailsBinding
    private lateinit var pagerAdapter: RepositoryDetailsPageAdapter
    private lateinit var gitUser: String
    private lateinit var gitRepo: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepositoryDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getIntentExtra()
        setupActionBar()
        setupViewPager()
    }

    private fun getIntentExtra() {
        gitUser = intent.getStringExtra("gitUser").toString()
        gitRepo = intent.getStringExtra("gitRepo").toString()
    }

    private fun setupActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Repository: $gitRepo"
    }

    private fun setupViewPager() {
        pagerAdapter = RepositoryDetailsPageAdapter(supportFragmentManager)
        binding.viewPager.adapter = pagerAdapter

        val pullRequestsFragment = PullRequestsFragment.newInstance()
        val issuesFragment = IssuesFragment.newInstance()

        val args = Bundle()
        args.putString("username", gitUser)
        args.putString("repository_name", gitRepo)

        pullRequestsFragment.arguments = args
        issuesFragment.arguments = args

        pagerAdapter.update(
            listOf(pullRequestsFragment, issuesFragment)
        )
        binding.tabs.setupWithViewPager(binding.viewPager)
    }

}