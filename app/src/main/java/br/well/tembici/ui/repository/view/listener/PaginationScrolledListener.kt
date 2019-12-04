package br.well.tembici.ui.repository.view.listener

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by well_ on 03/12/2019 for tembici-challenge.
 */
abstract class PaginationScrolledListener(private val layoutManager: RecyclerView.LayoutManager): RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        with(layoutManager){
            val visibleItemCount = childCount
            val totalItemCount = itemCount
            val firstVisibleItemPosition = (this as LinearLayoutManager?)!!.findFirstVisibleItemPosition()

            if (!isLoading() && !isLastPage()){
                if((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0){
                    loadMoreItems()
                }
            }
        }

    }

    abstract fun isLoading(): Boolean
    abstract fun loadMoreItems()
    abstract fun isLastPage(): Boolean
}