package com.utlis

sealed class NetworkResult<T>(val data: T? = null, val message: String? = null) {
    class Loading<T> : NetworkResult<T>()
    class Success<T>(data: T) : NetworkResult<T>(data)
    class Error<T>(message: String?, data: T? = null) : NetworkResult<T>(data, message)

}

//sealed class DataState<out T> {
//    data class Success<T>(val data: T) : DataState<T>()
//    data class Error(val errorMessage: String) : DataState<Nothing>()
//    object Loading : DataState<Nothing>()
//}