package com.monh.packager.data.remote.auth

import com.google.firebase.messaging.FirebaseMessaging
import com.monh.packager.base.BaseRepository
import com.monh.packager.utils.InformativeResponse
import com.monh.packager.utils.network.ApplicationException
import com.monh.packager.utils.network.ErrorType
import com.monh.packager.utils.network.Result
import timber.log.Timber
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userService: UserService
) : BaseRepository(){

    suspend fun signInUser(signInRequest: SignInRequest):Result<LoginResponse>{
        return safeApiCall { userService.logIn(signInRequest) }
            .let { result ->
                when (result) {
                    is Result.Success -> {
                        Result.Success(result.data.data!!)
                    }
                    is Result.Error -> result
                    else -> Result.Error(ApplicationException(type = ErrorType.Unexpected))
                }
            }
    }

    suspend fun updateUserToken(firebaseToken: String, uuid:String):Result<InformativeResponse>{
        return safeApiCall { userService.sendFireBaseToken(UserTokenRequest(deviceUuid = uuid, fb_token = firebaseToken, lang = sharedPreferencesUtils.currentLanguage)) }
            .let { result ->
                when (result) {
                    is Result.Success -> {
                        Result.Success(result.data.data!!)
                    }
                    is Result.Error -> result
                    else -> Result.Error(ApplicationException(type = ErrorType.Unexpected))
                }
            }
    }

    suspend fun changeStatus(isChecked: Boolean):Result<InformativeResponse>{
        return safeApiCall { userService.updatePackagerStatus(UpdateStatusRequest(isChecked)) }
            .let { result ->
                when (result) {
                    is Result.Success -> {
                        Result.Success(result.data.data!!)
                    }
                    is Result.Error -> result
                    else -> Result.Error(ApplicationException(type = ErrorType.Unexpected))
                }
            }
    }

    suspend fun getOrdersStatistics():Result<OrdersStatistics>{
        return safeApiCall { userService.getPackagerStatistics() }
            .let { result ->
                when (result) {
                    is Result.Success -> {
                        Result.Success(result.data.data!!)
                    }
                    is Result.Error -> result
                    else -> Result.Error(ApplicationException(type = ErrorType.Unexpected))
                }
            }
    }

    suspend fun resetPassword(resetPasswordRequest: ResetPasswordRequest):Result<InformativeResponse>{
        return safeApiCall { userService.resetPassword(resetPasswordRequest) }
            .let { result ->
                when (result) {
                    is Result.Success -> {
                        Result.Success(result.data.data!!)
                    }
                    is Result.Error -> result
                    else -> Result.Error(ApplicationException(type = ErrorType.Unexpected))
                }
            }
    }

    suspend fun logOut(uuid: String):Result<InformativeResponse>{
        return safeApiCall { userService.logOut(uuid = uuid) }
            .let { result ->
                when (result) {
                    is Result.Success -> {
                        Result.Success(result.data.data!!)
                    }
                    is Result.Error -> result
                    else -> Result.Error(ApplicationException(type = ErrorType.Unexpected))
                }
            }
    }

    suspend fun changePassword(changePasswordRequest: ChangePasswordRequest):Result<InformativeResponse>{
        return safeApiCall { userService.changePassword(changePasswordRequest) }
            .let { result ->
                when (result) {
                    is Result.Success -> {
                        Result.Success(result.data.data!!)
                    }
                    is Result.Error -> result
                    else -> Result.Error(ApplicationException(type = ErrorType.Unexpected))
                }
            }
    }

    fun isUserLoggedIn():Boolean{
        return sharedPreferencesUtils.userLoginResponse != null
    }

    fun getUser():LoginResponse?{
        return sharedPreferencesUtils.userLoginResponse
    }

    fun saveUser(user:LoginResponse){
        sharedPreferencesUtils.userLoginResponse = user
    }

    fun getStatusOnline():Boolean{
        return sharedPreferencesUtils.statusOnline
    }

    fun saveNewPackagerStatus(isChecked: Boolean) {
        sharedPreferencesUtils.statusOnline = isChecked
    }


    fun subscribeToSellerTopic() {
        val currentSellerId = sharedPreferencesUtils.userLoginResponse?.packager?.sellerId
        val currentLang = sharedPreferencesUtils.currentLanguage
        val subscriptionTopic = "${currentLang}_${currentSellerId}"
        FirebaseMessaging.getInstance()
            .subscribeToTopic(subscriptionTopic)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    sharedPreferencesUtils.currentSubscriptionTopic = subscriptionTopic
                    Timber.e("subscribeToFirebaseTopic: $subscriptionTopic")
                }
            }
    }

    fun removeUserData() {
        sharedPreferencesUtils.userLoginResponse = null
        val currentTopic = sharedPreferencesUtils.currentSubscriptionTopic
        FirebaseMessaging.getInstance().unsubscribeFromTopic(currentTopic?:"")
    }

    fun getCurrentLanguage(): String {
        return sharedPreferencesUtils.currentLanguage
    }

    /*
    * 1- change shared prefs lang
    * 2- un subscribe from old firebase topic
    * 3- subscribe to the new topic
    * */
    fun handleLanguageChange(currentLang: String) {
        sharedPreferencesUtils.currentLanguage = currentLang
        val oldFirebaseTopic = sharedPreferencesUtils.currentSubscriptionTopic
        FirebaseMessaging.getInstance()
            .unsubscribeFromTopic(oldFirebaseTopic?:"")
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    subscribeToSellerTopic()
                }
            }

    }

}