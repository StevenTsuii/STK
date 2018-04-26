package com.example.steven.stk

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.steven.stk.repo.AppRepository
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var appRepository: AppRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.plusActivityComponent().inject(this)
        Toast.makeText(this, "appPackageName: ${appRepository.getPackageName()} " +
                "getVersionCode:${appRepository.getVersionCode()}", Toast.LENGTH_LONG).show()
    }

    //    @Inject
//    lateinit var deviceInfo: DeviceInfo
//
//    @Inject
//    lateinit var user: User
//
//    @Inject
//    @field:Named(GUEST)
//    lateinit var guest: User
//
//    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
//        when (item.itemId) {
//            R.id.navigation_home -> {
//                message.setText(R.string.title_home)
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.navigation_dashboard -> {
//                message.setText(R.string.title_dashboard)
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.navigation_notifications -> {
//                message.setText(R.string.title_notifications)
//                return@OnNavigationItemSelectedListener true
//            }
//        }
//        false
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
//        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, MainFragment()).commit()
//
//        DaggerInfoComponent.create().inject(this)
//
//        Log.d("StevenCheck", "DeviceInfo Tag: ${deviceInfo.mTag}")
//        Log.d("StevenCheck", "User userName: ${user.mUserName} age:${user.mAge}")
//        Log.d("StevenCheck", "Guest guestName: ${guest.mUserName} age:${guest.mAge}")
//    }

}