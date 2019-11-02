package org.ytt.code4good.viewModels

import androidx.lifecycle.ViewModel

data class TaskViewModel(
    var name: String,
    var coins: String
) : ViewModel()