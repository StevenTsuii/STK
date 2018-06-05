package com.example.steven.stk.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import com.example.steven.stk.R
import com.example.steven.stk.base.activity.BaseActivity
import com.example.steven.stk.extension.plugActivityComponent
import com.example.steven.stk.extension.toast
import com.example.steven.stk.fragment.MainFragment
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



    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, MainFragment()).commit()


        // SubComponent
        plugActivityComponent().inject(this)
        toast("appPackageName: ${appRepository.getPackageName()} " +
                "getVersionCode:${appRepository.getVersionCode()}")

        compositeDisposable.add(Observable.fromArray(arrayOf(""))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(Consumer { }))



    }

}