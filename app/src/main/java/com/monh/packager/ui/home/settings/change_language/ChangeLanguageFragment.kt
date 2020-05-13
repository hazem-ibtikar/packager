package com.monh.packager.ui.home.settings.change_language

import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.monh.packager.R
import com.monh.packager.base.BaseBottomSheetDialogFragment
import com.monh.packager.ui.home.HomeActivity
import com.monh.packager.utils.localization.ARABIC
import com.monh.packager.utils.localization.ENGLISH
import kotlinx.android.synthetic.main.change_language_fragment.*


class ChangeLanguageFragment : BaseBottomSheetDialogFragment<ChangeLanguageViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.change_language_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        selectDefaultLanguage()
        handleChangeLanguage()
        handleSubmitNewLanguage()
    }

    private fun handleSubmitNewLanguage() {
        submit.setOnClickListener {
            if (viewModel.currentLang.isNotEmpty()){
                viewModel.changeLanguage()
                activity?.recreate()
                dismiss()
            }
        }
    }

    private fun handleChangeLanguage() {
        arabicLayout.setOnClickListener {
            showArabicIcon()
            viewModel.currentLang = ARABIC
        }
        englishLayOut.setOnClickListener {
            showEnglishIcon()
            viewModel.currentLang = ENGLISH
        }
    }

    private fun selectDefaultLanguage() {
        viewModel.getCurrentLanguage().let {
            if (it == ARABIC){
                showArabicIcon()
            }else{
                showEnglishIcon()
            }
        }
    }

    private fun showEnglishIcon() {
        arabicIcon.visibility = View.GONE
        englishIcon.visibility = View.VISIBLE
    }

    private fun showArabicIcon() {
        englishIcon.visibility = View.GONE
        arabicIcon.visibility = View.VISIBLE
    }


}
