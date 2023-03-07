package com.anrayus.apirequest.model

data class Key(val data: KeyInfo, val code:Int)
data class KeyInfo(val unikey:String)