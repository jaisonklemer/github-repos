package com.klemer.githubrepos.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import com.klemer.githubrepos.R
import com.klemer.githubrepos.repositories.GithubRepository
import com.klemer.githubrepos.view.fragments.FiltersDialogFragment
import com.klemer.githubrepos.view.fragments.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.root_container, MainFragment.newInstance())
                .commitNow()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.app_bar_items, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.btnFilter -> {
                FiltersDialogFragment.newInstance().show(supportFragmentManager, "")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}