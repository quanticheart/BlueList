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
import com.quanticheart.core.base.viewModel.BaseViewModel
import com.quanticheart.core.databinding.FragmentBaseBinding
import org.koin.android.viewmodel.ext.android.viewModel
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

abstract class BaseFragment<viewModel : BaseViewModel, dataBinding : ViewDataBinding>(@LayoutRes private val resLayout: Int) :
    Fragment() {

    protected val viewModel: viewModel? by viewModel(getClassByT())

    protected lateinit var binding: dataBinding

    private lateinit var baseBinding: FragmentBaseBinding

    abstract fun onFinishBindingView(binding: dataBinding)

    abstract fun onFinishLoadViewModel(viewModel: viewModel)

    /**
     * Create view
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        baseBinding = FragmentBaseBinding.inflate(inflater, container, false)
        binding = DataBindingUtil.inflate(layoutInflater, resLayout, null, false)
        binding.apply {
            lifecycleOwner = this@BaseFragment
        }
        baseBinding.containerBaseFragment.apply {
            addView(binding.root)
        }
        return baseBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFinishBindingView(binding)
        viewModel?.let { onFinishLoadViewModel(it) }
    }

    fun loading(status: Boolean) {
        baseBinding.flipperLoading.displayedChild = if (status) 1 else 0
    }

    @Suppress("UNCHECKED_CAST")
    private fun getClassByT(): KClass<viewModel> =
        ((javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<viewModel>).kotlin
}