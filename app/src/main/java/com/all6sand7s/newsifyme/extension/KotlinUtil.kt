package com.all6sand7s.newsifyme.extension

import android.content.Context
import android.widget.Toast

/**
 * Created by adris on 2/27/2018.
 */
fun Context.showToast(message :String, length :Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}