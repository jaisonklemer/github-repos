package com.klemer.githubrepos.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class RepositoryDetailsPageAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private var fragList = mutableListOf<Fragment>()

    private val titles = listOf("Pull Requests", "Open Issues")

    override fun getCount() = fragList.size

    override fun getItem(position: Int): Fragment {
        return fragList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

    fun update(fragments: List<Fragment>) {
        fragList = fragments.toMutableList()
        notifyDataSetChanged()
    }
}