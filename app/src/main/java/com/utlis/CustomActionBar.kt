package com.utlis

import android.view.View
import com.base.NevNewsApplication
import com.example.myapplication.databinding.CustomActionBarBinding
import com.utlis.callbackinterface.OnBackButtonClicked
import javax.inject.Inject

class CustomActionBar @Inject constructor(
    private var bind: CustomActionBarBinding
) {
    lateinit var pronBackButtonClicked: OnBackButtonClicked
    fun setActionbarBackPress(pronBackButtonClicked: OnBackButtonClicked) {
        this.pronBackButtonClicked = pronBackButtonClicked
    }

    fun setActionBar(tittleText: String) {
        bind.tittleName = tittleText
        bind.customActionBar = this

    }

    fun hideBackButton() {
        bind.backbutton.hide()
    }

    fun showBackButton() {
        bind.backbutton.show()
    }


    fun backButtonClicked(view: View) {
        if (pronBackButtonClicked != null) {
            pronBackButtonClicked.backButtonClicked()
        }
        println("BACK == ${"backButtonClicked"}")

    }
}