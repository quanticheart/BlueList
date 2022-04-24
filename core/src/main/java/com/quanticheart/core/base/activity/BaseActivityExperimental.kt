@file:Suppress("MemberVisibilityCanBePrivate", "UNCHECKED_CAST", "unused")

package com.quanticheart.core.base.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.quanticheart.core.base.viewModel.BaseViewModel
import com.quanticheart.core.databinding.FragmentBaseBinding
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

abstract class BaseActivityExperimental<viewModel : BaseViewModel, dataBinding : ViewBinding> :
    AppCompatActivity() {

    val viewModel: viewModel? by lazy {
        ViewModelProvider(this).get(getClassByT())
    }

    protected lateinit var binding: dataBinding
    private lateinit var baseBinding: FragmentBaseBinding

    abstract fun onFinishBindingView(binding: dataBinding)

    abstract fun onFinishLoadViewModel(viewModel: viewModel)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val superclass: Type = javaClass.genericSuperclass!!
        val aClass = (superclass as ParameterizedType).actualTypeArguments[1] as Class<*>
        val method: Method = aClass.getDeclaredMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.javaPrimitiveType
        )
        binding = method.invoke(null, layoutInflater, null, false) as dataBinding
        baseBinding = FragmentBaseBinding.inflate(layoutInflater)
        setContentView(baseBinding.root)
        baseBinding.containerBaseFragment.apply {
            addView(binding.root)
        }
        onFinishBindingView(binding)
        viewModel?.let { onFinishLoadViewModel(it) }
    }

    fun loading(status: Boolean) {
        baseBinding.flipperLoading.displayedChild = if (status) 1 else 0
    }

    @Suppress("UNCHECKED_CAST")
    private fun getClassByT(): Class<viewModel> =
        ((javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<viewModel>)
}
