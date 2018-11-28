package com.example.steven.stk.activity

import android.content.Intent
import android.os.Bundle
import com.example.steven.stk.R
import com.example.steven.stk.base.activity.BaseActivity
import com.example.steven.stk.extension.addFragment
import com.example.steven.stk.extension.plugActivityComponent
import com.example.steven.stk.fragment.SNSFragment


class SnsActivity : BaseActivity() {

    private var snsFragment = SNSFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        plugActivityComponent().inject(this)

        setContentView(R.layout.activity_sns)
        addFragment(snsFragment, R.id.fragment_container)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        snsFragment?.let {
            snsFragment.onActivityResult(requestCode, resultCode, data)
        }
    }
}