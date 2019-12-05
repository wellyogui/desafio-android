package br.well.tembici.common

import androidx.appcompat.app.AppCompatActivity
import br.well.tembici.R
import br.well.tembici.ui.pullrequest.view.controller.PullRequestFragment

class ScreenNavigator(private val activity: AppCompatActivity) {

    fun toPullRequests(userName: String, repoName: String) {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.mainRootView, PullRequestFragment.newInstance(userName, repoName))
            .commitNow()
    }

}
