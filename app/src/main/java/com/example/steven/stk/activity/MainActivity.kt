package com.example.steven.stk.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import com.example.steven.stk.R
import com.example.steven.stk.base.activity.BaseActivity
import com.example.steven.stk.extension.disableShiftMode
import com.example.steven.stk.extension.plugActivityComponent
import com.example.steven.stk.extension.replaceFragment
import com.example.steven.stk.extension.toast
import com.example.steven.stk.fragment.ArticleListContainerFragment
import com.example.steven.stk.fragment.SecondFragment
import com.example.steven.stk.repo.AppRepository
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



    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                replaceFragment(articleListContainerFragment, R.id.fragmentContainer)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                replaceFragment(SecondFragment(), R.id.fragmentContainer)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                replaceFragment(ArticleListContainerFragment(), R.id.fragmentContainer)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_steven -> {
                message.setText("???")
                replaceFragment(SecondFragment(), R.id.fragmentContainer)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                message.setText(R.string.title_notifications)
                replaceFragment(ArticleListContainerFragment(), R.id.fragmentContainer)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        plugActivityComponent().inject(this)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.disableShiftMode()

        toast("appPackageName: ${appRepository.getPackageName()} " +
                "getVersionCode:${appRepository.getVersionCode()}")

        compositeDisposable.add(Observable.fromArray(arrayOf(""))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(Consumer { }))


        replaceFragment(ArticleListContainerFragment(), R.id.fragmentContainer)

    }

}