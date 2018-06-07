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
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
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

        val bandwidthMeter1 = DefaultBandwidthMeter()
        val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory(bandwidthMeter1)
        val trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)

        val (player, videoSource) = createPlayer(trackSelector)

        testPlayerView.player = player
        testPlayerView2.player = player
        player.prepare(videoSource)


    }

    private fun createPlayer(trackSelector: DefaultTrackSelector): Pair<SimpleExoPlayer, ExtractorMediaSource> {
        // 2. Create the player
        val player = ExoPlayerFactory.newSimpleInstance(activity, trackSelector)
        val bandwidthMeter = DefaultBandwidthMeter()
        // Produces DataSource instances through which media data is loaded.
        val dataSourceFactory = DefaultDataSourceFactory(activity,
                "fgsdfgsdfgsdfg", bandwidthMeter)
        // This is the MediaSource representing the media to be played.
        val videoSource = ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse("https://video.appledaily.com.hk/mcp/encode/2017/12/04/3497170/20171203_ccm_03v40AD_w.mp4"))
        return Pair(player, videoSource)
    }
}