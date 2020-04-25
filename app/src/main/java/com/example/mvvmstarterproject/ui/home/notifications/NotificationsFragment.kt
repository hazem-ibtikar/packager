package com.example.mvvmstarterproject.ui.home.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.mvvmstarterproject.R
import kotlinx.android.synthetic.main.app_bar_home.*
import com.example.mvvmstarterproject.base.BaseFragment

class NotificationsFragment : BaseFragment<NotificationsViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
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
        activity?.toolbar?.title = context?.getString(R.string.notifications)
    }
}
