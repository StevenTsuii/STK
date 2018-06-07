package com.example.steven.stk.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.steven.stk.GlideApp
import com.example.steven.stk.R
import com.example.steven.stk.data.model.ArticleListModel
import com.google.android.exoplayer2.ExoPlayer
import kotlinx.android.synthetic.main.item_article_list_cell.view.*







/**
 * Created by steven on 23/5/2018.
 */
class ArticleListAdapter(articleItemList: ArrayList<ArticleListModel.ArticleItem>, player: ExoPlayer) : RecyclerView.Adapter<ArticleListAdapter.ArticleListCellItemViewHolder>() {

    var articleItemList = articleItemList
    var player = player


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ArticleListCellItemViewHolder {
        return ArticleListCellItemViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_article_list_cell, parent, false))
    }

    override fun onBindViewHolder(holder: ArticleListCellItemViewHolder?, position: Int) {
        holder?.bind(articleItem = articleItemList.get(position), player = player)
    }

    override fun getItemCount(): Int {
        return articleItemList?.size ?: 0
    }


    class ArticleListCellItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(articleItem: ArticleListModel.ArticleItem, player: ExoPlayer) {
            itemView.title.text = articleItem.title
            itemView.label.text = articleItem.label
            itemView.viewCount.text = articleItem.social.viewCount
            itemView.playerView.player = player

            var isHas480pVideo = false
            for (media in articleItem.mediaGroup) {
                if("videos" == media.type && "480p" == media.quality){
                    isHas480pVideo = true
                }
            }

            for (media in articleItem.mediaGroup) {

                if(isHas480pVideo && "videos" == media.type && "480p" == media.quality){
//                    val bandwidthMeter = DefaultBandwidthMeter()
//                    val dataSourceFactory = DefaultDataSourceFactory(itemView.context,
//                            "yourApplicationNameUserAgent", bandwidthMeter)
//                    val videoSource = ExtractorMediaSource.Factory(dataSourceFactory)
//                            .createMediaSource(Uri.parse(media.url))
//                    player.prepare(videoSource)

//                    val bandwidthMeter1 = DefaultBandwidthMeter()
//                    val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory(bandwidthMeter1)
//                    val trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)
//
//// 2. Create the player
//                    val player = ExoPlayerFactory.newSimpleInstance(itemView.context, trackSelector)
//                    val bandwidthMeter = DefaultBandwidthMeter()
//// Produces DataSource instances through which media data is loaded.
//                    val dataSourceFactory = DefaultDataSourceFactory(itemView.context,
//                           "fgsdfgsdfgsdfg", bandwidthMeter)
//// This is the MediaSource representing the media to be played.
//                    val videoSource = ExtractorMediaSource.Factory(dataSourceFactory)
//                            .createMediaSource(Uri.parse(media.url))
//// Prepare the player with the source.
//                    player.prepare(videoSource)

                    itemView.playerView.visibility = View.VISIBLE
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
    }
}