package com.dicoding.etrash.pref

//sealed class HelperStat <out R> private constructor(){
//    object Loading : HelperStat<Nothing>()
//    data class Success<out T>(val data: T) : HelperStat<T>()
//    data class Error(val error: String) : HelperStat<Nothing>()
//}

sealed class HelperStat<out T> {
    object Loading : HelperStat<Nothing>()
    data class Success<out T>(val data: T) : HelperStat<T>()
    data class Error(val error: String) : HelperStat<Nothing>()
}
