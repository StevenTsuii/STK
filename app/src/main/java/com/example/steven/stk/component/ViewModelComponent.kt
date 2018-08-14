package com.example.steven.stk.component

import com.example.steven.stk.module.UserModule
import com.example.steven.stk.viewmodel.CellViewModel
import dagger.Component

@Component(modules = arrayOf(UserModule::class))
interface ViewModelComponent {
    fun inject(cellViewModel: CellViewModel)

//    @Component.Builder
//    interface Builder {
//        fun build(): ViewModelComponent
//
//        fun userModule(userModule: UserModule): Builder
//    }
}