package com.quanticheart.core.base.dialog.extentions

import android.content.Context
import android.text.Spanned
import android.view.View
import androidx.fragment.app.Fragment
import com.quanticheart.core.R
import com.quanticheart.core.base.dialog.BaseFragmentDialog
import com.quanticheart.core.databinding.DialogActionsBinding
import com.quanticheart.core.extentions.commons.view.hide
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
            acceptLabel,
            acceptListener,
            resources.getString(R.string.label_cancel),
            null,
            true
        )
    }
}

fun Fragment?.dialogAlert(
    title: String?,
    msg: String?,
    acceptLabel: String,
    acceptListener: (() -> Unit)? = null
) {
    this?.context?.dialogAlert(title, msg, acceptLabel, acceptListener)
}

fun Context?.dialogAlert(
    title: String?,
    msg: String?,
    acceptLabel: String,
    acceptListener: (() -> Unit)? = null
) {
    this?.let {
        DialogDefaultAction(
            it,
            title,
            msg.toSpannedText(),
            acceptLabel,
            acceptListener,
            resources.getString(R.string.label_cancel),
            null,
            false
        )
    }
}

class DialogDefaultAction(
    context: Context,
    private val title: String?,
    private val msg: Spanned?,
    private val labelAccept: String?,
    private val acceptListener: (() -> Unit)?,
    private val labelCancel: String?,
    private val cancelListener: ((dialog: DialogDefaultAction) -> Unit)?,
    private val showCancelButton: Boolean = true
) : BaseFragmentDialog<DialogActionsBinding>(context, R.layout.dialog_actions) {

    override fun onViewFinishCreate(binding: DialogActionsBinding) {
        /**
         * Msg
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
        if (showCancelButton) {
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
        } else {
            binding.spaceX.hide()
            binding.btnCancel.hide()
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