package com.example.tasteatlas

import com.google.gson.Gson

fun dataToJson(model: Any): String = Gson().toJson(model).replace("/", "$$$")
fun jsonToData(data: String, model: Class<*>): Any? = Gson().fromJson(data.replace("$$$", "/"), model)