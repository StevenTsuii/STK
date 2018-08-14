package com.example.steven.stk.base.activity.viewmodel

import android.arch.lifecycle.ViewModel
import com.example.steven.stk.component.DaggerViewModelComponent
import com.example.steven.stk.component.ViewModelComponent
import com.example.steven.stk.module.UserModule
import com.example.steven.stk.viewmodel.CellViewModel

abstract class  BaseViewModel: ViewModel(){

    private val viewModelComponent: ViewModelComponent = DaggerViewModelComponent
            .builder()
            .userModule(UserModule())
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is CellViewModel -> viewModelComponent.inject(this)
        }
    }
}