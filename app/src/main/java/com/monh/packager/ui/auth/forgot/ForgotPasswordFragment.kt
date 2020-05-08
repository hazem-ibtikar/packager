package com.monh.packager.ui.auth.forgot

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.monh.packager.R
import com.monh.packager.base.BaseFragment
import com.monh.packager.utils.EventObserver
import kotlinx.android.synthetic.main.forgot_password_fragment.*


class ForgotPasswordFragment : BaseFragment<ForgotPasswordViewModel>() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.forgot_password_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        handleBackPressed()
        handleResetPassword()
        handleResetPasswordSuccessfully()
    }

    private fun handleResetPasswordSuccessfully() {
        viewModel.resetPasswordLiveData.observe(viewLifecycleOwner, EventObserver{
            showResetPasswordDialog(it)
        })
    }

    private fun showResetPasswordDialog(msg:String) {
        AlertDialog.Builder(context)
            .setTitle(getString(R.string.reset_password))
            .setMessage(msg) // Specifying a listener allows you to take an action before dismissing the dialog.
            // The dialog is automatically dismissed when a dialog button is clicked.
            .setPositiveButton(android.R.string.yes,
                DialogInterface.OnClickListener { dialog, which ->
                    // Continue with delete operation
                    findNavController().popBackStack()
                }) // A null listener allows the button to dismiss the dialog and take no further action.
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }

    private fun handleResetPassword() {
        resetPasswordBtn.setOnClickListener {
            if (emailET.text.toString().isBlank()){
                emailET.error = context?.getString(R.string.email_empty_validation)
                return@setOnClickListener
            }
            viewModel.resetPassword(emailET.text.toString())
        }
    }

    private fun handleBackPressed() {
        back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}
