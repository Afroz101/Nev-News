package com.utlis

import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
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


    fun setFavIcon(@DrawableRes int: Int) {
        bind.favouriteIcon.setImageResource(int)
    }

    fun showFavIcon() {
        bind.favouriteIcon.show()
    }

    fun hideFavIcon() {
        bind.favouriteIcon.hide()
    }

    fun showFavListIcon() {
        bind.favouriteListIcon.show()
    }

    fun hideFavListIcon() {
        bind.favouriteListIcon.hide()
    }

    fun backButtonClicked(view: View) {
        if (pronBackButtonClicked != null) {
            pronBackButtonClicked.backButtonClicked()
        }

    }

    fun otherActionbarIconClicked(view: View) {
        if (pronBackButtonClicked != null) {
            pronBackButtonClicked.otherActionbarIconClick(view)
        }

    }
}