package br.well.tembici.gitservice.api

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit

/**
 * Created by well_ on 03/12/2019 for tembici-challenge.
 */
object GitApiModule {

    val gson: Gson by lazy {
        val gsonBuilder = gsonBuilder(FieldNamingPolicy.IDENTITY)
        registerTypeAdapter(gsonBuilder)
            .create()
    }

    lateinit var retrofit: Retrofit

    fun buildRetrofit(okHttpClient: OkHttpClient,
                      converterFactory: Converter.Factory,
                      adapterFactory: CallAdapter.Factory): Retrofit {

        val baseUrl = ""
        retrofit = Retrofit.Builder().client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(adapterFactory).build()

        return retrofit
    }

    private fun gsonBuilder(fieldNamePolicy: FieldNamingPolicy): GsonBuilder {
        return GsonBuilder()
            .setFieldNamingPolicy(fieldNamePolicy)
            .setPrettyPrinting()
    }

    private fun registerTypeAdapter(gsonBuilder: GsonBuilder): GsonBuilder {
        return gsonBuilder
    }
}