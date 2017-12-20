package com.qyb.citylistdemo

/**
 * Created by Qing Yuanbiao.
 * Created on 2017/12/18.
 * Desc:
 *      城市实体类
 *      name        城市名字
 *      sortLitter  首字母大写
 */
data class CityModel(var name:String, var sortLitter:String) {
    override fun toString(): String {
        return "CityModel(name='$name', sortLitter='$sortLitter')"
    }
}