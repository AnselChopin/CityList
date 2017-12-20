package com.qyb.citylistdemo

/**
 * Created by Qing Yuanbiao.
 * Created on 2017/12/19.
 * Desc:
 *      对数据进行A-Z排序
 */
class CityComparator : Comparator<CityModel>{
    override fun compare(o1: CityModel?, o2: CityModel?): Int {
        return o1?.sortLitter!!.compareTo(o2?.sortLitter!!)
    }

}