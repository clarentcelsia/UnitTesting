package com.example.unittesting.util

data class Response<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <X> success(data: X?): Response<X> {
            return Response(Status.SUCCESS, data, null)
        }

        fun <X> error(message: String, data: X?): Response<X> {
            return Response(Status.ERROR, data, message)
        }

        fun <X> loading(data: X?): Response<X> {
            return Response(Status.LOADING, data, null)
        }
    }
}

enum class Status{
    SUCCESS,
    LOADING,
    ERROR
}