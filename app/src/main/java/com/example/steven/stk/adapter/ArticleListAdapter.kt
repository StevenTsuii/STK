package com.example.steven.stk.adapter

import android.content.Context
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.util.Log
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
class ArticleListAdapter(articleItemList: ArrayList<ArticleListModel.ArticleItem>) : RecyclerView.Adapter<ArticleListAdapter.ArticleListCellItemViewHolder>() {

    var articleItemList = articleItemList
    var currentMiddlePosition: Int = 0
    var playerHashMap = HashMap<Int, SimpleExoPlayer>()

    fun addArticleItemList(list: ArrayList<ArticleListModel.ArticleItem>) {

        if (articleItemList == null) {
            articleItemList = ArrayList()
        }

        articleItemList?.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ArticleListCellItemViewHolder {
        Log.d("StevenCheck", "onCreateViewHolder")
        return ArticleListCellItemViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_article_list_cell, parent, false))
    }

    override fun onBindViewHolder(holder: ArticleListCellItemViewHolder?, position: Int) {
        Log.d("StevenCheck", "onBindViewHolder position:${position}")
        holder?.bind(articleItem = articleItemList.get(position), playerHashMap = playerHashMap)
    }

    override fun getItemCount(): Int {
        return articleItemList.size
    }


    override fun onViewAttachedToWindow(holder: ArticleListCellItemViewHolder) {
        super.onViewAttachedToWindow(holder)
        Log.d("StevenCheck", "onViewAttachedToWindow position:${holder.adapterPosition}")

        currentMiddlePosition = if (holder.adapterPosition == 0) {
            holder.adapterPosition
        } else if (currentMiddlePosition < holder.adapterPosition) {
            holder.adapterPosition - 1
        } else {
            holder.adapterPosition + 1
        }

        Log.d("StevenCheck", "currentMiddlePosition :${currentMiddlePosition}")


        playerHashMap?.forEach {
            it.value.playWhenReady = it.key == currentMiddlePosition
        }

        Log.d("StevenCheck", "playerHashMap.size :${playerHashMap.size}")
    }

    override fun onViewDetachedFromWindow(holder: ArticleListCellItemViewHolder) {
        super.onViewDetachedFromWindow(holder)
        Log.d("StevenCheck", "onViewDetachedFromWindow position:${holder.adapterPosition}")
        holder.simpleExoPlayer?.playWhenReady = false
        Log.d("StevenCheck", "playerHashMap.size :${playerHashMap.size}")
    }

    override fun onViewRecycled(holder: ArticleListCellItemViewHolder) {
        super.onViewRecycled(holder)
        Log.d("StevenCheck", "onViewRecycled position:${holder.adapterPosition}")
        playerHashMap.remove(holder.adapterPosition)
        holder.simpleExoPlayer?.release()
        holder.simpleExoPlayer = null
        Log.d("StevenCheck", "playerHashMap.size :${playerHashMap.size}")
    }

    class ArticleListCellItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var simpleExoPlayer: SimpleExoPlayer? = null
        var mMediaUrl: String? = null

        fun bind(articleItem: ArticleListModel.ArticleItem, playerHashMap: HashMap<Int, SimpleExoPlayer>) {
            itemView.title.text = articleItem.title
            itemView.label.text = articleItem.label
            itemView.viewCount.text = articleItem.social.viewCount
            itemView.position.text = "${adapterPosition}"

            for (media in articleItem.mediaGroup) {
                if ("image" == media.type) {
                    GlideApp.with(itemView.context)
                            .load(media.largePath)
                            .centerCrop()
                            .placeholder(R.drawable.stk_placeholder)
                            .into(itemView.image)

                    break
                }
            }

            var mediaUrl: String? = null

            for (media in articleItem.mediaGroup) {
                if ("videos" == media.type && "480p" == media.quality) {
                    mediaUrl = media.url
                    mMediaUrl = media.url
                    break
                }
            }
            if (mediaUrl.isNullOrEmpty()) {
                itemView.playerView.visibility = View.GONE
                itemView.play_icon.visibility = View.GONE
            } else {
                itemView.playerView.visibility = View.VISIBLE
                itemView.play_icon.visibility = View.VISIBLE

                simpleExoPlayer = createSimpleExoPlayer(itemView.context)
                playerHashMap.put(adapterPosition, simpleExoPlayer!!)
                itemView.playerView.player = simpleExoPlayer
                simpleExoPlayer?.prepare(createMediaSource(mediaUrl!!, itemView.context))


            }
        }

        fun createSimpleExoPlayer(context: Context): SimpleExoPlayer {
            val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory(DefaultBandwidthMeter())
            val trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)
            return ExoPlayerFactory.newSimpleInstance(context, trackSelector)
        }

        public fun createMediaSource(videoUrl: String, context: Context): ExtractorMediaSource? {
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
//                simpleExoPlayer?.addListener(object : Player.EventListener{
//                    override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters?) {
//                    }
//
//                    override fun onSeekProcessed() {
//                    }
//
//                    override fun onTracksChanged(trackGroups: TrackGroupArray?, trackSelections: TrackSelectionArray?) {
//                    }
//
//                    override fun onPlayerError(error: ExoPlaybackException?) {
//                    }
//
//                    override fun onLoadingChanged(isLoading: Boolean) {
//                    }
//
//                    override fun onPositionDiscontinuity(reason: Int) {
//                    }
//
//                    override fun onRepeatModeChanged(repeatMode: Int) {
//                    }
//
//                    override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {
//                    }
//
//                    override fun onTimelineChanged(timeline: Timeline?, manifest: Any?, reason: Int) {
//                    }
//
//                    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
//                    }
//
//                })

}