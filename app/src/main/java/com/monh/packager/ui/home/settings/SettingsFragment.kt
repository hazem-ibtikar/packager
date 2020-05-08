package com.monh.packager.ui.home.settings

import androidx.appcompat.app.AlertDialog;
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.monh.packager.R
import com.monh.packager.base.BaseFragment
import com.monh.packager.ui.auth.login.LoginActivity
import com.monh.packager.utils.localization.ARABIC
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : BaseFragment<SettingsViewModel>() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpFragmentTitle()
        handleClickListener()
        handleToolBar()
        handleLogOutClick()
        handleChangeLanguage()
        handleEnglishArabicText()
    }

    private fun handleEnglishArabicText() {
        viewModel.getCurrentLang().let {
            language.text = if (it == ARABIC) getString(R.string.arabic) else getString(R.string.english)
        }
    }

    private fun handleChangeLanguage() {
        language.setOnClickListener {
            findNavController().navigate(R.id.action_nav_settings_to_changeLanguageFragment)
        }
    }

    private fun handleLogOutClick() {
        logOut.setOnClickListener {
            showLogOutDialog()
        }
    }

    private fun showLogOutDialog() {
        val alertDialog = AlertDialog.Builder(requireContext()).create()
        alertDialog.setTitle(getString(R.string.reset_password))
        alertDialog.setMessage(getString(R.string.log_out_msg)) // Specifying a listener allows you to take an action before dismissing the dialog.
        alertDialog.setButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE, getString(R.string.log_out_yes)){ dialog, _ ->
            alertDialog.dismiss()
            viewModel.removeUserData()
            activity?.startActivity(Intent(requireActivity(), LoginActivity::class.java))
            activity?.finish()
        } //
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.log_out_no)){ dialog, _ ->
            dialog.dismiss()
        }
        alertDialog.setIcon(android.R.drawable.ic_dialog_alert)
        alertDialog.show()
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
