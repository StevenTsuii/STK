package com.example.steven.stk

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.steven.stk.data.FakeData1
import com.example.steven.stk.data.ForeverLife
import com.example.steven.stk.data.LongLife
import com.example.steven.stk.data.ShortLife
import com.example.steven.stk.repo.AppRepository
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by steven on 20/3/2018.
 */
class MainFragment : Fragment() {

    @Inject
    lateinit var fakeData1 : FakeData1

    @Inject
    lateinit var appRepository: AppRepository

    @Inject
    @Named(FRAGMENT_MODULE_CREATED_TIME)
    lateinit var fragmentModuleCreatedTime: String

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_main, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        App.instance.plusFragmentComponent().inject(this)
        val item0 = MainData(fragmentModuleCreatedTime, appRepository.getVersionCode(), "NA");
        val item1 = MainData("steven", 20, "HK");
        val item2 = MainData(name = "G_G", age = 10, address = "NY");
        val item3 = item1.copy(name = "StevenTsui", age = 25);
        var itemList = arrayListOf(item0, item1, item2, item3)

        var mainAdapter = MainAdapter(activity.baseContext, itemList)

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = mainAdapter

        Toast.makeText(activity.baseContext, "Yeah11111123!!!", Toast.LENGTH_LONG).show()
        refreshButton.setText("Refresh")
//
//        var lifeComponent = DaggerLifeComponent.create()
//
//        refreshButton.setOnClickListener {
//
//            var lifeSubComponent = lifeComponent.getLifeSubComponent()
//            var lifeSpan = LifeSpan()
//            lifeSubComponent.inject(lifeSpan)
//            Log.d("StevenCheck", "ShortLife ${lifeSpan.shortLife.createdTimestamp}");
//            Log.d("StevenCheck", "LongLife ${lifeSpan.longLife.createdTimestamp}");
//            Log.d("StevenCheck", "ForeverLife ${lifeSpan.foreverLife.createdTimestamp}");
//            Log.d("StevenCheck", "=================================");
//
//        }


    }
}

class LifeSpan() {
    @Inject lateinit var shortLife: ShortLife
    @Inject lateinit var longLife: LongLife
    @Inject lateinit var foreverLife: ForeverLife
}