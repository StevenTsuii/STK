package com.example.steven.stk.activity

import android.os.Bundle
import com.example.steven.stk.R
import com.example.steven.stk.base.activity.BaseActivity
import com.example.steven.stk.extension.plugActivityComponent
import kotlinx.android.synthetic.main.activity_article_detail.*

/**
 * Created by steven on 7/6/2018.
 */
class ArticleDetailActivity: BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        setContentView(R.layout.activity_article_detail)

        plugActivityComponent().inject(this)

        toolbar.title = "STK"
        toolbar.subtitle = "Testing Dagger2 with Kotlin"
//        setSupportActionBar(toolbar)
    }
}
