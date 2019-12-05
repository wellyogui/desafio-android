package br.well.tembici.common.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_repository.view.*

/**
 * Created by well_ on 05/12/2019 for tembici-challenge.
 */
fun ImageView.loadImage(imageUrl: String) {
    Glide.with(context)
        .load(imageUrl)
        .into(userImageView)

}