package com.example.steven.stk.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.steven.stk.R
import com.example.steven.stk.base.activity.BaseFragment
import com.example.steven.stk.extension.plugFragmentComponent
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import kotlinx.android.synthetic.main.fragment_second.*

/**
 * Created by steven on 5/6/2018.
 */
class SecondFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        plugFragmentComponent().inject(this)




        playMediaSource(testPlayerView, "http://video.appledaily.com.hk/mcp/encode/2018/06/07/3626607/20180607_chi_612_w.mp4")
        playMediaSource(testPlayerView2, "http://video.appledaily.com.hk/mcp/encode/2018/06/06/3626383/20180608_Sub573new_clean_w.mp4")
        playMediaSource(testPlayerView3, "http://video.appledaily.com.hk/mcp/encode/2018/06/07/3626915/180530GeorgeMing_PhoMetro_ADL_mp4_w.mp4")


    }


    private fun playMediaSource(playerView: PlayerView, videoUrl: String) {
        val bandwidthMeter = DefaultBandwidthMeter()
        val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory(bandwidthMeter)
        val trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)
        val player = ExoPlayerFactory.newSimpleInstance(activity, trackSelector)

        playerView.player = player

        player.prepare(createMediaSource(bandwidthMeter, videoUrl))

    }

    private fun createMediaSource(bandwidthMeter: DefaultBandwidthMeter, videoUrl: String): ExtractorMediaSource? {
        val defaultHttpDataSourceFactory = DefaultHttpDataSourceFactory("Android", bandwidthMeter,
                DefaultHttpDataSource.DEFAULT_CONNECT_TIMEOUT_MILLIS,
                DefaultHttpDataSource.DEFAULT_READ_TIMEOUT_MILLIS,
                true)

        // Produces DataSource instances through which media data is loaded.
        val dataSourceFactory = DefaultDataSourceFactory(activity, bandwidthMeter,
                defaultHttpDataSourceFactory)
        // This is the MediaSource representing the media to be played.
        return ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(videoUrl))

    }
}