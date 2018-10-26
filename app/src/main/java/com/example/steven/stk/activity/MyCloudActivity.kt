package com.example.steven.stk.activity

import android.os.Bundle
import com.example.steven.stk.R
import com.example.steven.stk.base.activity.BaseActivity
import com.example.steven.stk.extension.addFragment
import com.example.steven.stk.extension.plugActivityComponent
import com.example.steven.stk.fragment.MyCloudFragment

class MyCloudActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_my_cloud)

        plugActivityComponent().inject(this)
        addFragment(MyCloudFragment(), R.id.fragment_container)
    }
}