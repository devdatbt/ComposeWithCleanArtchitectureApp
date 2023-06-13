package com.example.notecomposeapp.utils

import android.annotation.SuppressLint
import com.google.gson.Gson
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer.ValueParametersHandler.DEFAULT

fun Float.convertCurrency(): String {
    val formatter = DecimalFormat("#,###,###")
    return formatter.format(this)
}

@SuppressLint("SimpleDateFormat")
fun Long.convertSimpleDateFormat(): String {
    val formatter = SimpleDateFormat("E, dd MMM yyyy")
    return formatter.format(Date(this))
}

fun String.convertStringToUpperCase(): String {
    return this.substring(0, 1).uppercase() + this.substring(1)
}

fun <T> String.jsonToObject(type: Class<T>): T {
    return Gson().fromJson(this, type)
}

fun <T> T.objectToJson(): String? {
    return Gson().toJson(this)
}

