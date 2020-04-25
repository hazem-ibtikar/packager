package com.example.mvvmstarterproject.ui.home.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmstarterproject.R
import com.example.mvvmstarterproject.base.BaseFragment
import kotlinx.android.synthetic.main.app_bar_home.*

class SettingsFragment : BaseFragment<SettingsViewModel>() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        viewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
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
