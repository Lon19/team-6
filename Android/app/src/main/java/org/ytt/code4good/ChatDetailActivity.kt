/*
 * Created by Yip Tsz To on 11/2/19 8:21 AM.
 * Copyright (c) 2019.
 */

package org.ytt.code4good

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ChatDetailActivity :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pos = intent.getIntExtra(ChatFragment.EXTRA_CHAT_POS,0)


    }
}