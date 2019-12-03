package br.well.tembici.ui.repository.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.well.tembici.R
import br.well.tembici.gitservice.api.model.Repository
import br.well.tembici.ui.repository.view.adapter.model.RepositoryItemAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_repository.view.*

/**
 * Created by well_ on 03/12/2019 for tembici-challenge.
 */
class RepositoriesAdapter(private val items: List<Repository>, private val listener: Listener) :
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
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: RepositoryItemAdapter) = with(itemView) {
            with(item) {
                repositoryNameView.text = repository
                repositoryDescriptionView.text = repositoryDescription
                pullRequestCountView.text = pullRequestCount.toString()
                forkCountView.text = forkCount.toString()
                userNameView.text = userName
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
}