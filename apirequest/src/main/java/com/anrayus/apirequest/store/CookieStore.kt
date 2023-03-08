package com.anrayus.apirequest.store

import okhttp3.Cookie

/**
 * cookie默认以HashMap形式储存于内存中，若要持久化储存cookie，则应使用[CookieStore.setCookieEvent].
 * 这需要重写[CookieEvent]中的[CookieEvent.saveCookieEvent]和[CookieEvent.loadCookieEvent]函数。
 * NOTE: cookieEven是一个静态变量，在任何地方使用[CookieStore.setCookieEvent]都将覆盖原有的函数
 */
class CookieStore {
    companion object{
        private val cookieMap = HashMap<String,List<Cookie>>()
    }

    fun load(host:String): List<Cookie>? {
        cookieEvent?.loadCookieEvent(host,cookieMap)
        return cookieMap[host]
    }

    fun save(host: String,cookies: List<Cookie>){
        cookieEvent?.saveCookieEvent(cookies, host)
        cookieMap[host] = cookies
    }

    /**
     * 设置cookie事件，这将覆盖原有的事件
     * @param event 实现[CookieEvent]
     */
    private var cookieEvent: CookieEvent? = null
    fun setCookieEvent(event: CookieEvent){
        cookieEvent = event
    }

    interface CookieEvent{
        fun saveCookieEvent(cookies: List<Cookie>, host:String)
        fun loadCookieEvent(host:String,cookieMap: HashMap<String,List<Cookie>>)
    }

}