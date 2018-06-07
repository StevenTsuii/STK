package com.example.steven.stk.fragment

import android.content.Context
import android.net.Uri
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
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_article_list.*
import kotlinx.android.synthetic.main.item_article_list_cell.view.*
import javax.inject.Inject

/**
 * Created by steven on 23/5/2018.
 */
class ArticleListFragment : BaseFragment() {

    @Inject
    lateinit var stkService2: STKService2

    var articleListAdapter = ArticleListAdapter()

    lateinit var simpleExoPlayer : SimpleExoPlayer

    var currentMediaUrl : String? = null




    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_article_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        plugFragmentComponent().inject(this)

        simpleExoPlayer = createSimpleExoPlayer()
        simpleExoPlayer.playWhenReady = true

        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val firstPos = layoutManager.findFirstVisibleItemPosition()
                val lastPos = layoutManager.findLastVisibleItemPosition()
                val middle = Math.abs(lastPos - firstPos) / 2 + firstPos

                log("onScrolled firstPos: ${firstPos} middle: ${middle} lastPos:${lastPos}")
                val currentArticleItem = articleListAdapter.articleItemList[middle]

                var mediaUrl : String? = null

                for (media in currentArticleItem.mediaGroup) {
                    if("videos" == media.type && "480p" == media.quality){
                        mediaUrl = media.url
                        break
                    }
                }

                if(mediaUrl.isNullOrEmpty()){
                    simpleExoPlayer.stop(true)
                    currentMediaUrl = null
                    return
                }else if((simpleExoPlayer.isLoading || simpleExoPlayer.playbackState == Player.STATE_READY || simpleExoPlayer.playbackState == Player.STATE_BUFFERING) && mediaUrl == currentMediaUrl){
                    return
                }else{
                    val itemView = layoutManager.findViewByPosition(middle)
                    simpleExoPlayer.stop(true)
                    itemView.playerView.player = simpleExoPlayer
                    simpleExoPlayer.prepare(createMediaSource(mediaUrl!!, itemView.context))
                    currentMediaUrl = mediaUrl
                    itemView.playerView.visibility = View.VISIBLE
                    itemView.playerView.hideController()
                }
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

    fun createSimpleExoPlayer(): SimpleExoPlayer {
        val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory(DefaultBandwidthMeter())
        val trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)
        return ExoPlayerFactory.newSimpleInstance(activity, trackSelector)
    }

    private fun createMediaSource( videoUrl: String, context: Context): ExtractorMediaSource? {
        val defaultHttpDataSourceFactory = DefaultHttpDataSourceFactory("Android", DefaultBandwidthMeter(),
                DefaultHttpDataSource.DEFAULT_CONNECT_TIMEOUT_MILLIS,
                DefaultHttpDataSource.DEFAULT_READ_TIMEOUT_MILLIS,
                true)

        // Produces DataSource instances through which media data is loaded.
        val dataSourceFactory = DefaultDataSourceFactory(context, DefaultBandwidthMeter(),
                defaultHttpDataSourceFactory)
        // This is the MediaSource representing the media to be played.
        return ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(videoUrl))

    }

}