package com.example.steven.stk.module

import android.content.Context
import com.example.steven.stk.annotation.ActivityScope
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelection
import com.google.android.exoplayer2.upstream.BandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import dagger.Module
import dagger.Provides

/**
 * Created by steven on 6/6/2018.
 */
@Module
class PlayerModule {

    @Provides
    @ActivityScope
    fun providesBandwidthMeter(): BandwidthMeter{
        return DefaultBandwidthMeter()
    }

    @Provides
    @ActivityScope
    fun providesVideoTrackSelectionFactory(bandwidthMeter: BandwidthMeter): TrackSelection.Factory{
        return AdaptiveTrackSelection.Factory(bandwidthMeter)
    }

    @Provides
    @ActivityScope
    fun providesTrackSelector(videoTrackSelectionFactory: TrackSelection.Factory): DefaultTrackSelector {
        return DefaultTrackSelector(videoTrackSelectionFactory)
    }

    @Provides
    @ActivityScope
    fun providesSimpleExoPlayer(context: Context, trackSelector: DefaultTrackSelector): SimpleExoPlayer {
        return ExoPlayerFactory.newSimpleInstance(context, trackSelector)
    }
}