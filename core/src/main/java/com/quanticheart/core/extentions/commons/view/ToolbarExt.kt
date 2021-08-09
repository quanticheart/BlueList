package com.quanticheart.core.extentions.commons.view

import android.app.Activity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import com.quanticheart.core.R

fun Toolbar.createBackToolbar(
    activity: Activity,
    toolbarTitle: String?,
    callback: View.OnClickListener? = null
) {
    activity as AppCompatActivity
    activity.setSupportActionBar(this)
    val bar = activity.supportActionBar
    toolbarTitle?.let {
        bar?.title = it
        bar?.setDisplayHomeAsUpEnabled(true)
    } ?: run { bar?.setDisplayShowTitleEnabled(false) }
    navigationIcon =
        ResourcesCompat.getDrawable(activity.resources, R.drawable.ic_baseline_arrow_back, null)
    callback?.let {
        setNavigationOnClickListener(it)
    } ?: run {
        setNavigationOnClickListener { activity.finish() }
    }
    show()
}
