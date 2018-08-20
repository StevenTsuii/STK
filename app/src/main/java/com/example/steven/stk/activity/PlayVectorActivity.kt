package com.example.steven.stk.activity

import android.graphics.drawable.Animatable2
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import com.example.steven.stk.R
import com.example.steven.stk.base.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_play_vector.*



class PlayVectorActivity: BaseActivity(){
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
    }
}