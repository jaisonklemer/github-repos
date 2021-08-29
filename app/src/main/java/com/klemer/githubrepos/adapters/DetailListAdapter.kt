package com.klemer.githubrepos.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.klemer.githubrepos.R
import com.klemer.githubrepos.databinding.ListItemRepoInfoBinding
import com.klemer.githubrepos.interfaces.IssueOrPullRequestClickListener
import com.klemer.githubrepos.models.RepoInfoModel

class DetailListAdapter(private val click: IssueOrPullRequestClickListener) :
    RecyclerView.Adapter<DetailListViewHolder>() {

    private var data = mutableListOf<RepoInfoModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_repo_info, parent, false)
        return DetailListViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailListViewHolder, position: Int) {
        holder.bind(data[position])
        holder.itemView.setOnClickListener { click.onItemClick(data[position]) }
    }

    override fun getItemCount() = data.size

    fun update(info: List<RepoInfoModel>) {
        data = info.toMutableList()
        notifyDataSetChanged()
    }
}

class DetailListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = ListItemRepoInfoBinding.bind(itemView)

    fun bind(i: RepoInfoModel) {
        binding.title.text = i.title
        binding.description.text = i.description
        binding.userName.text = i.user.name

        binding.userAvatar.apply {
            Glide.with(this)
                .load(i.user.avatar)
                .into(this)
        }
    }
}