package com.example.steven.stk.activity

import android.os.Bundle
import com.example.steven.stk.R
import com.example.steven.stk.base.activity.BaseActivity
import com.example.steven.stk.extension.log
import com.example.steven.stk.extension.plugActivityComponent
import com.example.steven.stk.gtm.ContainerLoadedCallback
import com.example.steven.stk.gtm.GoogleTagManagerHelper
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.tagmanager.ContainerHolder
import com.google.android.gms.tagmanager.TagManager
import com.google.android.tagmanager.examples.cuteanimals.ContainerHolderSingleton
import kotlinx.android.synthetic.main.activity_test_gtm.*
import java.util.concurrent.TimeUnit

/**
 * Created by steven on 4/7/2018.
 */
class TestGTMActivity : BaseActivity() {

    lateinit var tagManager: TagManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_test_gtm)

        plugActivityComponent().inject(this)

        loadGTMContainer()

        bottomBarButtonTag.setOnClickListener {
            GoogleTagManagerHelper.pushBottomBarButtonTag(this)
        }
        bottomBarButtonTag2.setOnClickListener {
            GoogleTagManagerHelper.pushBottomBarButtonTag2(this)
        }
        bottomBarButtonTag3.setOnClickListener {
            GoogleTagManagerHelper.pushBottomBarButtonTag3(this)
        }

        screenViewTag.setOnClickListener {
            GoogleTagManagerHelper.pushScreenViewTag(this)
        }
        screenViewTag2.setOnClickListener {
            GoogleTagManagerHelper.pushScreenViewTag2(this)
        }
        screenViewTag3.setOnClickListener {
            GoogleTagManagerHelper.pushScreenViewTag3(this)
        }
        navigationButton1.setOnClickListener {
            GoogleTagManagerHelper.pushNavigationButtonTag(this, "Home")
        }
        navigationButton2.setOnClickListener {
            GoogleTagManagerHelper.pushNavigationButtonTag(this, "Dashboard")
        }
        navigationButton3.setOnClickListener {
            GoogleTagManagerHelper.pushNavigationButtonTag(this, "Setting")
        }

        readArticleTag.setOnClickListener {
            GoogleTagManagerHelper.pushReadArticleTag(this, "articleA")
        }

        readArticleTag2.setOnClickListener {
            GoogleTagManagerHelper.pushReadArticleTag2(this, "articleB")
        }

        readArticleTag3.setOnClickListener {
            GoogleTagManagerHelper.pushReadArticleTag3(this, "articleC")
        }


        normalEventTag.setOnClickListener {
            GoogleTagManagerHelper.pushNormalEventTag(this, "categoryA", action = "actionA", label = "labelA", value = "valueA")
        }

        normalEventTag2.setOnClickListener {
            GoogleTagManagerHelper.pushNormalEventTag(this, "categoryB", action = "actionB", label = "labelB", value = "valueB")
        }


        normalEventTag3.setOnClickListener {
            GoogleTagManagerHelper.pushNormalEventTag(this, "categoryC", action = "actionC", label = "labelC", value = "valueC")
        }

        refreshContainer.setOnClickListener { GoogleTagManagerHelper.refreshContainer() }
    }

    private fun loadGTMContainer() {
        tagManager = TagManager.getInstance(this)
        tagManager.setVerboseLoggingEnabled(true)

        val pending = tagManager.loadContainerPreferNonDefault("GTM-PLFQ5FV",
                R.raw.gtm_v4_container_binary31)

        pending.setResultCallback(object : ResultCallback<ContainerHolder> {
            override fun onResult(containerHolder: ContainerHolder) {
                ContainerHolderSingleton.containerHolder = containerHolder
                val container = containerHolder.container
                if (!containerHolder.status.isSuccess) {
                    log("load Container failure")
                    return
                }
                log("load Container success")
                ContainerLoadedCallback.registerCallbacksForContainer(container)
                containerHolder.setContainerAvailableListener(ContainerLoadedCallback())
            }


        }, 100000, TimeUnit.SECONDS)
    }
}