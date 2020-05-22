package com.monh.packager.ui.home.settings.contactUs

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.monh.packager.R
import com.monh.packager.base.BaseFragment
import com.monh.packager.utils.network.Result
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.contact_us_fragment.*


class ContactUsFragment : BaseFragment<ContactUsViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.contact_us_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpFragmentTitle()
        handleToolBar()
        viewModel.getContactUs()
        handleContactUsResponse()
        handleContactsClick()
    }

    private fun handleContactsClick() {
        phoneIcon.setOnClickListener {
            callPermission()
        }

        mailIcon.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:${viewModel.contactUsLiveData.value?.contacts?.supportEmail}") // only email apps should handle this
            }

            if (intent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(intent)
            }
        }

        addressIcon.setOnClickListener {
            // Creates an Intent that will load a map of San Francisco

            // Creates an Intent that will load a map of San Francisco
            val gmmIntentUri = Uri.parse("geo:${viewModel.contactUsLiveData.value?.contacts?.latitude?.toFloat()},${viewModel.contactUsLiveData.value?.contacts?.longitude?.toFloat()}")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")

            if (mapIntent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(mapIntent)
            }
        }
    }

    private fun callPermission(){
        Dexter.withActivity(activity)
            .withPermission(Manifest.permission.CALL_PHONE)
            .withListener(object : PermissionListener{
                override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                    val intent = Intent(Intent.ACTION_CALL).apply {
                        data = Uri.parse("tel:${viewModel.contactUsLiveData.value?.contacts?.supportPhoneno}")
                    }
                    if (intent.resolveActivity(requireActivity().packageManager) != null) {
                        startActivity(intent)
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest?,
                    token: PermissionToken?
                ) {

                }

                override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                    Toast.makeText(requireContext(), getString(R.string.nedd_call_permission), Toast.LENGTH_SHORT).show()
                }

            }).check()
    }
    private fun handleContactUsResponse() {
        viewModel.contactUsLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                address.text = it.contacts?.supportAddress
                phone.text = it.contacts?.supportPhoneno
                mail.text = it.contacts?.supportEmail
            }
        })
    }

    private fun handleToolBar() {
        activity?.toolbar?.navigationIcon = context?.getDrawable(R.drawable.icon_back)
        activity?.toolbar?.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setUpFragmentTitle() {
        activity?.toolbar?.title = context?.getString(R.string.contact_us)
    }

    override fun showError(error: Result.Error) {
        showNoContactsLayout()
    }

    private fun showNoContactsLayout() {
        noContactsLayout.visibility = View.VISIBLE
        contactsGroup.visibility = View.GONE
    }
}
