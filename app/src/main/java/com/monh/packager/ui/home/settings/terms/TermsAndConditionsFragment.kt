package com.monh.packager.ui.home.settings.terms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.monh.packager.R
import com.monh.packager.base.BaseFragment
import com.monh.packager.utils.network.Result
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.terms_and_conditions_fragment.*

class TermsAndConditionsFragment : BaseFragment<TermsAndConditionsViewModel>() {

    companion object {
        fun newInstance() = TermsAndConditionsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.terms_and_conditions_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpFragmentTitle()
        handleToolBar()
        viewModel.getTermsAndConditions()
        handleTermsResponse()
    }

    private fun handleToolBar() {
        activity?.toolbar?.navigationIcon = context?.getDrawable(R.drawable.icon_back)
        activity?.toolbar?.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setUpFragmentTitle() {
        activity?.toolbar?.title = context?.getString(R.string.termsAndConditions)
    }

    private fun handleTermsResponse() {
        viewModel.termsLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                termsWebView.loadData(it.terms?.templateData, "text/html", "UTF-8")
            }
        })
    }

    override fun showError(error: Result.Error) {
        showNoContactsLayout()
    }

    private fun showNoContactsLayout() {
        noContactsLayout.visibility = View.VISIBLE
        termsWebView.visibility = View.GONE
    }

}
