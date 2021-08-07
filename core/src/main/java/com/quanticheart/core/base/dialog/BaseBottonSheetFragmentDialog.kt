package com.quanticheart.core.base.dialog

import android.app.Activity
import android.app.Dialog
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
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.quanticheart.core.base.dialog.constants.DialogConstants
import com.quanticheart.core.extentions.vars.toDp
import com.quanticheart.core.extentions.system.logI

abstract class BaseBottonSheetFragmentDialog<T : ViewDataBinding>(
    private val activity: Activity,
    @LayoutRes private val resLayout: Int
) : BottomSheetDialogFragment(), OnDialogActions<T> {

    protected var binding: T? = null

    override fun onShow() {
        this.javaClass.simpleName.logI(DialogConstants.TAG_DIALOG_SHOW)
    }

    override fun onHide() {
        this.javaClass.simpleName.logI(DialogConstants.TAG_DIALOG_HIDE)
        dismiss()
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
                val padding = 0.toDp
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
            (activity as AppCompatActivity?)?.supportFragmentManager?.let {
                show(
                    it,
                    activity.javaClass.name
                )
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
