package com.anrayus.apirequest.model

data class QRCode(val data: QRCodeInfo, val code: Int)
data class QRCodeInfo(val qrurl:String,val qrimg:String)
data class QRCodeStatusInfo(val code:Int,
                            val message:String,
                            val cookie:String)