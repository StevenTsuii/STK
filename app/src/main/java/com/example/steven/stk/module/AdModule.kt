package com.example.steven.stk.module

import android.content.Context
import com.example.steven.stk.annotation.ActivityScope
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.reward.RewardedVideoAd
import dagger.Module
import dagger.Provides

/**
 * Created by steven on 4/6/2018.
 */
@Module
class AdModule {

    @Provides
    @ActivityScope
    fun providesInterstitialAd(context: Context): InterstitialAd {
        var intersititalAd = InterstitialAd(context)
        //intersititalAd.adUnitId = "ca-app-pub-4344418392776540/1863517339"
        intersititalAd.adUnitId = "ca-app-pub-3940256099942544/1033173712" //google provide test id
        return intersititalAd
    }

    @Provides
    @ActivityScope
    fun providesRewardedVideoAd(context: Context): RewardedVideoAd{
        return MobileAds.getRewardedVideoAdInstance(context)
    }
}