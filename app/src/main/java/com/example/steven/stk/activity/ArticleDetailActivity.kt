package com.example.steven.stk.activity

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.example.steven.stk.R
import com.example.steven.stk.base.activity.BaseActivity
import com.example.steven.stk.databinding.ActivityArticleDetailBinding
import com.example.steven.stk.extension.plugActivityComponent
import com.example.steven.stk.viewmodel.CellViewModel
import kotlinx.android.synthetic.main.activity_article_detail.*

/**
 * Created by steven on 7/6/2018.
 */
class ArticleDetailActivity: BaseActivity(){

    private lateinit var binding: ActivityArticleDetailBinding
    private lateinit var cellViewModel: CellViewModel
    var isFly = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_article_detail)
        cellViewModel = ViewModelProviders.of(this).get(CellViewModel::class.java)
        binding.cellViewModel = cellViewModel

        plugActivityComponent().inject(this)

        toolbar.title = "STK"
        toolbar.subtitle = "Testing Dagger2 with Kotlin"
//        setSupportActionBar(toolbar)

        buttonB.setOnClickListener {

            it.animate().y(if(isFly)400f else 1200f )
            isFly = !isFly

        }
    }


}
