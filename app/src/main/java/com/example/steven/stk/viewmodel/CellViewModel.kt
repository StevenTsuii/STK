package com.example.steven.stk.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.steven.stk.base.activity.viewmodel.BaseViewModel
import com.example.steven.stk.data.FakeData2
import javax.inject.Inject

class CellViewModel: BaseViewModel(){

    val buttonText: MutableLiveData<String> = MutableLiveData()


    @Inject
    lateinit var fakeData2: FakeData2

    init {
        Log.d("", "stevenCheck Inject success fakeData2: ${fakeData2.TAG}")
        buttonText.value = fakeData2.TAG
    }
}