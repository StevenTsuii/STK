package com.example.steven.stk.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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

    var articleListAdapter = ArticleListAdapter()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_article_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        plugFragmentComponent().inject(this)

        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val firstPos = layoutManager.findFirstVisibleItemPosition()
                val lastPos = layoutManager.findLastVisibleItemPosition()
                val middle = Math.abs(lastPos - firstPos) / 2 + firstPos


                log("onScrolled firstPos: ${firstPos} middle: ${middle} lastPos:${lastPos}")
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val firstPos = layoutManager.findFirstVisibleItemPosition()
                val lastPos = layoutManager.findLastVisibleItemPosition()
                val middle = Math.abs(lastPos - firstPos) / 2 + firstPos
                log("onScrollStateChanged firstPos: ${firstPos} middle: ${middle} lastPos:${lastPos}")
            }
        })

        stkService2.articleList("LANDING", "0", "40")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {
                    log("ArticleList Result size: ${it.content.size}")
                    articleListAdapter.addArticleItemList(it.content)
                    recyclerView.adapter = articleListAdapter

                })

    }
}