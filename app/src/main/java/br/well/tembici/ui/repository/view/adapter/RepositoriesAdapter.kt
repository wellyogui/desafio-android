package br.well.tembici.ui.repository.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.well.tembici.R
import br.well.tembici.ui.repository.view.adapter.model.RepositoryItemAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_repository.view.*
import java.util.Collections.addAll

/**
 * Created by well_ on 03/12/2019 for tembici-challenge.
 */
open class RepositoriesAdapter(private val items: List<RepositoryItemAdapter>, private val listener: Listener) :
    RecyclerView.Adapter<RepositoriesAdapter.ViewHolder>() {

    interface Listener {
        fun onRepositoryClicked()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_repository, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.bindViewHolder(holder, position)
        with(holder.itemView) {
            rootAdapterView.setOnClickListener {
                listener.onRepositoryClicked()
            }
        }

        holder.bind(items[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: RepositoryItemAdapter) = with(itemView) {
            with(item) {
                repositoryNameView.text = repository
                repositoryDescriptionView.text = repositoryDescription
                starCountView.text = starCount.toString()
                forkCountView.text = forkCount.toString()
                userNickNameView.text = userNickname

                Glide.with(context)
                    .load(userImage)
                    .placeholder(R.drawable.octocat)
                    .into(userImageView)
            }
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