package com.example.steven.stk

import com.example.steven.stk.component.InfoComponent
import com.example.steven.stk.data.User
import com.example.steven.stk.module.InfoModule
import com.example.steven.stk.module.UserModule
import dagger.Component
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by steven on 26/3/2018.
 */
class TestMainActivity {
    @Inject
    lateinit var user: User

    @Inject
    @field:Named(GUEST)
    lateinit var guest: User

    @Before
    fun setup() {
        DaggerTestInfoComponent.builder().userModule(TestUserModule()).build().inject(this)
    }

    @Test
    fun simpleTest() {
        assert(user!=null)
        assert(guest!=null)
        assertEquals("laugh die me", user.mUserName)
        assertEquals("Guest ABC", guest.mUserName)
        assert(user.mAge?.toInt() ?: 0 >50)
        assert(guest.mAge?.toInt() ?: 0 >50)
    }



}

class TestUserModule : UserModule() {
    override fun providesUserName(): String {
        return "laugh die me"
    }
}

@Component(modules = arrayOf(InfoModule::class, UserModule::class))
interface TestInfoComponent : InfoComponent {
    fun inject(testMainActivity: TestMainActivity)
}