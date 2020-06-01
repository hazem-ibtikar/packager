package com.monh.packager.ui.home.settings.edit_profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.monh.packager.R
import com.monh.packager.base.BaseFragment
import com.monh.packager.ui.home.HomeActivity
import com.monh.packager.utils.EventObserver
import com.opensooq.supernova.gligar.GligarPicker
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.edit_profile_fragment.*

class EditProfileFragment : BaseFragment<EditProfileViewModel>() {

    companion object {
        fun newInstance() = EditProfileFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.edit_profile_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpFragmentTitle()
        handleToolBar()
        handleImageSelection()
        handleSaveProfile()
        initUiWithUserData()
    }

    private fun initUiWithUserData() {
        viewModel.getUserData()
        viewModel.userLiveDate.observe(viewLifecycleOwner, Observer {
            setUserImage(it.imageUrl?:"")
            phoneET.setText(it.phone)
            nameET.setText(it.name)
            emailET.text = it.email
        })
    }

    private fun handleSaveProfile() {
        save.setOnClickListener {
            if (phoneET.text.toString().isBlank()){
                showErrorMsg(R.string.empty_validation)
                return@setOnClickListener
            }
            if (!phoneET.text.toString().matches(Regex("^(009665|9665|\\+9665|05|5)([503649187])([0-9]{7})\$"))){
                showErrorMsg(R.string.phone_validation)
                return@setOnClickListener
            }
            if (nameET.text.toString().isBlank()){
                showErrorMsg(R.string.empty_validation)
                return@setOnClickListener
            }
            viewModel.editProfile(nameET.text.toString(), phoneET.text.toString())
        }
        viewModel.updatedSuccessfully.observe(viewLifecycleOwner, EventObserver{
            if (it){
                (activity as HomeActivity).getUserData()
                activity?.onBackPressed()
            }
        })
    }

    private fun handleImageSelection() {
        selectImage.setOnClickListener {
            GligarPicker()
                .requestCode(PICKER_REQUEST_CODE)
                .withFragment(this)
                .limit(1)
                .disableCamera(true)
                .cameraDirect(false)
                .show()
        }
    }

    private fun handleToolBar() {
        activity?.toolbar?.navigationIcon = context?.getDrawable(R.drawable.icon_back)
        activity?.toolbar?.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setUpFragmentTitle() {
        activity?.toolbar?.title = context?.getString(R.string.edit_profile)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            PICKER_REQUEST_CODE -> {
                val selectedImagePath = data?.extras?.getStringArray(GligarPicker.IMAGES_RESULT)?.get(0)
                viewModel.selectedImagePath = selectedImagePath?:""
                if (selectedImagePath?.isNotEmpty() == true){
                    setUserImage(selectedImagePath)
                }
            }
        }
    }

    private fun setUserImage(imagePath:String){
        Glide.with(this).load(imagePath).into(profile_image)
    }
}
const val PICKER_REQUEST_CODE = 123