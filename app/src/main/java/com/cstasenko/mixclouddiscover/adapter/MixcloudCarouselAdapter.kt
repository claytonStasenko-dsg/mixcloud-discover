package com.cstasenko.mixclouddiscover.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cstasenko.mixclouddiscover.databinding.ItemCarouselImageCardBinding
import com.cstasenko.mixclouddiscover.loadImage
import com.cstasenko.mixclouddiscover.model.MixcloudShow

class MixcloudCarouselAdapter(initialItemList: List<MixcloudShow>, private val clickListenerCallback: ((item: MixcloudShow) -> Unit))
    : RecyclerView.Adapter<MixcloudCarouselAdapter.MixcloudShowViewHolder>(){

    //TODO send this from the view Model into the recyclerview
    private val itemList: List<MixcloudShow> =
        listOf(initialItemList.last().copy(key = "faked1")) + initialItemList + listOf(initialItemList.first().copy(key = "faked2"))


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
            binding.imageCard.setOnClickListener {
                clickListenerCallback.invoke(item)
            }
        }
    }

}