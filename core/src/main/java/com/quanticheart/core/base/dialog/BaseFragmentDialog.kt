package com.quanticheart.core.base.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.quanticheart.core.base.dialog.constants.DialogConstants
import com.quanticheart.core.extentions.system.logE
import com.quanticheart.core.extentions.system.logI
import com.quanticheart.core.extentions.vars.toDp

abstract class BaseFragmentDialog<T : ViewDataBinding>(
    private val mContext: Context,
    @LayoutRes private val resLayout: Int
) : DialogFragment(), OnDialogActions<T> {

    protected var binding: T? = null

    override fun onShow() {
        this.javaClass.simpleName.logI(DialogConstants.TAG_DIALOG_SHOW)
    }

    override fun onHide() {
        this.javaClass.simpleName.logI(DialogConstants.TAG_DIALOG_HIDE)
    }

    init {
        safeShow()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, resLayout, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.let { onViewFinishCreate(it) }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val d = super.onCreateDialog(savedInstanceState)
        d.apply {
            setOnShowListener {
                onShow()
            }
        }
        return d
    }

    override fun onStart() {
        super.onStart()
        dialog?.apply {
            val window = window
            window?.apply {
                val padding = 16.toDp
                setBackgroundDrawable(
                    InsetDrawable(
                        ColorDrawable(Color.TRANSPARENT),
                        padding,
                        padding,
                        padding,
                        padding
                    )
                )
                setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }

            setOnDismissListener {
                onHide()
            }
        }
    }

    private fun safeShow() {
        try {
            (mContext as AppCompatActivity?)?.supportFragmentManager?.let {
                show(it, mContext.javaClass.name)
            } ?: run {
                "mContext is null".logE()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun safeHide() {
        try {
            dismiss()
        } catch (e: Exception) {
        }
    }
}
