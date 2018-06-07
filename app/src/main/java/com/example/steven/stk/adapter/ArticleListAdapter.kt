package com.example.steven.stk.adapter

import android.content.Context
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.steven.stk.GlideApp
import com.example.steven.stk.R
import com.example.steven.stk.data.model.ArticleListModel
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import kotlinx.android.synthetic.main.item_article_list_cell.view.*







/**
 * Created by steven on 23/5/2018.
 */
class ArticleListAdapter : RecyclerView.Adapter<ArticleListAdapter.ArticleListCellItemViewHolder>() {

     var articleItemList : ArrayList<ArticleListModel.ArticleItem> = ArrayList()
    
    fun addArticleItemList(list : ArrayList<ArticleListModel.ArticleItem>){
        articleItemList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ArticleListCellItemViewHolder {
        return ArticleListCellItemViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_article_list_cell, parent, false))
    }

    override fun onBindViewHolder(holder: ArticleListCellItemViewHolder?, position: Int) {
        holder?.bind(articleItem = articleItemList.get(position))
    }

    override fun getItemCount(): Int {
        return articleItemList.size
    }


    class ArticleListCellItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var simpleExoPlayer : SimpleExoPlayer
        private val bandwidthMeter = DefaultBandwidthMeter()

        init {
            val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory(bandwidthMeter)
            val trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)
            simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(itemView.context, trackSelector)
//            simpleExoPlayer.playWhenReady = true
        }

        fun bind(articleItem: ArticleListModel.ArticleItem) {
            itemView.title.text = articleItem.title
            itemView.label.text = articleItem.label
            itemView.viewCount.text = articleItem.social.viewCount

            var isHas480pVideo = false
            for (media in articleItem.mediaGroup) {
                if("videos" == media.type && "480p" == media.quality){
                    isHas480pVideo = true
                    break
                }
            }

            for (media in articleItem.mediaGroup) {

                if(isHas480pVideo && "videos" == media.type && "480p" == media.quality){

                    itemView.playerView.player = simpleExoPlayer
                    val videoSource = createMediaSource(bandwidthMeter, media.url, itemView.context)
                    if (!simpleExoPlayer.isLoading) {
                        simpleExoPlayer.prepare(videoSource)
                    }

                    itemView.playerView.visibility = View.VISIBLE
                    itemView.playerView.hideController()
                    break
                }
                else if (!isHas480pVideo && "image" == media.type) {
                    GlideApp.with(itemView.context)
                            .load(media.largePath)
                            .centerCrop()
                            .into(itemView.image)
                    itemView.playerView.visibility = View.GONE

                    break
                }
            }
        }

        private fun createMediaSource(bandwidthMeter: DefaultBandwidthMeter, videoUrl: String, context: Context): ExtractorMediaSource? {
            val defaultHttpDataSourceFactory = DefaultHttpDataSourceFactory("Android", bandwidthMeter,
                    DefaultHttpDataSource.DEFAULT_CONNECT_TIMEOUT_MILLIS,
                    DefaultHttpDataSource.DEFAULT_READ_TIMEOUT_MILLIS,
                    true)

            // Produces DataSource instances through which media data is loaded.
            val dataSourceFactory = DefaultDataSourceFactory(context, bandwidthMeter,
                    defaultHttpDataSourceFactory)
            // This is the MediaSource representing the media to be played.
            return ExtractorMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(Uri.parse(videoUrl))

        }
    }

    override fun onViewAttachedToWindow(holder: ArticleListCellItemViewHolder?) {
        super.onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: ArticleListCellItemViewHolder?) {
        super.onViewDetachedFromWindow(holder)
    }

}