@file:Suppress("MemberVisibilityCanBePrivate")

package com.quanticheart.core.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.quanticheart.core.base.GenericBinding
import com.quanticheart.core.base.activity.ActivityConstruct
import com.quanticheart.core.base.viewModel.BaseViewModel
import com.quanticheart.core.databinding.FragmentBaseBinding
import com.quanticheart.core.extentions.commons.view.createBackToolbar
import com.quanticheart.core.system.broadcast.destroyBroadcastManager
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

abstract class BaseFragment<viewModel : BaseViewModel, dataBinding : ViewBinding> :
    Fragment(), ActivityConstruct<viewModel, dataBinding> {

    protected val viewModel: viewModel? by lazy { getViewModel(clazz = getClassGenericForViewModel()) }

    protected lateinit var binding: dataBinding

    private lateinit var baseBinding: FragmentBaseBinding

    /**
     * Create view
     */
    @Suppress("UNCHECKED_CAST")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            GenericBinding(getClassGenericForViewBinding()).inflate(inflater, container, false)
        baseBinding = FragmentBaseBinding.inflate(inflater, container, false)
        baseBinding.containerBaseFragment.apply {
            addView(binding.root)
        }
        return baseBinding.root
    }

    protected fun backToolbar(title: String? = null, listener: View.OnClickListener? = null) {
        this@BaseFragment.activity?.let {
            baseBinding.toolbarDefaultFragment.createBackToolbar(
                it,
                title,
                listener
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view(binding)
        viewModel?.let { viewModel(it) }
    }

    fun loading(status: Boolean) {
        baseBinding.flipperLoading.displayedChild = if (status) 1 else 0
    }

    fun finish() = activity?.finish()
    @Suppress("UNCHECKED_CAST")
    private fun getClassGenericForViewModel(): KClass<viewModel> =
        ((javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<viewModel>).kotlin

    @Suppress("UNCHECKED_CAST")
    private fun getClassGenericForViewBinding(): Class<dataBinding> =
        ((javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<dataBinding>)

    override fun onDestroy() {
        super.onDestroy()
        destroyBroadcastManager()
    }
}
