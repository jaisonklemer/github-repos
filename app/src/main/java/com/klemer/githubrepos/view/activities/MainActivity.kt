package com.klemer.githubrepos.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import com.klemer.githubrepos.R
import com.klemer.githubrepos.view.fragments.MainFragment

class MainActivity : AppCompatActivity() {
    private var mainFragment = MainFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.root_container, mainFragment)
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
                mainFragment.showFilters()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}