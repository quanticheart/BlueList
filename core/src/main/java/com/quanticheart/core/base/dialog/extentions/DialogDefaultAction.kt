package com.quanticheart.core.base.dialog.extentions

import android.app.Activity
import android.view.View
import androidx.fragment.app.Fragment
import com.quanticheart.core.R
import com.quanticheart.core.base.dialog.BaseFragmentDialog
import com.quanticheart.core.databinding.DialogActionsBinding

fun Fragment.dialogAction(
    title: String?,
    msg: String,
    acceptLabel: String,
    acceptListener: () -> Unit
) {
    activity?.let {
        DialogDefaultAction(
            it,
            title,
            msg,
            resources.getString(R.string.label_cancel),
            null,
            acceptLabel,
            acceptListener
        )
    }
}

class DialogDefaultAction(
    activity: Activity,
    private val title: String?,
    private val msg: String?,
    private val labelCancel: String?,
    private val cancelListener: ((dialog: DialogDefaultAction) -> Unit)?,
    private val labelAccept: String?,
    private val acceptListener: (() -> Unit)?
) : BaseFragmentDialog<DialogActionsBinding>(activity, R.layout.dialog_actions) {

    override fun onViewFinishCreate(binding: DialogActionsBinding) {

        /**
         * Msgs
         */
        binding.dialogTitle.apply {
            visibility = title?.let {
                text = it
                View.VISIBLE
            } ?: View.GONE
        }

        binding.dialogMsg.apply {
            visibility = msg?.let {
                text = it
                View.VISIBLE
            } ?: View.GONE
        }

        /**
         * Actions
         */
        binding.btnCancel.apply {
            visibility = labelCancel?.let {
                text = it
                setOnClickListener {
                    cancelListener?.let { it1 -> it1(this@DialogDefaultAction) }
                    safeHide()
                }
                View.VISIBLE
            } ?: run {
                binding.spaceX.visibility = View.GONE
                View.GONE
            }
        }

        binding.btnOK.apply {
            visibility = labelAccept?.let {
                text = it
                setOnClickListener {
                    acceptListener?.let { it1 -> it1() }
                    safeHide()
                }
                View.VISIBLE
            } ?: run {
                binding.spaceX.visibility = View.GONE
                View.GONE
            }
        }

        /**
         * Default Actions
         */
        binding.dialogDefaultClose.apply {
            setOnClickListener {
                cancelListener?.let { it1 -> it1(this@DialogDefaultAction) }
                safeHide()
            }
        }
    }
}