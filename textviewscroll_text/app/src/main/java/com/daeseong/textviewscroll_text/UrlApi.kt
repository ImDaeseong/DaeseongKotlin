package com.daeseong.textviewscroll_text


class UrlApi private constructor() {

    private val items: MutableList<UrlItem> = ArrayList()

    companion object {
        private var instance: UrlApi? = null

        fun getInstance(): UrlApi {
            if (instance == null) {
                instance = UrlApi()
            }
            return instance!!
        }
    }

    fun setItem(text: String, url: String) {
        val item = UrlItem()
        item.setItem(text, url)
        items.add(item)
    }

    fun getItem(nItem: Int): UrlItem? {
        return if (nItem >= 0 && nItem < items.size) {
            items[nItem]
        } else {
            null
        }
    }

    fun getItems(): List<UrlItem> {
        return items
    }

    fun clear() {
        items.clear()
    }

    inner class UrlItem {
        private var text: String? = null
        private var url: String? = null

        fun setItem(text: String, url: String) {
            this.text = text
            this.url = url
        }

        fun getText(): String? {
            return text
        }

        fun getUrl(): String? {
            return url
        }
    }
}