@file:Suppress("unused")

package com.quanticheart.core.generics.recyclerView

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BindingListAdapter<T : Any, binding : ViewDataBinding>(
    @LayoutRes private val layoutResource: Int,
    diff: DiffUtil.ItemCallback<T>? = null
) :
    ListAdapter<T, BindingListAdapter<T, binding>.DataBindingViewHolder>(
        diff ?: BaseItemCallback<T>()
    ) {

    abstract fun bind(binding: binding, item: T, position: Int)

    override fun getItemViewType(position: Int): Int = layoutResource

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<binding>(layoutInflater, viewType, parent, false)
        return DataBindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    override fun submitList(list: List<T>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    inner class DataBindingViewHolder(private val binding: binding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: T, position: Int) {
            bind(binding, item, position)
            binding.executePendingBindings()
        }
    }
}

class BaseDiffCallback<T : Any>(private val oldList: List<T>, private val newList: List<T>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}

open class BaseItemCallback<T : Any> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem.toString() == newItem.toString()

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem.toString() == newItem.toString()
}
