package com.valerasetrakov.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class DiffUtilItemCallbackImpl<IT>: DiffUtil.ItemCallback<IT>() {
    override fun areItemsTheSame(oldItem: IT, newItem: IT): Boolean =
        areContentsTheSame(oldItem, newItem)
    override fun areContentsTheSame(oldItem: IT, newItem: IT): Boolean =
        oldItem == newItem
}

open class DelegatesAdapter(private val delegates: List<BaseDelegate<*, *>>):
    ListAdapter<Any, BaseViewHolder<*, *>>(DiffUtilItemCallbackImpl<Any>()) {

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)!!
        return delegates.indexOfFirst{ it.checkType(item) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        delegates[viewType].onCreateViewHolder(parent)

    override fun onBindViewHolder(holder: BaseViewHolder<*, *>, position: Int) {
        val delegate  = delegates[getItemViewType(position)] as BaseDelegate<Any, ViewDataBinding>
        val item = getItem(position)
        holder.binding?.setVariable(0, item)
        delegate.onBindViewHolder(holder as BaseViewHolder<Any, ViewDataBinding>, item)
    }


}



/**
 * IT - item type
 * DB - data binding layout type
 */
abstract class BaseDelegate<IT: Any, DB: ViewDataBinding>(val layoutId: Int, private val variableId: Int, var onItemClickListener: OnItemClickListener<IT>? = null) {

    open fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<IT, DB> {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return BaseViewHolder(variableId, view)
    }

    open fun onBindViewHolder(holder: BaseViewHolder<IT, DB>, item: IT) {
        holder.bind(item)
        holder.binding?.root?.setOnClickListener {
            onItemClickListener?.onItemClick(item)
        }
    }

    abstract fun checkType(item: Any): Boolean

    interface OnItemClickListener<IT: Any> {
        fun onItemClick(item: IT)
    }
}

class BaseViewHolder<IT: Any, out DB: ViewDataBinding>(private val variableId: Int, view: View): RecyclerView.ViewHolder(view) {

    val binding: DB? = DataBindingUtil.bind<DB?>(view)

    fun bind(item: IT) {
        binding?.setVariable(variableId, item)
    }
}