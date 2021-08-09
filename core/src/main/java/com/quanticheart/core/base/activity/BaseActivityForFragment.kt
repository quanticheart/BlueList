package com.quanticheart.core.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.quanticheart.core.R
import com.quanticheart.core.databinding.FragmentBaseBinding

abstract class BaseActivityForFragment : AppCompatActivity() {

    abstract fun setBaseFragmentView(): Fragment

    private lateinit var baseBinding: FragmentBaseBinding

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseBinding = FragmentBaseBinding.inflate(layoutInflater)
        setContentView(baseBinding.root)

        val f = setBaseFragmentView()
        val fBundle = f.arguments

        intent.extras?.let {
            val b = fBundle ?: Bundle()
            b.putAll(it)
            f.arguments = b
        }

        supportFragmentManager.beginTransaction().replace(R.id.containerBaseFragment, f).commit()
    }
}