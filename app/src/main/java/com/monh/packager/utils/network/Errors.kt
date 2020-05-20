package com.monh.packager.utils.network

class ApplicationException(
    val type: ErrorType,
    val errorMessage: String? = null,
    val errorMessageRes: Int? = null,
    val throwable: Throwable? = null,
    val tag:String = ""
) : RuntimeException()

sealed class ErrorType {
    sealed class Network(Code: Int) : ErrorType() {
        object Unauthorized : Network(401)
        object ResourceNotFound : Network(404)
        object Unexpected : Network(-1)
        object NoInternetConnection : Network(-2)
    }

    object Unexpected : ErrorType()
    object UserError : ErrorType()

    data class Validation(val errors: HashMap<Int, Int?> = hashMapOf()) : ErrorType()
}
