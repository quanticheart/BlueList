package com.quanticheart.bluelist.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.quanticheart.bluelist.R
import com.quanticheart.core.base.activity.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}