package br.well.tembici.ui.pullrequest.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.well.tembici.R
import br.well.tembici.common.ext.loadImage
import br.well.tembici.common.ext.toHumanDate
import br.well.tembici.ui.pullrequest.view.adapter.model.PullRequestItemAdapter
import kotlinx.android.synthetic.main.item_pull_request.view.*

/**
 * Created by well_ on 05/12/2019 for tembici-challenge.
 */
class PullRequestsAdapter(val items: ArrayList<PullRequestItemAdapter>): RecyclerView.Adapter<PullRequestsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pull_request, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addAll(pullRequestItemsAdapter: ArrayList<PullRequestItemAdapter>) {
        val size = items.size
        size + pullRequestItemsAdapter.size
        with(items) {
            addAll(pullRequestItemsAdapter)
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(item: PullRequestItemAdapter) {
            with(itemView) {
                userNickNameView.text = item.userName
                pullRequestNameView.text = item.pullRequestTitle
                pullRequestDescriptionView.text = item.pullRequestDescription
                pullRequestDate.text = "Opened on ${item.pullRequestDate.toHumanDate()}"
                userImageView.loadImage(item.userImage)
            }
        }
    }

}

