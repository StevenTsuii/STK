package com.example.steven.stk.fragment


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.steven.stk.FRAGMENT_MODULE_CREATED_TIME
import com.example.steven.stk.MainData
import com.example.steven.stk.R
import com.example.steven.stk.adapter.MainAdapter
import com.example.steven.stk.adapter.MainPagerAdapter
import com.example.steven.stk.base.activity.BaseFragment
import com.example.steven.stk.data.ForeverLife
import com.example.steven.stk.data.LongLife
import com.example.steven.stk.data.ShortLife
import com.example.steven.stk.data.network.STKService
import com.example.steven.stk.data.network.STKService2
import com.example.steven.stk.extension.log
import com.example.steven.stk.extension.plugFragmentComponent
import com.example.steven.stk.extension.toast
import com.example.steven.stk.repo.AppRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by steven on 20/3/2018.
 */
class MainFragment : BaseFragment() {

    @Inject
    lateinit var stkService: STKService

    @Inject
    lateinit var stkService2: STKService2

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

        plugFragmentComponent().inject(this)
        val item0 = MainData(fragmentModuleCreatedTime, appRepository.getVersionCode(), "NA");
        val item1 = MainData("steven", 20, "HK");
        val item2 = MainData(name = "G_G", age = 10, address = "NY");
        val item3 = item1.copy(name = "StevenTsui", age = 25);
        var itemList = arrayListOf(item0, item1, item2, item3)

        var mainAdapter = MainAdapter(activity.baseContext, itemList)

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = mainAdapter

        toast("MainFragment")
        refreshButton.setText("Refresh")

        viewPager.adapter = MainPagerAdapter(fragmentManager)




        stkService.startUp()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {
                    log("Startup Result: ${it}")
                    resultTextView.text = "Startup Result: ${it.state}"
                })


        stkService.sideMenu()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {
                    resultTextView.text = "${resultTextView.text}\n\nsideMenu Result:${it.state}"
                    log("SideMenu Result: ${it}")
                })

        stkService2.newsCatList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {
                    resultTextView.text = "${resultTextView.text}\n\nNewsCatList Result:${it.state}"
                    log("NewsCatList Result: ${it.state}")
                })

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