package com.qyb.citylistdemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import kotlin.collections.ArrayList

/**
 * Created by Qing Yuanbiao.
 * Created on 2017/12/19.
 * Desc:
 *      右侧的字母索引View，快速滑动栏
 */
class SideBar : View{

    companion object {
        val INDEX_STRING : Array<String> = arrayOf(
                "A", "B", "C", "D", "E", "F", "G", "H", "I","J", "K", "L", "M",
                "N", "O", "P", "Q", "R", "S", "T", "U", "V","W", "X", "Y", "Z")
    }

    private var showText:TextView? = null  //显示当前按下的字母的TextView
    lateinit var litterList : ArrayList<String>
    private var paint:Paint = Paint()
    private var choose = -1
    private var mListener: OnLetterChangedListener? = null

    constructor(context: Context):super(context){init()}

    constructor(context: Context,attributeSet: AttributeSet):super(context,attributeSet){init()}

    constructor(context: Context,attributeSet: AttributeSet,defstyleRes:Int):super(context,attributeSet,defstyleRes){init()}

    fun init(){
        setBackgroundColor(Color.parseColor("#F0F0F0"))
        litterList = arrayListOf()
        for (i in 0 until INDEX_STRING.size){
            litterList.add(INDEX_STRING[i])
        }
    }

    fun setOnTouchingLetterChangedListener(listener: OnLetterChangedListener){
        mListener = listener
    }

    fun setShowSeletedLitter(textView: TextView){
        showText = textView
    }

    /**
     * 改变数据源
     */
    fun setIndexText(indexString:ArrayList<String>){
        litterList = indexString
        //LogUtils.info("新索引器数据源：${indexString.toString()}")
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //获取对应的宽高
        var width = getWidth()
        var height = getHeight()

        //计算每个字母的高度
        var singleHeight = height / litterList.size
        //画出所有的字母
        paint.typeface = Typeface.DEFAULT_BOLD
        paint.isAntiAlias = true
        paint.textSize = 20f
        for (i in 0 until litterList.size){
            //如果某个字母被选中
            if (i == choose){
                paint.color = Color.parseColor("#4F41FD")
                paint.isFakeBoldText = true
            }else{
                paint.color = Color.parseColor("#606060")
                paint.isFakeBoldText = false
            }
            // x坐标等于中间-字符串宽度的一半.
            var xPos  = width/2 - paint.measureText(litterList.get(i))/2
            //y坐标等于上面字母的个数*每个字母的高度+每个字母高度的一半
            var yPos = singleHeight * i + singleHeight/2
            canvas?.drawText(litterList.get(i),xPos.toFloat(),yPos.toFloat(),paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var action = event?.action
        //点击时y坐标
        var yPos = event?.y
        var oldChoose = choose
        //计算被点击字母的位置,(点击y坐标 / 总高度的比例 = 点击字母位置 / 数组的长度)
        var position :Int= (yPos!! / height * litterList.size).toInt()

        when(action){
            MotionEvent.ACTION_UP -> {
                setBackgroundColor(Color.parseColor("#F0F0F0"))
                choose = -1
                invalidate()
                showText?.visibility = View.GONE
            }
            else -> {
                setBackgroundResource(R.drawable.sidebar_background)
                if (position != oldChoose){
                    if (position >= 0 && position < litterList.size){
                        mListener?.onTouchingLetterChanged(litterList.get(position))
                    }
                    showText?.text = litterList.get(position)
                    showText?.visibility = View.VISIBLE
                }
                choose = position
                invalidate()
            }
        }

        return true
    }


}