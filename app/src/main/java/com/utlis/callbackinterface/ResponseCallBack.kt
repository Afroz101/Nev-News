package com.utlis.callbackinterface

interface ResponseCallBack {
    fun onApiFailure(message:String)
    fun onApiException(e: String)
}