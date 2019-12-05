package br.well.tembici.common

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import br.well.tembici.R
import br.well.tembici.ui.pullrequest.view.controller.PullRequestFragment


class ScreenNavigator(private val activity: AppCompatActivity) {

    fun toPullRequests(userName: String, repoName: String) {
        activity.supportFragmentManager.beginTransaction().apply {
            replace(R.id.mainRootView, PullRequestFragment.newInstance(userName, repoName))
            addToBackStack(PullRequestFragment::class.java.simpleName)
        }.commit()
    }

    fun toPullRequestDetails(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        activity.startActivity(browserIntent)
    }

}
