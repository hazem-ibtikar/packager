package com.monh.packager.utils.network

@Suppress("DataClassPrivateConstructor")
data class NetworkState private constructor(val status: Status, val errorMessageRes: Int? = null) {
    companion object {
        val LOADED = NetworkState(Status.SUCCESS)
        val LOADING = NetworkState(Status.RUNNING)
        fun error(errorMessageRes: Int?) = NetworkState(Status.FAILED, errorMessageRes)
    }
}

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}
