/*
 * Created by Yip Tsz To on 11/1/19 10:28 PM
 * Copyright (c) 2019.
 */


package org.ytt.code4good.viewModels

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel

data class ChatViewModel(
    val app: Application,
    @DrawableRes private var imageSrc: Int,
    var text: String
) : AndroidViewModel(app) {
    fun getDrawable(): Drawable? {
        return ContextCompat.getDrawable(getApplication(), imageSrc)
    }
}