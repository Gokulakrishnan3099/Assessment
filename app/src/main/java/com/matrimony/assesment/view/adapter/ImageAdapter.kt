package com.matrimony.assesment.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matrimony.assesment.databinding.ImageItemBinding

class ImageAdapter(private val context: Context, private val mList: List<String>) :
    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageAdapter.ViewHolder {
        val binding = ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageAdapter.ViewHolder, position: Int) {
        with(holder){
            binding.apply {
                Glide
                    .with(context)
                    .load(mList[position])
                    .centerCrop()
                    .into(imageView)

            }
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(val binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root)
}
