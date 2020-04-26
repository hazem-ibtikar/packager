package com.example.mvvmstarterproject.ui.home.settings.edit_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.example.mvvmstarterproject.R
import com.example.mvvmstarterproject.base.BaseFragment
import kotlinx.android.synthetic.main.app_bar_home.*

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
}
