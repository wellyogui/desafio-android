package br.well.tembici.ui.pullrequest.view.controller

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import br.well.tembici.common.factory.ControllerFactory
import br.well.tembici.common.provider.AppProvider
import br.well.tembici.common.provider.FragmentLayoutProvider
import br.well.tembici.common.view.base.BaseFragment
import timber.log.Timber

class PullRequestFragment: BaseFragment<PullRequestViewContract, PullRequestController, AppProvider, ControllerFactory>() {

    lateinit var userName: String
    lateinit var repoName: String

    companion object {
        const val EXTRA_USER_NAME = "EXTRA_USER_NAME"
        const val EXTRA_REPO_NAME = "EXTRA_REPO_NAME"

        fun newInstance(userName: String, repoName: String) = PullRequestFragment().run {
            arguments = Bundle().apply {
                putString(EXTRA_USER_NAME, userName)
                putString(EXTRA_REPO_NAME, repoName)
            }
            return@run this
        }
    }

    override val viewContract: PullRequestViewContract by lazy {
        appProvider.viewFactory.providePullRequestView(view?.parent as ViewGroup?)
    }
    override val controller: PullRequestController by lazy {
        controllerFactory.providePullRequestController(userName, repoName)
    }
    override val appProvider: AppProvider by lazy {
        AppProvider(
            (requireActivity() as AppCompatActivity),
            (requireActivity() as FragmentLayoutProvider).fragmentFrame()
        )
    }
    override val controllerFactory: ControllerFactory by lazy {
        ControllerFactory(
            appProvider.useCaseFactory,
            appProvider,
            this
        )
    }

    override fun initData() {
        arguments?.getString(EXTRA_USER_NAME)?.let {
            userName = it
        } ?: run {
            Timber.e("Bundle %s is required and was not found", EXTRA_USER_NAME)
            activity?.finish()
        }

        arguments?.getString(EXTRA_REPO_NAME)?.let {
            repoName = it
        } ?: run {
            Timber.e("Bundle %s is required and was not found", EXTRA_REPO_NAME)
            activity?.finish()
        }
    }
}
