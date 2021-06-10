package com.cstasenko.mixclouddiscover.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cstasenko.mixclouddiscover.databinding.ItemCarouselImageCardBinding
import com.cstasenko.mixclouddiscover.loadImage
import com.cstasenko.mixclouddiscover.model.MixcloudShow

class MixcloudCarouselAdapter(
    private val itemList: List<MixcloudShow>,
    private val clickListenerCallback: ((item: MixcloudShow) -> Unit)
) :
    RecyclerView.Adapter<MixcloudCarouselAdapter.MixcloudShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MixcloudShowViewHolder {
        return MixcloudShowViewHolder(
            ItemCarouselImageCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MixcloudShowViewHolder, position: Int) {
        itemList.get(position).let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }

    inner class MixcloudShowViewHolder(private val binding: ItemCarouselImageCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MixcloudShow) {
            binding.coverImage.loadImage(item.imageUrl)
            binding.coverTitle.text = item.name
            binding.coverImage.setOnClickListener {
                clickListenerCallback.invoke(item)
            }
        }
    }
}
