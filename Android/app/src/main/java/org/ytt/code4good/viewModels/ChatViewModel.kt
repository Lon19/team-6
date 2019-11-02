/*
 * Created by Yip Tsz To on 11/2/19 4:18 AM.
 * Copyright (c) 2019.
 */


package org.ytt.code4good.viewModels

import android.app.Application
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel

data class ChatViewModel(
    val app: Application,
    @DrawableRes var imageSrc: Int,
    var name: String,
    var streak: Int,
    var detail: String,
    var time: String
) : AndroidViewModel(app) {
    val convo = listOf<ChatViewModel>()

    fun getDrawable() = ContextCompat.getDrawable(getApplication(), imageSrc)
}