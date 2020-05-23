package com.monh.packager.ui.home.settings.change_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.monh.packager.R
import com.monh.packager.base.BaseFragment
import com.monh.packager.utils.network.Result
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.change_password_fragment.*

class ChangePasswordFragment : BaseFragment<ChangePasswordViewModel>() {

    companion object {
        fun newInstance() = ChangePasswordFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.change_password_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpFragmentTitle()
        handleToolBar()
        handleSaveBtn()
        handlePasswordChanged()
    }

    private fun handlePasswordChanged() {
        viewModel.passwordChangedSuccessfully.observe(viewLifecycleOwner, Observer {
            showPasswordChangedSuccessfullyDialog()
        })
    }

    private fun showPasswordChangedSuccessfullyDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.reset_password))
            .setMessage(getString(R.string.password_changed_successfully))
            .setPositiveButton(android.R.string.yes
            ) { _, _ ->
                findNavController().popBackStack()
            }
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }

    private fun handleSaveBtn() {
        saveBtn.setOnClickListener {
            if (oldPassword.text.toString().isBlank()){
                oldPassword.error = context?.getString(R.string.password_empty_validation)
                return@setOnClickListener
            }
            if (newPassword.text.toString().isBlank()){
                newPassword.error = context?.getString(R.string.password_empty_validation)
                return@setOnClickListener
            }
            if (rePassword.text.toString().isBlank()){
                rePassword.error = context?.getString(R.string.password_empty_validation)
                return@setOnClickListener
            } else if (newPassword.text.toString() != rePassword.text.toString()){
                rePassword.error = context?.getString(R.string.password_repasword_match_validation)
                return@setOnClickListener
            }
            viewModel.changePassword(oldPassword.text.toString(), newPassword.text.toString())

        }
    }

    private fun handleToolBar() {
        activity?.toolbar?.navigationIcon = context?.getDrawable(R.drawable.icon_back)
        activity?.toolbar?.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setUpFragmentTitle() {
        activity?.toolbar?.title = context?.getString(R.string.change_password)
    }

    override fun logout() {

    }
}
