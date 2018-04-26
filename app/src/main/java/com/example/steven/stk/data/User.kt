package com.example.steven.stk.data

/**
 * Created by steven on 26/3/2018.
 */
class User constructor(userName: String) {

    val mUserName = userName
    var mAge: String? = null

    constructor(userName: String, age: Int) : this(userName) {
        mAge = age.toString()
    }
}