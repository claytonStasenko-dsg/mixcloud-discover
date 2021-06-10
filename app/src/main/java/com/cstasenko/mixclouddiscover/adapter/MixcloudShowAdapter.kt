package com.cstasenko.mixclouddiscover.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cstasenko.mixclouddiscover.databinding.ItemCarouselImageCardBinding
import com.cstasenko.mixclouddiscover.loadImage
import com.cstasenko.mixclouddiscover.model.MixcloudShow

class MixcloudShowAdapter(
    private val positionListenerCallback: (() -> Unit)
) : ListAdapter<MixcloudShow, MixcloudShowAdapter.MixcloudShowViewHolder>(MixcloudShowDiffUtil()) {

    private lateinit var shows: List<MixcloudShow>

    private val runnable = Runnable { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MixcloudShowViewHolder {
        return MixcloudShowViewHolder(
            ItemCarouselImageCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MixcloudShowViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
        if (position == itemCount - 2) {
            positionListenerCallback.invoke()
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }

    inner class MixcloudShowViewHolder(private val binding: ItemCarouselImageCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MixcloudShow) {
            binding.coverImage.loadImage(item.imageUrl)
            binding.coverTitle.text = item.name
        }
    }

    class MixcloudShowDiffUtil : DiffUtil.ItemCallback<MixcloudShow>() {
        override fun areItemsTheSame(oldItem: MixcloudShow, newItem: MixcloudShow): Boolean {
            return oldItem.key == newItem.key
        }

        override fun areContentsTheSame(oldItem: MixcloudShow, newItem: MixcloudShow): Boolean {
            return oldItem == newItem
        }
    }
}
