package com.quanticheart.bluelist.view.fragment.list.dialog

/* ktlint-disable no-wildcard-imports */
import android.app.Activity
import com.quanticheart.bluelist.R
import com.quanticheart.bluelist.databinding.DialogAddToDoBinding
import com.quanticheart.core.base.dialog.BaseBottonSheetFragmentDialog
import com.quanticheart.core.extentions.commons.view.onCheckListener
import com.quanticheart.core.extentions.commons.view.setCheckedListener
import com.quanticheart.core.extentions.commons.view.setSafeOnClickListener
import com.quanticheart.core.extentions.commons.view.statusInvisible
import com.quanticheart.core.extentions.system.showDatePicker
import com.quanticheart.core.extentions.system.showTimePicker
import com.quanticheart.core.extentions.system.toast
import com.quanticheart.core.extentions.vars.toDate
import com.quanticheart.core.extentions.vars.toDateLabel
import com.quanticheart.domain.model.ToDoInsert
import java.util.*

class DialogAddToDo(
    private val activity: Activity,
    private val callback: (ToDoInsert) -> Unit
) :
    BaseBottonSheetFragmentDialog<DialogAddToDoBinding>(activity, R.layout.dialog_add_to_do) {

    override fun onViewFinishCreate(binding: DialogAddToDoBinding) {

        var priority = 1
        var alarm = false
        var dateAlarm: Date? = null

        binding.priorityGroup.setCheckedListener {
            priority = when (it) {
                R.id.low -> 1
                R.id.medium -> 2
                R.id.high -> 3
                else -> 1
            }
        }

        binding.addAlarm.onCheckListener {
            alarm = it
            binding.addAlarmDate.statusInvisible(it)
        }

        binding.addAlarmDate.setSafeOnClickListener {
            dateAlarm = null
            var selectedDate: String
            activity.showDatePicker { date ->
                selectedDate = date
                activity.showTimePicker { time ->
                    selectedDate = "$selectedDate $time"
                    dateAlarm = selectedDate.toDate()
                    binding.addAlarmDate.text = selectedDate.toDateLabel()
                }
            }
        }

        binding.btn.setOnClickListener {
            if (alarm && dateAlarm == null) {
                activity.toast(getString(R.string.msg_warning_to_do_date_alarm))
            } else {
                val title = binding.title.text?.toString()
                val desc = binding.desc.text?.toString()

                if (title.isNullOrEmpty()) {
                    activity.toast(getString(R.string.msg_warning_to_do_write_all_fields))
                } else {
                    callback(ToDoInsert(Date(), title, desc, priority, dateAlarm))
                    this.safeHide()
                }
            }
        }

        binding.cancel.setOnClickListener { safeHide() }
    }
}