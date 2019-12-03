package br.well.tembici

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.well.tembici.gitservice.api.GitApiModule

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initApi()
    }

    fun initApi(){
        GitApiModule.buildRetrofit()
    }
}
