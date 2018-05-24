package com.example.steven.stk.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.steven.stk.R
import com.example.steven.stk.adapter.ArticleListAdapter
import com.example.steven.stk.base.activity.BaseFragment
import com.example.steven.stk.data.network.STKService2
import com.example.steven.stk.extension.log
import com.example.steven.stk.extension.plugFragmentComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_article_list.*
import javax.inject.Inject

/**
 * Created by steven on 23/5/2018.
 */
class ArticleListFragment : BaseFragment() {

    @Inject
    lateinit var stkService2: STKService2

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_article_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        plugFragmentComponent().inject(this)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        stkService2.articleList("LANDING", "0", "40")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {
                    log("ArticleList Result size: ${it.content.size}")

                    recyclerView.adapter = ArticleListAdapter(it.content)
                })

    }
}