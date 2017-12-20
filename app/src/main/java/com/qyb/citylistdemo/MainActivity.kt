package com.qyb.citylistdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var sourceDateList:ArrayList<CityModel>
    lateinit var adapter:CityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findView()
        setEvent()
    }

    fun findView(){
        main_sidebar.setShowSeletedLitter(showLitter)
        sourceDateList = filledData(resources.getStringArray(R.array.provinces))
        adapter = CityAdapter(sourceDateList)
        main_rvlist.layoutManager = LinearLayoutManager(this)
        main_rvlist.adapter = adapter
    }

    fun setEvent(){
        //将列表和右侧快速滑动蓝关联起来
        main_sidebar.setOnTouchingLetterChangedListener(object : OnLetterChangedListener {
            override fun onTouchingLetterChanged(s: String) {
                var position = adapter.getPositionForSection(s[0].toInt())
                if (position != -1) main_rvlist.smoothScrollToPosition(position)
            }
        })
    }


    /**
     * 从xml中获取所有省份
     */
    fun filledData(data:Array<String>) : ArrayList<CityModel>{
        var mSortList = ArrayList<CityModel>()
        var indexString = ArrayList<String>()

        for (i in 0 until data.size){
            var sortModel = CityModel("","")
            sortModel.name = data[i]
            var firstLitter = PinyinUtils.getInitials(data[i]).toUpperCase().substring(0,1)
            if (firstLitter.matches("[A-Z]".toRegex())){
                sortModel.sortLitter = firstLitter
                if (!indexString.contains(firstLitter)) indexString.add(firstLitter)
            }
            mSortList.add(sortModel)
        }

        Collections.sort(indexString)
        Collections.sort(mSortList, CityComparator())

        main_sidebar.setIndexText(indexString)

        return mSortList
    }

}
