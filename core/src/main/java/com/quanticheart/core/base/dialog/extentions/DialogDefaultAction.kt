package com.quanticheart.core.base.dialog.extentions

import android.content.Context
import android.text.Spanned
import android.view.View
import com.quanticheart.core.R
import com.quanticheart.core.base.dialog.BaseFragmentDialog
import com.quanticheart.core.databinding.DialogActionsBinding
import com.quanticheart.core.extentions.vars.toSpannedText

fun Context?.dialogAction(
    title: String?,
    msg: String?,
    acceptLabel: String,
    acceptListener: () -> Unit
) {
    this?.let {
        DialogDefaultAction(
            it,
            title,
            msg.toSpannedText(),
            resources.getString(R.string.label_cancel),
            null,
            acceptLabel,
            acceptListener
        )
    }
}

class DialogDefaultAction(
    context: Context,
    private val title: String?,
    private val msg: Spanned?,
    private val labelCancel: String?,
    private val cancelListener: ((dialog: DialogDefaultAction) -> Unit)?,
    private val labelAccept: String?,
    private val acceptListener: (() -> Unit)?
) : BaseFragmentDialog<DialogActionsBinding>(context, R.layout.dialog_actions) {

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