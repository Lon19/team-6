/*
 * Created by Yip Tsz To on 11/2/19 8:29 AM.
 * Copyright (c) 2019.
 */

package org.ytt.code4good.viewModels

import androidx.lifecycle.ViewModel

data class ConvoViewModel(
    val name : String,
    val message : String,
    val time : String
) : ViewModel()