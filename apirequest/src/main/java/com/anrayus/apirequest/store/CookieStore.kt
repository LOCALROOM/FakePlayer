package com.anrayus.apirequest.store

/**
 * cookie默认以HashMap形式储存于内存中，若要持久化储存cookie，则应使用[CookieStore.setCookieEvent].
 * 这需要重写[CookieEvent]中的[CookieEvent.saveCookieEvent]和[CookieEvent.loadCookieEvent]函数。
 * NOTE: cookieEven是一个静态变量，在任何地方使用[CookieStore.setCookieEvent]都将覆盖原有的函数
 */
object CookieStore {
    val cookieMap = HashMap<String,String>()

    fun load(host:String): String? {
        cookieEvent?.loadCookieEvent(host,cookieMap)
        return cookieMap[host]
    }

    fun save(host: String,cookies: String){
        cookieEvent?.saveCookieEvent(cookies, host)
        cookieMap[host] = cookies
    }

    fun put(host:String,cookie:String?){
        if (cookie!=null)
            cookieMap[host] = cookie
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
        fun saveCookieEvent(cookies: String, host:String)
        fun loadCookieEvent(host:String,cookieMap: HashMap<String,String>)
    }

}