package com.example.steven.stk.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.steven.stk.FRAGMENT_MODULE_CREATED_TIME
import com.example.steven.stk.R
import com.example.steven.stk.adapter.MainPagerAdapter
import com.example.steven.stk.base.activity.BaseFragment
import com.example.steven.stk.data.network.STKService
import com.example.steven.stk.data.network.STKService2
import com.example.steven.stk.extension.log
import com.example.steven.stk.extension.plugFragmentComponent
import com.example.steven.stk.extension.toast
import com.example.steven.stk.repo.AppRepository
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.reward.RewardItem
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_article_list_container.*
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by steven on 5/6/2018.
 */
class ArticleListContainerFragment : BaseFragment() {


    @Inject
    lateinit var stkService: STKService

    @Inject
    lateinit var stkService2: STKService2

    @Inject
    lateinit var appRepository: AppRepository

    @Inject
    lateinit var interstitialAd: InterstitialAd

    @Inject
    lateinit var rewardedVideoAd: RewardedVideoAd

    @Inject
    @Named(FRAGMENT_MODULE_CREATED_TIME)
    lateinit var fragmentModuleCreatedTime: String

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_article_list_container, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        plugFragmentComponent().inject(this)
        refreshButton.setText("Refresh")
        refreshButton.setOnClickListener {
//            if (rewardedVideoAd.isLoaded) {
//                rewardedVideoAd.show()
//            }else{
//                loadRewardedVideoAd()
//            }
        }

        initInterstitialAd()

        initBannerAd()

        loadRewardedVideoAd()


        viewPager.adapter = MainPagerAdapter(fragmentManager)

        stkService.startUp()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {
                    log("Startup Result: ${it}")
                    resultTextView.text = "Startup Result: ${it.state}"
                })


        stkService.sideMenu()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {
                    resultTextView.text = "${resultTextView.text}\n\nsideMenu Result:${it.state}"
                    log("SideMenu Result: ${it}")
                })

        stkService2.newsCatList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {
                    resultTextView.text = "${resultTextView.text}\n\nNewsCatList Result:${it.state}"
                    log("NewsCatList Result: ${it.state}")
                })


    }

    private fun loadRewardedVideoAd() {
        rewardedVideoAd.rewardedVideoAdListener = object : RewardedVideoAdListener {
            override fun onRewardedVideoAdClosed() {
            }

            override fun onRewardedVideoAdLeftApplication() {
            }

            override fun onRewardedVideoAdLoaded() {
                if (rewardedVideoAd.isLoaded) {
                    rewardedVideoAd.show()
                }
            }

            override fun onRewardedVideoAdOpened() {
            }

            override fun onRewardedVideoCompleted() {
            }

            override fun onRewarded(rewardItem: RewardItem?) {
                toast("Rewarded got amount: ${rewardItem?.amount} type: ${rewardItem?.type}")
            }

            override fun onRewardedVideoStarted() {
            }

            override fun onRewardedVideoAdFailedToLoad(p0: Int) {
            }

        }

        rewardedVideoAd.loadAd("\n" +
                "ca-app-pub-4344418392776540/3923059681", //test "ca-app-pub-3940256099942544/5224354917",
                AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build())
    }

    private fun initBannerAd() {
        adView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                log("onAdLoaded  adView.isShown:${adView.isShown}")

            }

            override fun onAdFailedToLoad(errorCode: Int) {
                // Code to be executed when an ad request fails.
                log("onAdFailedToLoad")
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                log("onAdOpened")
            }

            override fun onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                log("onAdLeftApplication")
            }

            override fun onAdClosed() {
                // Code to be executed when when the user is about to return
                // to the app after tapping on an ad.
                log("onAdClosed")
            }
        }


        val adRequest = AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build()
        adView.loadAd(adRequest)
    }

    private fun initInterstitialAd() {
        interstitialAd.loadAd(AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build())
        interstitialAd.adListener = object : AdListener() {
            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                log("interstitialAd onAdLoaded")
                interstitialAd.show()
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                // Code to be executed when an ad request fails.
                log("interstitialAd onAdFailedToLoad")
            }

            override fun onAdOpened() {
                // Code to be executed when the ad is displayed.
                log("interstitialAd onAdOpened")
            }

            override fun onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                log("interstitialAd onAdLeftApplication")
            }

            override fun onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
                log("interstitialAd onAdClosed")
            }
        }
    }
}

