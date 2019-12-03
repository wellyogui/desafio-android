package br.well.tembici.ui.repository.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView
import br.well.tembici.R
import br.well.tembici.ui.repository.view.adapter.model.RepositoryItemAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_repository.view.*

/**
 * Created by well_ on 03/12/2019 for tembici-challenge.
 */
open class RepositoriesAdapter(
    private val items: ArrayList<RepositoryItemAdapter>,
    private val listener: Listener
) :
    RecyclerView.Adapter<RepositoriesAdapter.ViewHolder>() {

    interface Listener {
        fun onRepositoryClicked()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_repository, parent, false)
        return ViewHolder(view)
    }

    @CallSuper
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.itemView) {
            rootAdapterView.setOnClickListener {
                listener.onRepositoryClicked()
            }
        }

        holder.bind(items[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: RepositoryItemAdapter) = with(itemView) {
            repositoryNameView.text = item.repository
            repositoryDescriptionView.text = item.repositoryDescription
            starCountView.text = item.starCount.toString()
            forkCountView.text = item.forkCount.toString()
            userNickNameView.text = item.userNickname

            Glide.with(context)
                .load(item.userImage)
                .placeholder(R.drawable.octocat)
                .into(userImageView)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    open fun add(items: ArrayList<RepositoryItemAdapter>, firstPage: Boolean = false) {
        val size = this.items.size
        size + items.size
        with(this.items) {
            addAll(items)
        }
        notifyDataSetChanged()
    }
}