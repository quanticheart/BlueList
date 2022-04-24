package com.quanticheart.core.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.quanticheart.core.base.GenericBinding
import com.quanticheart.core.base.viewModel.BaseViewModel
import com.quanticheart.core.databinding.FragmentBaseBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

abstract class BaseActivity<viewModel : BaseViewModel, dataBinding : ViewBinding> :
    AppCompatActivity(), ActivityConstruct<viewModel, dataBinding> {

    protected val viewModel: viewModel? by lazy { getViewModel(clazz = getClassGenericForViewModel()) }

    protected lateinit var binding: dataBinding
    private lateinit var baseBinding: FragmentBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = GenericBinding(getClassGenericForViewBinding()).inflate(layoutInflater)
        baseBinding = FragmentBaseBinding.inflate(layoutInflater)
        setContentView(baseBinding.root)
        baseBinding.containerBaseFragment.apply {
            addView(binding.root)
        }
        view(binding)
        viewModel?.let { viewModel(it) }
    }

    fun loading(status: Boolean) {
        baseBinding.flipperLoading.displayedChild = if (status) 1 else 0
    }

    @Suppress("UNCHECKED_CAST")
    private fun getClassGenericForViewModel(): KClass<viewModel> =
        ((javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<viewModel>).kotlin

    @Suppress("UNCHECKED_CAST")
    private fun getClassGenericForViewBinding(): Class<dataBinding> =
        ((javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<dataBinding>)
}
