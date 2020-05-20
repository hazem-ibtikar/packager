package com.monh.packager.ui.home.settings.edit_profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide

import com.monh.packager.R
import com.monh.packager.base.BaseFragment
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
    }

    private fun handleImageSelection() {
        selectImage.setOnClickListener {
            GligarPicker()
                .requestCode(PICKER_REQUEST_CODE)
                .withFragment(this)
                .limit(1)
                .disableCamera(true)
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
                Glide.with(this).load(data?.extras?.getStringArray(GligarPicker.IMAGES_RESULT)?.get(0)).into(profile_image)
            }
        }
    }
}
const val PICKER_REQUEST_CODE = 123