package com.daeseong.paging_test.API

class SearchApi {

    private val tag = SearchApi::class.java.simpleName

    companion object {

        private var instance: SearchApi? = null

        fun getInstance() : SearchApi {
            if (instance == null) {
                instance = SearchApi()
            }
            return instance as SearchApi
        }
    }

    private val map: HashMap<Int, List<itemData>> = HashMap()

    fun clear() {
        map.clear()
    }

    fun setMap(nIndex: Int, list: List<itemData>): Boolean {
        if (map.containsKey(nIndex)) {
            return false
        }
        map[nIndex] = list
        return true
    }

    fun getItem(nIndex: Int): List<itemData>? {
        return if (map.containsKey(nIndex)) {
            map[nIndex]!!
        } else null
    }

    fun getItem(): HashMap<Int, List<itemData>> {
        return map
    }

    fun getSearch(sKey: String?): List<itemData>? {
        val listA: MutableList<itemData> = ArrayList()
        for (key in map.keys) {
            val list = map[key]
            if (list != null) {
                for (i in list) {
                    if (i.NAME.toString().indexOf(sKey!!) > -1) {
                        val item = itemData()
                        item.setItem(i.ID, i.NAME, i.HTMLURL, i.CreateData)
                        listA.add(item)
                    }
                }
            }
        }
        return listA
    }

    class itemData {

        var ID: String? = null
        var NAME: String? = null
        var HTMLURL: String? = null
        var CreateData: String? = null

        fun setItem(ID: String?, NAME: String?, HTMLURL: String?, CreateData: String?) {
            this.ID = ID
            this.NAME = NAME
            this.HTMLURL = HTMLURL
            this.CreateData = CreateData
        }
    }
}
