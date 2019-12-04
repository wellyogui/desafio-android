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

    private var isLoadingAdded: Boolean = false

    interface Listener {
        fun onRepositoryClicked()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = when (viewType) {
            ViewHolderType.LOADING.typeId -> {
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_repository_progress, parent, false)
            }
            ViewHolderType.ITEM.typeId -> {
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_repository, parent, false)
            }
            else -> throw IllegalArgumentException("You must pass viewType as LOADING or ITEM")
        }
        return ViewHolder(view)
    }

    @CallSuper
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.itemView) {
            rootAdapterView.setOnClickListener {
                listener.onRepositoryClicked()
            }
        }

        if (getItemViewType(position) == ViewHolderType.ITEM.typeId) {
            holder.bind(items[position])
        }
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

    inner class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (position == items.size - 1 && isLoadingAdded) {
            true -> ViewHolderType.LOADING.typeId
            false -> ViewHolderType.ITEM.typeId
        }
    }

    open fun addAll(items: ArrayList<RepositoryItemAdapter>, firstPage: Boolean = false) {
        val size = this.items.size
        size + items.size
        with(this.items) {
            addAll(items)
        }
        notifyDataSetChanged()
    }

    fun add(repository: RepositoryItemAdapter) {
        items.add(repository)
        notifyItemInserted(items.size - 1)
    }

    fun showLoading() {
        add(RepositoryItemAdapter("fake repo", "", "", "", 0, 0))
    }

    fun hideLoading() {
        val position = items.size - 1
        val result = items[position]

        items.remove(result)
        notifyItemRemoved(position)
    }
}

enum class ViewHolderType(val typeId: Int) {
    LOADING(0),
    ITEM(1)
}