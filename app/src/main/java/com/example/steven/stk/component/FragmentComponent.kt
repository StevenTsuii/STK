package com.example.steven.stk.component

import com.example.steven.stk.annotation.FragmentScope
import com.example.steven.stk.fragment.*
import com.example.steven.stk.module.FragmentModule
import dagger.Subcomponent

/**
 * Created by steven on 26/4/2018.
 */
@Subcomponent(modules = arrayOf(FragmentModule::class))
@FragmentScope
interface FragmentComponent {
    fun inject(mainFragment: MainFragment)
    fun inject(articleListFragment: ArticleListFragment)
    fun inject(fragmentArticleListContainerFragment: ArticleListContainerFragment)
    fun inject(secondFragment: SecondFragment)
    fun inject(myCloudFragment: MyCloudFragment)
}