package com.example.steven.stk.activity

import android.os.Bundle
import android.os.PersistableBundle
import com.example.steven.stk.R
import com.example.steven.stk.base.activity.BaseActivity
import com.example.steven.stk.extension.plugActivityComponent

/**
 * Created by steven on 7/6/2018.
 */
class ArticleDetailActivity: BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        setContentView(R.layout.activity_main)

        plugActivityComponent().inject(this)
    }
}
