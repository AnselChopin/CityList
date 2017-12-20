package com.qyb.citylistdemo

import com.github.stuxuhai.jpinyin.PinyinFormat
import com.github.stuxuhai.jpinyin.PinyinHelper

/**
 * Created by Qing Yuanbiao.
 * Created on 2017/12/19.
 * Desc:
 *      将汉字转换为拼音
 */
object PinyinUtils {

    /**
     * 获取首字母
     * @param chinese 汉字
     */
    fun getInitials(chinese:String) : String{
        return PinyinHelper.getShortPinyin(chinese)
    }

    /**
     * 将字符串中的中文转化为拼音,英文字符不变
     * @param chinese 汉字
     */
    fun convertToPinyin(chinese:String,separator:String) : String{
        return PinyinHelper.convertToPinyinString(chinese,separator,PinyinFormat.WITHOUT_TONE)
    }
}