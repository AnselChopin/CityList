package com.qyb.citylistdemo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SectionIndexer
import android.widget.TextView

/**
 * Created by Qing Yuanbiao.
 * Created on 2017/12/19.
 * Desc:
 *      重点：SectionIndexer接口的使用
 */
class CityAdapter : RecyclerView.Adapter<CityAdapter.CityViewHolder>,SectionIndexer {


    private var mData : ArrayList<CityModel>

    constructor(data : ArrayList<CityModel>){
        mData = data
        //LogUtils.info(mData.toString())
    }

    fun updateData(data : ArrayList<CityModel>){
        mData = data
        notifyDataSetChanged()
    }

    override fun getSections(): Array<Any> {
        return arrayOf()
    }

    /**
     * 根据ListView的position来找出当前位置所在的分组
     */
    override fun getSectionForPosition(position: Int): Int {
        var shortStr = mData.get(position).sortLitter
        //LogUtils.info("这个section是：$shortStr")
        return shortStr[0].toInt()
    }

    /**
     * 根据首字母的Char值来获取在该ListView中第一次出现该首字母的位置,也就是当前分组所在的位置
     */
    override fun getPositionForSection(sectionIndex: Int): Int {
        for (i in 0 until itemCount){
            var firstLitter = mData.get(i).sortLitter[0].toUpperCase()
            if (sectionIndex == firstLitter.toInt()) return i
        }
        return -1
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CityViewHolder {
        var itemView = LayoutInflater.from(parent?.context).inflate(R.layout.item_select_city,parent,false)
        return CityViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: CityViewHolder?, position: Int) {
        holder?.onBind()
    }


    /* ------------------------------------- pretty divide line ----------------------------------*/
    inner class CityViewHolder : RecyclerView.ViewHolder{

        private lateinit var tvCityLitter:TextView
        private lateinit var tvCityName:TextView

        constructor(itemView:View) : super(itemView){findView()}

        fun findView(){
            tvCityLitter = itemView.findViewById(R.id.tv_catagory)
            tvCityName = itemView.findViewById(R.id.tv_city_name)
        }

        fun onBind(){
            //找到当前位置所在的分组
            var section = getSectionForPosition(layoutPosition)
            //如果当前位置就是一个分组第一次出现的位置,就显示代表这个分组的字母
            if (layoutPosition == getPositionForSection(section)){
                tvCityLitter.visibility = View.VISIBLE
                tvCityLitter.text = mData.get(layoutPosition).sortLitter
            }else{
                tvCityLitter.visibility = View.GONE
            }
            tvCityName.text = mData.get(layoutPosition).name
        }

    }
}