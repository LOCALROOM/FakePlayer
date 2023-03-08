package com.anrayus.apirequest

import com.anrayus.apirequest.store.CookieStore
import com.google.gson.Gson
import okhttp3.*

open class BaseService {

    private val client = OkHttpClient().newBuilder()
    val cookieStore = CookieStore()
    protected val gson = Gson()
    init {
        client.cookieJar(object : CookieJar {
            override fun loadForRequest(url: HttpUrl): List<Cookie> {
                return cookieStore.load(url.host) ?: emptyList()
            }

            override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
                cookieStore.save(url.host,cookies)
            }

        })
    }

    /**
     * @param requestUrl 请求Api地址
     * @param key 请求的Key def=null
     * @param cookie 请求头cookie def=null
     * @param params 请求附带参数
     */
    fun request(requestUrl:String,key:String?,vararg params:Pair<String,String>):String{
        val url = StringBuffer()
        url.append("${RequestManager.getBaseUrl()}${requestUrl}?timestamp=${System.currentTimeMillis()}")

        params.forEach{
            url.append("&${it.first}=${it.second}")
        }

        if (key!=null){
            url.append("&key=${key}")
        }
        val request = Request.Builder().apply {
            url(url.toString())
        }.build()


        return client.build().newCall(request).execute().body.string()
    }
}