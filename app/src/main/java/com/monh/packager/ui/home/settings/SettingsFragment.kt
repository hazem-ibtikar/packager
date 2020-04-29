package com.monh.packager.ui.home.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.monh.packager.R
import com.monh.packager.base.BaseFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : BaseFragment<SettingsViewModel>() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_settings, container, false)
        viewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpFragmentTitle()
        handleClickListener()
        handleToolBar()
    }

    private fun handleToolBar() {
        activity?.toolbar?.navigationIcon = context?.getDrawable(R.drawable.ic_sidemenu)
        activity?.toolbar?.setNavigationOnClickListener {
            activity?.drawer_layout?.open()
        }
    }
    private fun handleClickListener() {
        contactUsHeader.setOnClickListener {
            findNavController().navigate(R.id.action_nav_settings_to_contactUsFragment)
        }

        editProfileHeader.setOnClickListener {
            findNavController().navigate(R.id.action_nav_settings_to_editProfileFragment)
        }

        changePasswordHeader.setOnClickListener {
            findNavController().navigate(R.id.action_nav_settings_to_changePasswordFragment)
        }
    }

    private fun setUpFragmentTitle() {
        activity?.toolbar?.title = context?.getString(R.string.seetings)
    }
}
