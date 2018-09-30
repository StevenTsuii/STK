package com.example.steven.stk.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.steven.stk.R
import com.example.steven.stk.base.activity.BaseFragment
import com.example.steven.stk.data.ForeverLife
import com.example.steven.stk.data.LongLife
import com.example.steven.stk.data.ShortLife
import com.example.steven.stk.extension.plugFragmentComponent
import javax.inject.Inject

/**
 * Created by steven on 20/3/2018.
 */
class MainFragment : BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_main, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        plugFragmentComponent().inject(this)



    }
}

class LifeSpan() {
    @Inject
    lateinit var shortLife: ShortLife
    @Inject
    lateinit var longLife: LongLife
    @Inject
    lateinit var foreverLife: ForeverLife
}