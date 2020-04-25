package com.example.mvvmstarterproject.ui.home.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.mvvmstarterproject.R
import com.example.mvvmstarterproject.base.BaseFragment
import kotlinx.android.synthetic.main.app_bar_home.*

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
    }

    private fun setUpFragmentTitle() {
        activity?.toolbar?.title = context?.getString(R.string.seetings)
    }
}
