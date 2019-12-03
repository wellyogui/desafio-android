package br.well.tembici.common.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment<VIEW_CONTRACT : RootViewContract, CONTROLLER : BaseController<VIEW_CONTRACT>,
        APP_PROVIDER : BaseAppProvider, CONTROLLER_FACTORY> : Fragment() {

    protected abstract val viewContract: VIEW_CONTRACT
    protected abstract val controller: CONTROLLER

    abstract val appProvider: APP_PROVIDER
    abstract val controllerFactory: CONTROLLER_FACTORY

    open fun initData() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        controller.onCreate(viewContract)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return viewContract.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controller.initViews()
    }

    fun onBackPressed(): Boolean {
        return if (childFragmentManager.backStackEntryCount > 0) {
            childFragmentManager.popBackStackImmediate()
        } else false
    }
}