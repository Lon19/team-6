/*
 * Created by Yip Tsz To on 11/2/19 2:01 AM
 * Copyright (c) 2019.
 */


package org.ytt.code4good.viewModels

import android.app.Application
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel

data class ChatViewModel(
    val app: Application,
    @DrawableRes private var imageSrc: Int,
    var text: String
) : AndroidViewModel(app) {
    fun getDrawable() = ContextCompat.getDrawable(getApplication(), imageSrc)
}