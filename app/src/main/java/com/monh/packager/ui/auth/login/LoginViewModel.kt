package com.monh.packager.ui.auth.login


import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.monh.packager.base.BaseViewModel
import com.monh.packager.data.remote.auth.SignInRequest
import com.monh.packager.data.remote.auth.UserRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val userRepository: UserRepository): BaseViewModel() {
    val loginSuccessfulLiveData:MutableLiveData<Boolean> = MutableLiveData()

    fun signIn(email:String, password:String){
        wrapBlockingOperation {
            handleResult(userRepository.signInUser(SignInRequest(email = email, password = password))){
                // save user in shared prefs
                userRepository.saveUser(it.data)
                loginSuccessfulLiveData.postValue(true)
            }
        }
    }

    fun sendTokenToServer(uuid:String) {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.d("TAG", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token

                wrapBlockingOperation(showLoading = false) { userRepository.updateUserToken(token?:"", uuid) }
            })
    }

    fun checkIfUserLoggedIn() : Boolean{
        return userRepository.isUserLoggedIn()
    }
}
