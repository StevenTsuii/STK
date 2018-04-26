package com.example.steven.stk.module

import com.example.steven.stk.GUEST
import com.example.steven.stk.GUEST_NAME
import com.example.steven.stk.USER_NAME
import com.example.steven.stk.data.User
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Created by steven on 26/3/2018.
 */
@Module
open class UserModule {


    @Provides
    fun providesUser(@Named(USER_NAME) userName: String, age: Int) = User(userName, age)

    @Provides
    @Named(USER_NAME)
    open fun providesUserName() = "StevenTsui"

    @Provides
    open fun providesAge() = 21

    @Provides
    @Named(GUEST)
    fun providesGuest(@Named("guestName") userName: String) = User(userName)

    @Provides
    @Named(GUEST_NAME)
    open fun providesGuestName() = "Guest ABC"


}
