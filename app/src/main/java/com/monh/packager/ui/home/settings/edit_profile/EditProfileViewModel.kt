package com.monh.packager.ui.home.settings.edit_profile

import androidx.lifecycle.MutableLiveData
import com.monh.packager.base.BaseViewModel
import com.monh.packager.data.remote.auth.Packager
import com.monh.packager.data.remote.auth.UserRepository
import com.monh.packager.utils.Event
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject


class EditProfileViewModel @Inject constructor(var userRepository: UserRepository) : BaseViewModel() {
    var selectedImagePath = ""
    var updatedSuccessfully:MutableLiveData<Event<Boolean>> = MutableLiveData()
    var userLiveDate:MutableLiveData<Packager> = MutableLiveData()
    fun editProfile(packagerName:String, packagerPhone:String){
        if (selectedImagePath.isNotEmpty()){
            //Create a file object using file path
            val file = File(selectedImagePath)

            // Create a request body with file and image media type
            val fileReqBody: RequestBody = file.asRequestBody("image/${file.extension}".toMediaTypeOrNull())

            // Create MultipartBody.Part using file request-body,file name and part name
            val part: MultipartBody.Part =
                MultipartBody.Part.createFormData("ref_image", file.name, fileReqBody)

            wrapBlockingOperation {
                handleResult(userRepository.uploadImage(part)){
                    sendEditProfileRequest(packagerName, packagerPhone, it.data.refImage)
                }
            }
        } else {
            sendEditProfileRequest(packagerName, packagerPhone)
        }
    }

    private fun sendEditProfileRequest(packagerName:String, packagerPhone:String, imagePath:String? = null) {
        wrapBlockingOperation {
            var updatedPackager = userLiveDate.value!!.apply {
                name     = packagerName
                phone    = packagerPhone
                imagePath?.let { imageUrl = it }
            }
            handleResult(userRepository.updateProfile(packager = updatedPackager)){
                userRepository.updateUser(updatedPackager)
                updatedSuccessfully.postValue(Event(true))
            }
        }
    }

    fun getUserData(){
        userRepository.getUser()?.packager?.let {
            userLiveDate.postValue(it)
        }
    }
}
const val MAX_SIZE = 2048