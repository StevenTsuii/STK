package com.example.steven.stk.activity

import android.graphics.drawable.Animatable2
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import com.example.steven.stk.AppEnvironmentConfig
import com.example.steven.stk.AppLevelConfig
import com.example.steven.stk.BuildConfig
import com.example.steven.stk.R
import com.example.steven.stk.base.activity.BaseActivity
import com.example.steven.stk.extension.log
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_play_vector.*
import java.util.concurrent.TimeUnit


class PlayVectorActivity : BaseActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_vector)

        var drawable = imageView.drawable as Animatable2
        drawable.registerAnimationCallback(@RequiresApi(Build.VERSION_CODES.M)
        object : Animatable2.AnimationCallback() {
            override fun onAnimationEnd(drawable2: Drawable) {
                var drawable2 = imageView.drawable as Animatable2
                drawable2.start()
            }
        })

        drawable.start()

        text2.text = "applicationId:\n ${BuildConfig.APPLICATION_ID}" +
                "\n\nFlavor:\n ${BuildConfig.FLAVOR}" +
                "\n\nEnvironment: ${AppEnvironmentConfig.environment}" +
                "\nLevel:${AppLevelConfig.level}"


        Observable.create(ObservableOnSubscribe<String> { emitter ->
            var count = 0
            imageView.setOnClickListener {
                emitter.onNext("hello${count++}")
            }
        })
//                .debounce(2, TimeUnit.SECONDS)
                .buffer(400, TimeUnit.MILLISECONDS)
                .filter { it.size >= 2 }
                .map { "success" }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    log("consumer: ${it}")
                }


    }
}