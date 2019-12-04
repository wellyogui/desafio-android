package br.well.tembici

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.well.tembici.common.provider.FragmentLayoutProvider
import br.well.tembici.gitservice.api.GitApiModule
import br.well.tembici.ui.repository.view.controller.RepositoryFragment

class MainActivity : AppCompatActivity(), FragmentLayoutProvider {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initApi()
        openFragment()
    }

    private fun initApi() {
        GitApiModule.buildRetrofit()
    }

    private fun openFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainRootView, RepositoryFragment.newInstance())
            .commitNow()
    }

    override fun fragmentFrame(): Int = R.id.mainRootView
}
