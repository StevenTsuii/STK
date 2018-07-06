package com.example.steven.stk.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import com.example.steven.stk.R
import com.example.steven.stk.base.activity.BaseActivity
import com.example.steven.stk.extension.disableShiftMode
import com.example.steven.stk.extension.plugActivityComponent
import com.example.steven.stk.extension.replaceFragment
import com.example.steven.stk.extension.toast
import com.example.steven.stk.fragment.ArticleListContainerFragment
import com.example.steven.stk.repo.AppRepository
import com.google.android.gms.tagmanager.TagManager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity() {

    @Inject
    lateinit var appRepository: AppRepository

    @Inject
    lateinit var compositeDisposable: CompositeDisposable

    val articleListContainerFragment = ArticleListContainerFragment()

    lateinit var tagManager: TagManager


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                replaceFragment(articleListContainerFragment, R.id.fragmentContainer)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {

//                message.setText(R.string.title_dashboard)
//                replaceFragment(SecondFragment(), R.id.fragmentContainer)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
 //               ContainerHolderSingleton.containerHolder?.refresh()
//                message.setText(R.string.title_notifications)
//                replaceFragment(ArticleListContainerFragment(), R.id.fragmentContainer)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_steven -> {
//                message.setText("???")
//                replaceFragment(SecondFragment(), R.id.fragmentContainer)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
//                GoogleTagManagerHelper.pushPageViewEvent(this, "SETTING_PAGE")
                message.setText(R.string.title_notifications)
                replaceFragment(ArticleListContainerFragment(), R.id.fragmentContainer)
                val intent = Intent(this, ArticleDetailActivity::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        plugActivityComponent().inject(this)

//        loadGTMContainer()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.disableShiftMode()

        toast("appPackageName: ${appRepository.getPackageName()} " +
                "getVersionCode:${appRepository.getVersionCode()}")

        compositeDisposable.add(Observable.fromArray(arrayOf(""))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(Consumer { }))


        replaceFragment(ArticleListContainerFragment(), R.id.fragmentContainer)

    }

//    private fun loadGTMContainer() {
//        tagManager = TagManager.getInstance(this)
//        tagManager.setVerboseLoggingEnabled(true)
//
//        val pending = tagManager.loadContainerPreferNonDefault("GTM-PLFQ5FV",
//                R.raw.gtm_v4_container_binary28)
//
//        pending.setResultCallback(object : ResultCallback<ContainerHolder> {
//            override fun onResult(containerHolder: ContainerHolder) {
//                ContainerHolderSingleton.containerHolder = containerHolder
//                val container = containerHolder.container
//                if (!containerHolder.status.isSuccess) {
//                    log("load Container failure")
//                    return
//                }
//                log("load Container success")
//                ContainerLoadedCallback.registerCallbacksForContainer(container)
//                containerHolder.setContainerAvailableListener(ContainerLoadedCallback())
//            }
//
//
//        }, 100000, TimeUnit.SECONDS)
//    }

}