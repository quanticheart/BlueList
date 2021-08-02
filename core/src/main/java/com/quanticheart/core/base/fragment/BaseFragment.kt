@file:Suppress("MemberVisibilityCanBePrivate")

package com.quanticheart.core.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.quanticheart.core.databinding.FragmentBaseBinding

abstract class BaseFragment<T : ViewDataBinding>(@LayoutRes private val resLayout: Int) :
    Fragment() {

    protected val binding: T by
    lazy {
        DataBindingUtil.inflate(
            layoutInflater, resLayout, null, false
        )
    }

    private lateinit var baseBinding: FragmentBaseBinding

    abstract fun onFinishBindingView(binding: T)

    /**
     * Create view
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        baseBinding = FragmentBaseBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = this@BaseFragment
        }
        baseBinding.containerBaseFragment.addView(binding.root)
        return baseBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFinishBindingView(binding)
    }
}