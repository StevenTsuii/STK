package com.example.steven.stk.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.steven.stk.fragment.ArticleListFragment

/**
 * Created by steven on 23/5/2018.
 */
class MainPagerAdapter(fragmentManager : FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    val sections = arrayOf("AA", "BB", "CC", "DD", "EE")

    override fun getItem(position: Int): Fragment {
        return ArticleListFragment()
    }

    override fun getCount(): Int {
        return sections.size
    }

}