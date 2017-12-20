#A-Z字母排序功能实现选择城市
##界面不是很炫酷，重点在于实现的思路方法，掌握原理才能千变万化(装B界大佬^_^)
##先看下效果图
![](https://raw.githubusercontent.com/AnselChopin/pictureRes/master/citylist.gif)

##实现步骤如下（注释都写在了源码中，这里就不啰嗦了）：
##1、准备数据源：
##	具体在在资源文件中，这里我采用xml的方式，简单包含了全国的省、直辖市等等
##2、CityModel
##3、CityComparator	
##4、PinyinUtils(这里引用了汉字转拼音的三方库pinyin4j)
##		implementation 'com.github.SilenceDut:jpinyin:v1.0'
##5、SideBar
##6、CityAdapter
##有兴趣的猿们可以下载源码