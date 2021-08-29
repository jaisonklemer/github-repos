package com.klemer.githubrepos.adapters

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.klemer.githubrepos.R
import com.klemer.githubrepos.databinding.ListItemRepositoryBinding
import com.klemer.githubrepos.extensions.formatMin
import com.klemer.githubrepos.models.Repository
import com.klemer.githubrepos.view.fragments.MainFragment

class RepoListAdapter : RecyclerView.Adapter<RepoListViewHolder>() {
    private var repoList = mutableListOf<Repository>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoListViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_repository, parent, false)
        return RepoListViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepoListViewHolder, position: Int) {
        holder.bind(repoList[position])
    }

    override fun getItemCount() = repoList.size

    fun update(newList: MutableList<Repository>, clearList: Boolean) {
        if (clearList) {
            repoList.clear()
        }

        repoList.addAll(newList)
        notifyDataSetChanged()
    }
}

class RepoListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val binding = ListItemRepositoryBinding.bind(itemView)

    fun bind(repository: Repository) {

        binding.repoName.text = repository.name
        binding.repoDescription.text = repository.description

        binding.userAvatar.apply {
            Glide.with(this)
                .load(repository.user.avatar)
                .into(this)
        }

        binding.userName.text = repository.user.name

        binding.starsCount.text = repository.starsCount.formatMin()
        binding.forksCount.text = repository.forksCount.formatMin()

        //Change view lang color
        val unwrappedDrawable: Drawable =
            binding.langColor.background
        val wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable)

        DrawableCompat.setTint(
            wrappedDrawable,
            Color.parseColor(MainFragment.languageColor)
        )

        binding.languageName.text = repository.language

    }
}