package br.well.tembici.common.util

open class Resource<out T> constructor(val status: ResourceState? = null,
                                       val data: T? = null,
                                       val message: String? = null,
                                       val loading: Boolean = false,
                                       val callback: (() -> Unit)? = null) {
    companion object {
        fun <T> loading(loading: Boolean): Resource<T> = Resource(ResourceState.LOADING, loading = loading)

        fun <T> success(data: T): Resource<T> = Resource(ResourceState.SUCCESS, data = data)

        //region Use error with message if the caller is a View Model
        fun <T> error(message: String?, callback: (() -> Unit)?): Resource<T> =
                Resource(ResourceState.ERROR, message = message, callback = callback)
    }
}


enum class ResourceState {
    LOADING, SUCCESS, ERROR
}