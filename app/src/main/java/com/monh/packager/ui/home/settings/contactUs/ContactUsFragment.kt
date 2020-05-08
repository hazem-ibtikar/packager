package com.monh.packager.ui.home.settings.contactUs

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.monh.packager.R
import com.monh.packager.base.BaseFragment
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
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${viewModel.contactUsLiveData.value?.contacts?.supportPhoneno}")
            }
            if (intent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(intent)
            }
        }

        mailIcon.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:${viewModel.contactUsLiveData.value?.contacts?.supportEmail}") // only email apps should handle this
            }
            if (intent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(intent)
            }
        }
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

}
