package com.monh.packager.ui.home.settings.contactUs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.monh.packager.R
import com.monh.packager.base.BaseFragment
import kotlinx.android.synthetic.main.app_bar_home.*

class ContactUsFragment : BaseFragment<ContactUsViewModel>() {

    companion object {
        fun newInstance() = ContactUsFragment()
    }


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
